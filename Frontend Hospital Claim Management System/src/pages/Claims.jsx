import { useEffect, useState } from "react";
import axios from "axios";

export default function Claims() {

  const [receipts, setReceipts] = useState([]);
  const [claims, setClaims] = useState([]);
  const [activePolicy, setActivePolicy] = useState(null);

  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  useEffect(() => {
    if (!username || !token) return;

    fetchReceipts();
    fetchClaims();
    fetchActivePolicy();
  }, []);

  const fetchReceipts = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9000/api/receipt/user/${username}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setReceipts(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchClaims = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9000/api/claims/user/${username}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setClaims(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchActivePolicy = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9000/api/claims/user/dashboard/${username}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );

      setActivePolicy(res.data.activePolicy);
    } catch (err) {
      console.error(err);
      setActivePolicy(null);
    }
  };

  const handleClaim = async (receiptId) => {

    if (!activePolicy) {
      alert("No active policy selected.");
      return;
    }

    try {
      await axios.post(
        `http://localhost:9000/api/claims/create/${username}/${receiptId}/${activePolicy.id}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );

      alert("Claim submitted successfully");
      fetchReceipts();
      fetchClaims();

    } catch (err) {
      console.error(err);
      alert("Claim failed");
    }
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1 style={{ marginBottom: "20px" }}>New Claim</h1>

      {/* Receipts Section */}
      <div style={{ marginTop: "20px" }}>
        {receipts
          .filter(r => r.status === "UNUSED")
          .map(receipt => (
            <div key={receipt.id} style={receiptCard}>
              <p><b>Hospital:</b> {receipt.hospitalName}</p>
              <p><b>Treatment:</b> {receipt.treatmentName}</p>
              <p><b>Amount:</b> ₹{receipt.expense}</p>

              <button
                onClick={() => handleClaim(receipt.id)}
                style={claimBtn}
              >
                Claim
              </button>
            </div>
          ))}

        {receipts.filter(r => r.status === "UNUSED").length === 0 &&
          <p style={{ color: "#64748b" }}>
            No receipts available for claim.
          </p>}
      </div>

      {/* Claim History */}
      <h2 style={{ marginTop: "60px", marginBottom: "20px" }}>
        Claim History
      </h2>

      <div style={tableWrapper}>
        <table style={tableStyle}>
          <thead style={theadStyle}>
            <tr>
              <th style={thStyle}>ID</th>
              <th style={thStyle}>Policy</th>
              <th style={thStyle}>Amount</th>
              <th style={thStyle}>Status</th>
            </tr>
          </thead>

          <tbody>
            {claims.map(claim => (
              <tr key={claim.id} style={rowStyle}>
                <td style={tdStyle}>{claim.id}</td>
                <td style={tdStyle}>{claim.policy?.name}</td>
                <td style={tdStyle}>₹{claim.amount}</td>
                <td style={tdStyle}>
                  <span style={statusBadge(claim.status)}>
                    {claim.status}
                  </span>
                </td>
              </tr>
            ))}

            {claims.length === 0 &&
              <tr>
                <td colSpan="4" style={emptyRow}>
                  No claims yet.
                </td>
              </tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}

/* ---------- Styles ---------- */

const receiptCard = {
  background: "#ffffff",
  padding: "20px",
  borderRadius: "14px",
  marginBottom: "18px",
  boxShadow: "0 8px 20px rgba(0,0,0,0.08)"
};

const claimBtn = {
  marginTop: "12px",
  padding: "8px 18px",
  background: "#2563eb",
  color: "#fff",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer",
  fontWeight: "500"
};

const tableWrapper = {
  background: "#ffffff",
  borderRadius: "16px",
  overflow: "hidden",
  boxShadow: "0 8px 25px rgba(0,0,0,0.08)"
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse"
};

const theadStyle = {
  background: "#1e293b",
  color: "#ffffff"
};

const thStyle = {
  padding: "18px",
  textAlign: "left",
  fontSize: "14px",
  letterSpacing: "0.5px"
};

const tdStyle = {
  padding: "18px",
  fontSize: "15px"
};

const rowStyle = {
  borderBottom: "1px solid #e2e8f0"
};

const emptyRow = {
  padding: "30px",
  textAlign: "center",
  color: "#64748b"
};

const statusBadge = (status) => {
  if (status === "APPROVED")
    return {
      background: "#dcfce7",
      color: "#166534",
      padding: "6px 14px",
      borderRadius: "20px",
      fontWeight: "600",
      fontSize: "13px"
    };

  if (status === "REJECTED")
    return {
      background: "#fee2e2",
      color: "#991b1b",
      padding: "6px 14px",
      borderRadius: "20px",
      fontWeight: "600",
      fontSize: "13px"
    };

  return {
    background: "#fef3c7",
    color: "#92400e",
    padding: "6px 14px",
    borderRadius: "20px",
    fontWeight: "600",
    fontSize: "13px"
  };
};
