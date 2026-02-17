import { useEffect, useState } from "react";
import axios from "axios";

function Treatments() {
  const [hospitalId, setHospitalId] = useState("");
  const [hospitalName, setHospitalName] = useState("");
  const [treatmentName, setTreatmentName] = useState("");
  const [expense, setExpense] = useState("");
  const [receipts, setReceipts] = useState([]);

  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  useEffect(() => {
    fetchReceipts();
  }, []);

  const fetchReceipts = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9000/api/receipt/user/${username}`,
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );
      setReceipts(res.data);
    } catch (err) {
      console.error("Error fetching receipts", err);
    }
  };

  const handleGenerate = async () => {
    try {
      await axios.post(
        "http://localhost:9000/api/receipt/create",
        {
          username,
          hospitalId,
          hospitalName,
          treatmentName,
          expense
        },
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );

      setHospitalId("");
      setHospitalName("");
      setTreatmentName("");
      setExpense("");

      fetchReceipts();
    } catch (err) {
      console.error("Error generating receipt", err);
    }
  };

  return (
    <div style={{ padding: "30px" }}>
      <h1 style={{ marginBottom: "25px" }}>Treatment & Receipt</h1>

      {/* FORM CARD */}
      <div
        style={{
          background: "white",
          padding: "25px",
          borderRadius: "14px",
          boxShadow: "0 8px 25px rgba(0,0,0,0.08)",
          marginBottom: "30px"
        }}
      >
        <div style={{ display: "flex", gap: "15px", flexWrap: "wrap" }}>
          <input
            placeholder="Hospital ID"
            value={hospitalId}
            onChange={(e) => setHospitalId(e.target.value)}
            style={inputStyle}
          />
          <input
            placeholder="Hospital Name"
            value={hospitalName}
            onChange={(e) => setHospitalName(e.target.value)}
            style={inputStyle}
          />
          <input
            placeholder="Treatment Name"
            value={treatmentName}
            onChange={(e) => setTreatmentName(e.target.value)}
            style={inputStyle}
          />
          <input
            placeholder="Expense"
            value={expense}
            onChange={(e) => setExpense(e.target.value)}
            style={inputStyle}
          />
          <button onClick={handleGenerate} style={buttonStyle}>
            Generate Receipt
          </button>
        </div>
      </div>

      {/* TABLE CARD */}
      <div
        style={{
          background: "white",
          borderRadius: "14px",
          boxShadow: "0 8px 25px rgba(0,0,0,0.08)",
          overflow: "hidden"
        }}
      >
        <table
          style={{
            width: "100%",
            borderCollapse: "collapse",
            fontFamily: "Segoe UI"
          }}
        >
          <thead>
            <tr
              style={{
                background: "linear-gradient(90deg,#1e293b,#334155)",
                color: "white",
                textAlign: "left"
              }}
            >
              <th style={thStyle}>ID</th>
              <th style={thStyle}>Hospital</th>
              <th style={thStyle}>Treatment</th>
              <th style={thStyle}>Expense</th>
              <th style={thStyle}>Status</th>
            </tr>
          </thead>

          <tbody>
            {receipts.map((r, index) => (
              <tr
                key={r.id}
                style={{
                  background: index % 2 === 0 ? "#f8fafc" : "#ffffff"
                }}
              >
                <td style={tdStyle}>{r.id}</td>
                <td style={tdStyle}>{r.hospitalName}</td>
                <td style={tdStyle}>{r.treatmentName}</td>
                <td style={tdStyle}>₹{r.expense}</td>
                <td
                  style={{
                    ...tdStyle,
                    fontWeight: "600",
                    color:
                      r.status === "CLAIMED"
                        ? "#10b981"
                        : "#f59e0b"
                  }}
                >
                  {r.status}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

/* ===== Styles ===== */

const inputStyle = {
  padding: "12px",
  borderRadius: "8px",
  border: "1px solid #cbd5e1",
  minWidth: "180px",
  outline: "none"
};

const buttonStyle = {
  padding: "12px 20px",
  background: "#3b82f6",
  color: "white",
  border: "none",
  borderRadius: "8px",
  cursor: "pointer"
};

const thStyle = {
  padding: "16px",
  fontSize: "15px"
};

const tdStyle = {
  padding: "14px",
  borderBottom: "1px solid #e2e8f0",
  fontSize: "14px"
};

export default Treatments;
