import { useNavigate } from "react-router-dom";

function RoleSelect() {

  const navigate = useNavigate();

  return (
    <div style={{ textAlign: "center", marginTop: "150px" }}>
      <h1>Select Login Type</h1>

      <div style={{ marginTop: "40px" }}>
        <button
          style={{ margin: "10px", padding: "10px 25px" }}
          onClick={() => navigate("/admin/login")}
        >
          Admin Login
        </button>

        <button
          style={{ margin: "10px", padding: "10px 25px" }}
          onClick={() => navigate("/user/login")}
        >
          User Login
        </button>

        <button
          style={{ margin: "10px", padding: "10px 25px" }}
          onClick={() => navigate("/user/signup")}
        >
          User Signup
        </button>
      </div>
    </div>
  );
}

export default RoleSelect;
