import React from 'react';
import { useNavigate } from 'react-router-dom';
import './UserDashboard.css';

export default function UserDashboard() {
  const navigate = useNavigate();
  // Fetch user info from localStorage
  const firstName = localStorage.getItem('firstName') || 'User';
  const userType = localStorage.getItem('userType') || 'guest';

  const pages = [
    { label: 'Profile', path: '/user/profile' },
    { label: 'Change Password', path: '/user/changepassword' },
    { label: 'Ledger', path: '/user/ledger' },
    { label: 'Financial Reports', path: '/user/financialreports' },
    { label: 'Chart of Accounts', path: '/user/chartofaccounts' },
    { label: 'Account Details', path: '/user/accountdetails' },
  ];

  return (
    <div className="dashboard">
      <div className="dashboard-content">
        <h1>{`Welcome, ${firstName}!`}</h1>
        <p>Your role: {userType}</p>

        <div className="dashboard-nav">
          {pages.map((page) => (
            <button key={page.path} onClick={() => navigate(page.path)} className="dashboard-button">
              {page.label}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
}
