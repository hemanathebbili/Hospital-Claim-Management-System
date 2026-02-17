import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import UserDashboard from "./pages/UserDashboard";
import Dashboard from "./pages/Dashboard";
import Claims from "./pages/Claims";
import Treatments from "./pages/Treatments";
import Policies from "./pages/Policies";         // ADMIN
import UserPolicies from "./pages/UserPolicies"; // USER
import PendingClaims from "./pages/PendingClaims";
import ProtectedRoute from "./components/ProtectedRoute";
import Layout from "./components/Layout";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* LOGIN */}
        <Route path="/" element={<Login />} />

        {/* USER DASHBOARD */}
        <Route
          path="/user/dashboard"
          element={
            <ProtectedRoute>
              <Layout>
                <UserDashboard />
              </Layout>
            </ProtectedRoute>
          }
        />

        {/* ADMIN DASHBOARD */}
        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Layout>
                <Dashboard />
              </Layout>
            </ProtectedRoute>
          }
        />

        {/* USER CLAIMS */}
        <Route
          path="/claims"
          element={
            <ProtectedRoute>
              <Layout>
                <Claims />
              </Layout>
            </ProtectedRoute>
          }
        />

        {/* USER TREATMENTS */}
        <Route
          path="/treatments"
          element={
            <ProtectedRoute>
              <Layout>
                <Treatments />
              </Layout>
            </ProtectedRoute>
          }
        />

        {/* ADMIN POLICY MANAGEMENT */}
        <Route
          path="/policies"
          element={
            <ProtectedRoute>
              <Layout>
                <Policies />
              </Layout>
            </ProtectedRoute>
          }
        />

        {/* USER POLICY SELECTION */}
        <Route
          path="/user/policies"
          element={
            <ProtectedRoute>
              <Layout>
                <UserPolicies />
              </Layout>
            </ProtectedRoute>
          }
        />

        {/* ADMIN PENDING CLAIMS */}
        <Route
          path="/pending-claims"
          element={
            <ProtectedRoute>
              <Layout>
                <PendingClaims />
              </Layout>
            </ProtectedRoute>
          }
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
