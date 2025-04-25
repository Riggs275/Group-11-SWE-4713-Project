import React, { useEffect, useState } from 'react';
import axios from 'axios';
import NavMenu from './NavMenu';
import MessageBanner from './MessageBanner';
import RatioCard from './RatioCard';

// Dashboard page to display ratio cards and pending notifications
export default function DashboardPage() {
  const [ratios, setRatios] = useState([]);
  const [pendingCount, setPendingCount] = useState(0);
  const userType = localStorage.getItem("userType");

  useEffect(() => {
    // Get all ratios
    axios.get('/api/dashboard/ratios')
      .then(res => setRatios(Object.entries(res.data)))
      .catch(console.error);

    // Get num of pending approvals
    axios.get('/api/dashboard/notifications/count')
      .then(res => setPendingCount(res.data))
      .catch(console.error);
  }, []);

  return (
    <div className="p-8">
      <header className="flex items-center justify-between mb-8">
        <h1 className="text-3xl font-bold">Dashboard</h1>
        <NavMenu type={userType} />
      </header>

      <MessageBanner count={pendingCount} />

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {ratios.map(([name, { value, status }]) => (
          <RatioCard
            key={name}
            ratioName={name}
            value={value}
            status={status}
          />
        ))}
      </div>
    </div>
  );
}
