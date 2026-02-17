import { useEffect, useState } from "react";
import axios from "axios";

function Policies() {

  const role = localStorage.getItem("role");
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  const [policies, setPolicies] = useState([]);
  const [activePolicy, setActivePolicy] = useState(null);

  // ADMIN STATES
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [coverage, setCoverage] = useState("");
  const [premium, setPremium] = useState("");
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    fetchPolicies();
    if (role === "USER") {
      fetchActivePolicy();
    }
  }, []);

  const fetchPolicies = async () => {
    const res = await axios.get(
      "http://localhost:9000/api/policies/all",
      { headers: { Authorization: `Bearer ${token}` } }
    );
    setPolicies(res.data);
  };

  const fetchActivePolicy = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9000/api/claims/user/dashboard/${username}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setActivePolicy(res.data.activePolicy);
    } catch {
      setActivePolicy(null);
    }
  };

  // ================= ADMIN FUNCTIONS =================

  const handleSubmit = async () => {

    const policyData = {
      name,
      description,
      coverage: Number(coverage),
      premium: Number(premium)
    };

    if (editId) {
      await axios.put(
        `http://localhost:9000/api/policies/update/${editId}`,
        policyData,
        { headers: { Authorization: `Bearer ${token}` } }
      );
    } else {
      await axios.post(
        "http://localhost:9000/api/policies/create",
        policyData,
        { headers: { Authorization: `Bearer ${token}` } }
      );
    }

    resetForm();
    fetchPolicies();
  };

  const handleEdit = (policy) => {
    setEditId(policy.id);
    setName(policy.name);
    setDescription(policy.description);
    setCoverage(policy.coverage);
    setPremium(policy.premium);
  };

  const handleDelete = async (id) => {
    await axios.delete(
      `http://localhost:9000/api/policies/delete/${id}`,
      { headers: { Authorization: `Bearer ${token}` } }
    );
    fetchPolicies();
  };

  const resetForm = () => {
    setEditId(null);
    setName("");
    setDescription("");
    setCoverage("");
    setPremium("");
  };

  // ================= USER FUNCTION =================

  const handleSelect = async (policyId) => {
    await axios.post(
      `http://localhost:9000/api/user-policies/attach/${username}/${policyId}`,
      {},
      { headers: { Authorization: `Bearer ${token}` } }
    );

    fetchActivePolicy();
    alert("Policy activated successfully");
  };

  // ================= RENDER =================

  return (
    <div style={{ padding: "40px" }}>

      <h1 style={{ marginBottom: "30px" }}>
        {role === "ADMIN" ? "Manage Policies" : "Available Policies"}
      </h1>

      {/* ================= ADMIN UI ================= */}
      {role === "ADMIN" && (
        <>
          <div style={card}>
            <input placeholder="Name" value={name} onChange={e => setName(e.target.value)} style={input}/>
            <input placeholder="Description" value={description} onChange={e => setDescription(e.target.value)} style={input}/>
            <input placeholder="Coverage" value={coverage} onChange={e => setCoverage(e.target.value)} style={input}/>
            <input placeholder="Premium" value={premium} onChange={e => setPremium(e.target.value)} style={input}/>

            <button onClick={handleSubmit} style={primaryBtn}>
              {editId ? "Update" : "Create"}
            </button>

            {editId && (
              <button onClick={resetForm} style={secondaryBtn}>
                Cancel
              </button>
            )}
          </div>
        </>
      )}

      {/* ================= TABLE ================= */}
      <div style={tableWrapper}>
        <table style={tableStyle}>
          <thead style={{ background: "#f1f5f9" }}>
            <tr>
              <th style={th}>ID</th>
              <th style={th}>Name</th>
              <th style={th}>Description</th>
              <th style={th}>Coverage</th>
              <th style={th}>Premium</th>
              <th style={th}>
                {role === "ADMIN" ? "Actions" : "Select"}
              </th>
            </tr>
          </thead>

          <tbody>
            {policies.map(policy => (
              <tr key={policy.id} style={row}>
                <td style={td}>{policy.id}</td>
                <td style={td}>{policy.name}</td>
                <td style={td}>{policy.description}</td>
                <td style={td}>₹{policy.coverage}</td>
                <td style={td}>₹{policy.premium}</td>
                <td style={td}>
                  {role === "ADMIN" ? (
                    <>
                      <button onClick={() => handleEdit(policy)} style={editBtn}>
                        Edit
                      </button>
                      <button onClick={() => handleDelete(policy.id)} style={deleteBtn}>
                        Delete
                      </button>
                    </>
                  ) : activePolicy?.id === policy.id ? (
                    <span style={{ color: "green", fontWeight: "bold" }}>
                      Active
                    </span>
                  ) : (
                    <button onClick={() => handleSelect(policy.id)} style={primaryBtn}>
                      Select
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

    </div>
  );
}

/* ================= STYLES ================= */

const card = {
  background: "#fff",
  padding: "20px",
  borderRadius: "12px",
  boxShadow: "0 4px 15px rgba(0,0,0,0.08)",
  marginBottom: "30px"
};

const input = {
  display: "block",
  width: "100%",
  padding: "10px",
  marginBottom: "10px",
  borderRadius: "6px",
  border: "1px solid #ddd"
};

const tableWrapper = {
  background: "#fff",
  borderRadius: "12px",
  boxShadow: "0 4px 15px rgba(0,0,0,0.08)",
  overflow: "hidden"
};

const tableStyle = { width: "100%", borderCollapse: "collapse" };
const th = { padding: "15px", textAlign: "left" };
const td = { padding: "15px" };
const row = { borderBottom: "1px solid #e5e7eb" };

const primaryBtn = {
  padding: "8px 16px",
  background: "#3b82f6",
  color: "#fff",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer",
  marginRight: "10px"
};

const secondaryBtn = {
  padding: "8px 16px",
  background: "#6b7280",
  color: "#fff",
  border: "none",
  borderRadius: "6px",
  cursor: "pointer"
};

const editBtn = {
  ...primaryBtn,
  background: "#10b981"
};

const deleteBtn = {
  ...primaryBtn,
  background: "#ef4444"
};

export default Policies;
