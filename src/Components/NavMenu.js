import React from 'react';
import { Link } from 'react-router-dom';

// Render navigation menu with user role items
export default function NavMenu({ userType }) {
  const menuItems = {
    admin: ['Dashboard', 'Accounts', 'Users', 'Reports'],
    manager: ['Dashboard', 'Approvals', 'Reports'],
    accountant: ['Dashboard', 'Journal', 'Entries']
  }[userType] || [];

  return (
    <nav>
      <ul className="flex space-x-4">
        {menuItems.map(item => (
          <li key={item}>
            <Link to={`/${item.toLowerCase()}`} className="text-blue-600 hover:underline">
              {item}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}
