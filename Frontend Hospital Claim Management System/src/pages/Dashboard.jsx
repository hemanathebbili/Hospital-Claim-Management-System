import { useEffect, useState } from "react";
import axios from "axios";

function Dashboard() {

  const [stats, setStats] = useState(null);
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchStats();
  }, []);

  const fetchStats = async () => {
    try {
      const res = await axios.get(
        "http://localhost:9000/api/claims/admin/dashboard",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setStats(res.data);

    } catch (err) {
      console.error("Admin dashboard error:", err);
    }
  };

  if (!stats) return <h2>Loading dashboard...</h2>;

  return (
    <div style={{ padding: "30px" }}>
      <h1 style={{ marginBottom: "30px" }}>Admin Dashboard</h1>

      <div style={{ display: "flex", gap: "25px" }}>

        <Card title="Total Claims" value={stats.totalClaims} color="#3b82f6" />
        <Card title="Pending Claims" value={stats.pendingClaims} color="#f59e0b" />
        <Card title="Approved Claims" value={stats.approvedClaims} color="#10b981" />
        <Card title="Rejected Claims" value={stats.rejectedClaims} color="#ef4444" />

      </div>
    </div>
  );
}

function Card({ title, value, color }) {
  return (
    <div
      style={{
        flex: 1,
        background: "white",
        padding: "30px",
        borderRadius: "15px",
        boxShadow: "0 8px 20px rgba(0,0,0,0.08)",
        borderLeft: `6px solid ${color}`,
        transition: "0.3s"
      }}
    >
      <h4 style={{ color: "#6b7280", marginBottom: "10px" }}>
        {title}
      </h4>

      <h2 style={{ fontSize: "32px", color }}>
        {value}
      </h2>
    </div>
  );
}

export default Dashboard;
