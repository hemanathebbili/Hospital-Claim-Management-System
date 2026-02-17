import { Link, useNavigate, useLocation } from "react-router-dom";

function Layout({ children }) {
  const navigate = useNavigate();
  const location = useLocation();

  const role = localStorage.getItem("role");
  const username = localStorage.getItem("username");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  const isActive = (path) => location.pathname === path;

  const menuItemStyle = (active) => ({
    padding: "12px 15px",
    borderRadius: "8px",
    marginBottom: "10px",
    background: active ? "#3b82f6" : "transparent",
    cursor: "pointer",
  });

  const linkStyle = {
    color: "white",
    textDecoration: "none",
    display: "block",
  };

  return (
    <div style={{ display: "flex", height: "100vh" }}>

      {/* ================= SIDEBAR ================= */}
      <div
        style={{
          width: "240px",
          background: "#1e293b",
          color: "white",
          display: "flex",
          flexDirection: "column",
          position: "fixed",
          left: 0,
          top: 0,
          bottom: 0,
        }}
      >
        {/* Top Menu */}
        <div style={{ padding: "25px" }}>
          <h2 style={{ marginBottom: "40px" }}>SkyLine</h2>

          <ul style={{ listStyle: "none", padding: 0 }}>

            {/* ================= USER MENU ================= */}
            {role === "USER" && (
              <>
                <li style={menuItemStyle(isActive("/user/dashboard"))}>
                  <Link to="/user/dashboard" style={linkStyle}>
                    Dashboard
                  </Link>
                </li>

                <li style={menuItemStyle(isActive("/user/policies"))}>
                  <Link to="/user/policies" style={linkStyle}>
                    Policies
                  </Link>
                </li>

                <li style={menuItemStyle(isActive("/claims"))}>
                  <Link to="/claims" style={linkStyle}>
                    Claims
                  </Link>
                </li>

                <li style={menuItemStyle(isActive("/treatments"))}>
                  <Link to="/treatments" style={linkStyle}>
                    Treatments
                  </Link>
                </li>
              </>
            )}

            {/* ================= ADMIN MENU ================= */}
            {role === "ADMIN" && (
              <>
                <li style={menuItemStyle(isActive("/dashboard"))}>
                  <Link to="/dashboard" style={linkStyle}>
                    Dashboard
                  </Link>
                </li>

                <li style={menuItemStyle(isActive("/policies"))}>
                  <Link to="/policies" style={linkStyle}>
                    Manage Policies
                  </Link>
                </li>

                <li style={menuItemStyle(isActive("/pending-claims"))}>
                  <Link to="/pending-claims" style={linkStyle}>
                    Pending Claims
                  </Link>
                </li>
              </>
            )}
          </ul>
        </div>

        {/* Logout fixed bottom */}
        <div
          style={{
            marginTop: "auto",
            padding: "20px",
          }}
        >
          <button
            onClick={handleLogout}
            style={{
              width: "100%",
              padding: "12px",
              background: "#ef4444",
              border: "none",
              borderRadius: "8px",
              color: "white",
              fontWeight: "bold",
              cursor: "pointer",
            }}
          >
            Logout
          </button>
        </div>
      </div>

      {/* ================= MAIN CONTENT ================= */}
      <div
        style={{
          marginLeft: "240px",
          flex: 1,
          display: "flex",
          flexDirection: "column",
          height: "100vh",
        }}
      >
        {/* Section Header */}
        <div
          style={{
            padding: "25px 40px",
            background: "#ffffff",
            borderBottom: "1px solid #e5e7eb",
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <h2 style={{ margin: 0 }}>
            {location.pathname
              .replace("/user/", "")
              .replace("/", "")
              .replace("-", " ")
              .toUpperCase()}
          </h2>

          <div style={{ fontWeight: "bold" }}>
            {role} - {username}
          </div>
        </div>

        {/* Scrollable Content */}
        <div
          style={{
            flex: 1,
            overflowY: "auto",
            padding: "40px",
            background: "#f4f6f9",
          }}
        >
          {children}
        </div>
      </div>
    </div>
  );
}

export default Layout;
