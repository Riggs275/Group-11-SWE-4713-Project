import React from 'react';
import './AccountTable.css';

const AccountTable = ({ accounts, onSelectAccount }) => {
  return (
    <div className="account-table-wrapper">
      <table className="account-table">
        <thead>
          <tr>
            <th>Account Name</th>
            <th>Account Number</th>
            <th>Category</th>
            <th>Subcategory</th>
            <th>Balance</th>
          </tr>
        </thead>
        <tbody>
          {accounts.map((acc, idx) => (
            <tr key={idx} onClick={() => onSelectAccount(acc)}>
              <td>{acc.accountName}</td>
              <td>{acc.accountNumber}</td>
              <td>{acc.category}</td>
              <td>{acc.subCategory}</td>
              <td>{acc.balance}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AccountTable;
