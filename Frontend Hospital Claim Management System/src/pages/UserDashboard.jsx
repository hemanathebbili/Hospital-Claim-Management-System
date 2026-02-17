import { useEffect, useState } from "react";
import axios from "axios";

function UserDashboard() {

  const [data, setData] = useState(null);

  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!username || !token) return;
    fetchDashboard();
  }, []);

  const fetchDashboard = async () => {
    try {
      const response = await axios.get(
        `http://localhost:9000/api/claims/user/dashboard/${username}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
      setData(response.data);
    } catch (error) {
      console.error("Dashboard error:", error);
    }
  };

  if (!data) return <h2>Loading dashboard...</h2>;

  return (
    <div style={{ padding: "30px" }}>
      <h1>User Dashboard</h1>

      <div style={{
        display: "flex",
        gap: "20px",
        marginTop: "30px",
        flexWrap: "wrap"
      }}>

        {/* Active Policy */}
        <div className="card">
          <h3>Active Policy</h3>

          {data.activePolicy ? (
            <>
              <p><strong>Name:</strong> {data.activePolicy.name}</p>
              <p><strong>Coverage:</strong> ₹{data.activePolicy.coverage}</p>
              <p><strong>Premium:</strong> ₹{data.activePolicy.premium}</p>
              <p><strong>Description:</strong> {data.activePolicy.description}</p>
            </>
          ) : (
            <p>No Active Policy</p>
          )}

        </div>

        <div className="card">
          <h3>Total Claims</h3>
          <p>{data.totalClaims}</p>
        </div>

        <div className="card pending">
          <h3>Pending</h3>
          <p>{data.pendingClaims}</p>
        </div>

        <div className="card approved">
          <h3>Approved</h3>
          <p>{data.approvedClaims}</p>
        </div>

        <div className="card rejected">
          <h3>Rejected</h3>
          <p>{data.rejectedClaims}</p>
        </div>

      </div>

      <style>
        {`
        .card {
          padding: 20px;
          background: #3b82f6;
          color: white;
          border-radius: 12px;
          width: 250px;
          text-align: left;
        }

        .pending { background: #f59e0b; }
        .approved { background: #10b981; }
        .rejected { background: #ef4444; }
        `}
      </style>
    </div>
  );
}

export default UserDashboard;
