import { useEffect, useState } from "react";
import axios from "axios";

function PendingClaims() {
  const [claims, setClaims] = useState([]);

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchPendingClaims();
  }, []);

  const fetchPendingClaims = async () => {
    try {
      const res = await axios.get(
        "http://localhost:9000/api/claims/pending",
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );
      setClaims(res.data);
    } catch (err) {
      console.error("Error fetching pending claims:", err);
    }
  };

  const handleApprove = async (id) => {
    try {
      await axios.put(
        `http://localhost:9000/api/claims/approve/${id}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      fetchPendingClaims();
    } catch (err) {
      console.error("Approve error:", err);
    }
  };

  const handleReject = async (id) => {
    try {
      await axios.put(
        `http://localhost:9000/api/claims/reject/${id}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      fetchPendingClaims();
    } catch (err) {
      console.error("Reject error:", err);
    }
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1 style={{ marginBottom: "30px" }}>Pending Claims</h1>

      <div style={tableWrapper}>
        <table style={tableStyle}>
          <thead>
            <tr style={theadStyle}>
              <th style={th}>ID</th>
              <th style={th}>User</th>
              <th style={th}>Policy</th>
              <th style={th}>Amount</th>
              <th style={th}>Actions</th>
            </tr>
          </thead>

          <tbody>
            {claims.map((claim) => (
              <tr key={claim.id} style={rowStyle}>
                <td style={td}>{claim.id}</td>
                <td style={td}>{claim.username}</td>
                <td style={td}>{claim.policy?.name}</td>
                <td style={td}>₹{claim.amount}</td>
                <td style={td}>
                  <button
                    style={approveBtn}
                    onClick={() => handleApprove(claim.id)}
                  >
                    Approve
                  </button>

                  <button
                    style={rejectBtn}
                    onClick={() => handleReject(claim.id)}
                  >
                    Reject
                  </button>
                </td>
              </tr>
            ))}

            {claims.length === 0 && (
              <tr>
                <td colSpan="5" style={{ padding: "20px", textAlign: "center" }}>
                  No Pending Claims
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

/* -------- Styles -------- */

const tableWrapper = {
  background: "#ffffff",
  borderRadius: "12px",
  overflow: "hidden",
  boxShadow: "0 4px 15px rgba(0,0,0,0.1)"
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse"
};

const theadStyle = {
  background: "#f1f5f9"
};

const th = {
  padding: "15px",
  textAlign: "left"
};

const td = {
  padding: "15px"
};

const rowStyle = {
  borderBottom: "1px solid #e5e7eb"
};

const approveBtn = {
  padding: "8px 15px",
  marginRight: "10px",
  background: "#10b981",
  color: "white",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer"
};

const rejectBtn = {
  padding: "8px 15px",
  background: "#ef4444",
  color: "white",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer"
};

export default PendingClaims;
