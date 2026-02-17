import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { signupUser } from "../services/authService";

function Signup() {

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: "",
    password: ""
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await signupUser({
        ...formData,
        role: "USER"
      });

      alert("Signup successful. Please login.");
      navigate("/user/login");

    } catch (error) {
      alert("Signup failed");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h2>User Signup</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="username"
          placeholder="Username"
          onChange={handleChange}
          required
        />
        <br /><br />

        <input
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
          required
        />
        <br /><br />

        <button type="submit">Signup</button>
      </form>
    </div>
  );
}

export default Signup;
