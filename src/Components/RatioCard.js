import React from 'react';

// Display a financial ratio with a border, colored based on status
export default function RatioCard({ ratioName, value, status }) {
  const borderClass = {
    GREEN: 'border-green-500',
    YELLOW: 'border-yellow-500',
    RED: 'border-red-500'
  }[status];

  return (
    <div className={`border-2 rounded-lg p-4 shadow ${borderClass}`}>
      <h3 className="text-lg font-semibold mb-2">{ratioName}</h3>
      <p className="text-2xl font-bold">{value.toFixed(2)}</p>
      <p className="mt-1 text-sm text-gray-600">Status: {status}</p>
    </div>
  );
}
