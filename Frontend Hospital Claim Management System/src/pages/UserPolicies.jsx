import { useEffect, useState } from "react";
import axios from "axios";

function UserPolicies() {
  const [policies, setPolicies] = useState([]);
  const [activePolicy, setActivePolicy] = useState(null);

  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  useEffect(() => {
    fetchPolicies();
    fetchActivePolicy();
  }, []);

  const fetchPolicies = async () => {
    try {
      const res = await axios.get(
        "http://localhost:9000/api/policies/all",
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );
      setPolicies(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchActivePolicy = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9000/api/user-policies/active/${username}`,
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );
      setActivePolicy(res.data);
    } catch (err) {
      console.log("No active policy");
    }
  };

  const attachPolicy = async (policyId) => {
    try {
      await axios.post(
        `http://localhost:9000/api/user-policies/attach/${username}/${policyId}`,
        {},
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );

      alert("Policy selected successfully");
      fetchActivePolicy();
    } catch (err) {
      alert("Attach failed");
    }
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1 style={{ marginBottom: "25px" }}>Select Policy</h1>

      {activePolicy && (
        <div style={activeBox}>
          <strong>Active Policy:</strong> {activePolicy.policy.name}
        </div>
      )}

      <div style={tableWrapper}>
        <table style={tableStyle}>
          <thead style={theadStyle}>
            <tr>
              <th style={th}>ID</th>
              <th style={th}>Name</th>
              <th style={th}>Description</th>
              <th style={th}>Coverage</th>
              <th style={th}>Premium</th>
              <th style={th}>Action</th>
            </tr>
          </thead>

          <tbody>
            {policies.map((p) => (
              <tr key={p.id} style={rowStyle}>
                <td style={td}>{p.id}</td>
                <td style={td}>{p.name}</td>
                <td style={td}>{p.description}</td>
                <td style={td}>₹{p.coverage}</td>
                <td style={td}>₹{p.premium}</td>
                <td style={td}>
                  <button
                    onClick={() => attachPolicy(p.id)}
                    style={selectBtn}
                  >
                    Select
                  </button>
                </td>
              </tr>
            ))}

            {policies.length === 0 && (
              <tr>
                <td colSpan="6" style={{ padding: "20px", textAlign: "center" }}>
                  No policies available.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

/* -------------------- STYLES -------------------- */

const activeBox = {
  background: "#ecfdf5",
  padding: "12px 18px",
  borderRadius: "8px",
  marginBottom: "20px",
  color: "#065f46",
  fontWeight: "500"
};

const tableWrapper = {
  background: "#ffffff",
  borderRadius: "12px",
  overflow: "hidden",
  boxShadow: "0 6px 20px rgba(0,0,0,0.08)"
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse"
};

const theadStyle = {
  background: "#f1f5f9"
};

const th = {
  padding: "16px",
  textAlign: "left",
  fontWeight: "600"
};

const td = {
  padding: "16px"
};

const rowStyle = {
  borderBottom: "1px solid #e5e7eb"
};

const selectBtn = {
  padding: "8px 18px",
  background: "#3b82f6",
  color: "#fff",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer"
};

export default UserPolicies;
