import React from 'react';

// Display message when there are pending items
export default function MessageBanner({ count }) {
  if (count <= 0) return null;

  return (
    <div className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-800 p-4 mb-6">
      <p>You have {count} adjusting entries awaiting approval.</p>
    </div>
  );
}
