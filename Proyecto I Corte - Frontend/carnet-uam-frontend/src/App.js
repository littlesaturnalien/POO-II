import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

// Rutas protegidas por rol
import ProtectedRoute from './components/ProtectedRoute';

// Página de login
import Login from './pages/Login';

// Estudiante
import StudentDashboard from './pages/student/Dashboard';
import StudentProfile from './pages/student/Profile';
import RequestID from './pages/student/RequestID';

// Admin
import AdminDashboard from './pages/admin/Dashboard';
import AdminUsers from './pages/admin/Users';
import AdminStudents from './pages/admin/Students';
import AdminCreateUsers from './pages/admin/CreateUser';
import AdminEditUser from './pages/admin/EditUser';
import StudentIDCard from './pages/admin/StudentIDCard';
import FacultyManagement from './pages/superadmin/FacultyManagement';
import DegreeManagement from './pages/superadmin/DegreeManagement';

//Superadmin
import SuperadminDashboard from "./pages/superadmin/Dashboard";

function App() {
  return (
    <Router>
      <Routes>

        {/* ✅ Login es la ruta inicial */}
        <Route path="/login" element={<Login />} />

        {/* ✅ Rutas para estudiante */}
        <Route
          path="/student/dashboard"
          element={
            <ProtectedRoute roles={['student', 'superadmin']}>
              <StudentDashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/student/profile"
          element={
            <ProtectedRoute roles={['student', 'superadmin']}>
              <StudentProfile />
            </ProtectedRoute>
          }
        />
        {/* ✅ Ruta para solicitar ID */}
        <Route
          path="/student/requestid"
          element={
            <ProtectedRoute roles={['student', 'superadmin']}>
              <RequestID />
            </ProtectedRoute>
          }
        />

        {/* ✅ Rutas para admin */}
          // Rutas de admin que ahora aceptan admin y superadmin
          <Route
              path="/admin/dashboard"
              element={
                  <ProtectedRoute roles={['admin','superadmin']}>
                      <AdminDashboard />
                  </ProtectedRoute>
              }
          />
        <Route
          path="/admin/users"
          element={
            <ProtectedRoute roles={['admin','superadmin']}>
              <AdminUsers />
            </ProtectedRoute>
          }
        />
        <Route
          path="/admin/students"
          element={
            <ProtectedRoute roles={['admin','superadmin']}>
              <AdminStudents />
            </ProtectedRoute>
          }
        />
          <Route
              path="/admin/students/:cif/idcard"
              element={
                  <ProtectedRoute roles={['admin','superadmin']}>
                      <StudentIDCard />
                  </ProtectedRoute>
              }
          />
          <Route
              path="/admin/createUser"
              element={
                  <ProtectedRoute roles={['admin','superadmin']}>
                      <AdminCreateUsers />
                  </ProtectedRoute>
              }
          />
          <Route
              path="/admin/editUser/:cif"
              element={
                  <ProtectedRoute roles={['admin','superadmin']}>
                      <AdminEditUser />
                  </ProtectedRoute>
              }
          />
          <Route
              path="/superadmin/dashboard"
              element={
                  <ProtectedRoute roles={['superadmin']}>
                      <SuperadminDashboard />
                  </ProtectedRoute>}
          />
          <Route
              path="/superadmin/faculties"
              element={
                  <ProtectedRoute roles={['superadmin']}>
                      <FacultyManagement />
                  </ProtectedRoute>}
          />
          <Route
              path="/superadmin/degrees"
              element={
                  <ProtectedRoute roles={['superadmin']}>
                      <DegreeManagement />
                  </ProtectedRoute>}
          />
        {/* ✅ Cualquier otra ruta te lleva al login */}
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </Router>
  );
}

export default App;
