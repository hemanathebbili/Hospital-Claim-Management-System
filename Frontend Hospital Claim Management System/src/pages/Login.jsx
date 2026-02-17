import { useState } from "react";
import { useNavigate } from "react-router-dom";
import loginImage from "../assets/image.jpg";
import axios from "axios";

function Login() {
  const [mode, setMode] = useState("USER_LOGIN"); 
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isDark, setIsDark] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // USER SIGNUP
      if (mode === "USER_SIGNUP") {
        await axios.post("http://localhost:9000/auth/signup", {
          username,
          password,
          role: "USER"
        });

        alert("User signup successful. Please login.");
        setMode("USER_LOGIN");
        return;
      }

      // ADMIN SIGNUP
      if (mode === "ADMIN_SIGNUP") {
        await axios.post("http://localhost:9000/auth/signup", {
          username,
          password,
          role: "ADMIN"
        });

        alert("Admin signup successful. Please login.");
        setMode("ADMIN_LOGIN");
        return;
      }

      // LOGIN (ADMIN OR USER)
      const response = await axios.post(
        "http://localhost:9000/auth/login",
        { username, password }
      );

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("username", username);

      if (mode === "ADMIN_LOGIN") {
        localStorage.setItem("role", "ADMIN");
        navigate("/dashboard");
      } else {
        localStorage.setItem("role", "USER");
        navigate("/user/dashboard");
      }

    } catch (error) {
      alert("Operation failed");
    }
  };

  return (
    <div
      style={{
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        backgroundColor: isDark ? "#0f172a" : "#f4f6f9",
        color: isDark ? "#ffffff" : "#000000"
      }}
    >
      {/* HEADER */}
      <div
        style={{
          height: "120px",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          position: "relative"
        }}
      >
        <h1
          style={{
            fontSize: "50px",
            fontFamily: "'Dancing Script', cursive",
            margin: 0
          }}
        >
          SkyLine
        </h1>

        <label
          style={{ position: "absolute", right: "40px" }}
          className="switch"
        >
          <input
            type="checkbox"
            checked={isDark}
            onChange={() => setIsDark(!isDark)}
          />
          <span className="slider"></span>
        </label>
      </div>

      {/* MAIN SECTION */}
      <div
        style={{
          flex: 1,
          display: "flex",
          alignItems: "center",
          justifyContent: "center"
        }}
      >
        {/* LEFT IMAGE */}
        <div style={{ width: "45%", padding: "40px" }}>
          <img
            src={loginImage}
            alt="Login"
            style={{
              width: "100%",
              borderRadius: "20px",
              boxShadow: "0 10px 25px rgba(0,0,0,0.15)"
            }}
          />
        </div>

        {/* RIGHT CARD */}
        <div
          style={{
            width: "40%",
            backgroundColor: isDark ? "#1e293b" : "#ffffff",
            padding: "40px",
            borderRadius: "20px",
            boxShadow: "0 10px 25px rgba(0,0,0,0.15)"
          }}
        >
          {/* ROLE BUTTONS */}
          <div style={{ display: "flex", gap: "10px", marginBottom: "30px" }}>
            <button
              onClick={() => setMode("ADMIN_LOGIN")}
              style={{
                flex: 1,
                padding: "12px",
                backgroundColor:
                  mode === "ADMIN_LOGIN" ? "#3b82f6" : "#bfdbfe",
                border: "none",
                borderRadius: "8px",
                cursor: "pointer"
              }}
            >
              Admin Login
            </button>

            <button
              onClick={() => setMode("USER_LOGIN")}
              style={{
                flex: 1,
                padding: "12px",
                backgroundColor:
                  mode === "USER_LOGIN" ? "#3b82f6" : "#bfdbfe",
                border: "none",
                borderRadius: "8px",
                cursor: "pointer"
              }}
            >
              User Login
            </button>

            <button
              onClick={() => setMode("USER_SIGNUP")}
              style={{
                flex: 1,
                padding: "12px",
                backgroundColor:
                  mode === "USER_SIGNUP" ? "#3b82f6" : "#bfdbfe",
                border: "none",
                borderRadius: "8px",
                cursor: "pointer"
              }}
            >
              User Signup
            </button>

            {/* NEW ADMIN SIGNUP BUTTON */}
            <button
              onClick={() => setMode("ADMIN_SIGNUP")}
              style={{
                flex: 1,
                padding: "12px",
                backgroundColor:
                  mode === "ADMIN_SIGNUP" ? "#3b82f6" : "#bfdbfe",
                border: "none",
                borderRadius: "8px",
                cursor: "pointer"
              }}
            >
              Admin Signup
            </button>
          </div>

          <h2 style={{ marginBottom: "20px" }}>
            {mode === "ADMIN_LOGIN" && "Admin Login"}
            {mode === "USER_LOGIN" && "User Login"}
            {mode === "USER_SIGNUP" && "User Signup"}
            {mode === "ADMIN_SIGNUP" && "Admin Signup"}
          </h2>

          {/* FORM */}
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              style={{
                width: "100%",
                padding: "14px",
                marginBottom: "20px",
                borderRadius: "8px",
                border: "1px solid #ccc"
              }}
            />

            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              style={{
                width: "100%",
                padding: "14px",
                marginBottom: "20px",
                borderRadius: "8px",
                border: "1px solid #ccc"
              }}
            />

            <button
              type="submit"
              style={{
                width: "100%",
                padding: "14px",
                backgroundColor: "#2563eb",
                color: "#ffffff",
                border: "none",
                borderRadius: "8px",
                fontSize: "16px",
                cursor: "pointer"
              }}
            >
              {mode === "USER_SIGNUP" || mode === "ADMIN_SIGNUP"
                ? "Signup"
                : "Login"}
            </button>
          </form>
        </div>
      </div>

      {/* Toggle CSS remains same */}
      <style>
        {`
        .switch {
          position: relative;
          display: inline-block;
          width: 50px;
          height: 25px;
        }
        .switch input { opacity: 0; width: 0; height: 0; }
        .slider {
          position: absolute;
          cursor: pointer;
          top: 0; left: 0; right: 0; bottom: 0;
          background-color: #ccc;
          transition: .4s;
          border-radius: 34px;
        }
        .slider:before {
          position: absolute;
          content: "";
          height: 19px; width: 19px;
          left: 3px; bottom: 3px;
          background-color: white;
          transition: .4s;
          border-radius: 50%;
        }
        input:checked + .slider { background-color: #3b82f6; }
        input:checked + .slider:before { transform: translateX(24px); }
        `}
      </style>
    </div>
  );
}

export default Login;
