import React from 'react';
import './LedgerTable.css';

const LedgerTable = ({ journalLines, filter }) => {
  const filteredLines = journalLines.filter(line => {
    const accountMatch = !filter.account || line.accountName.toLowerCase().includes(filter.account.toLowerCase());
    const amountMatch = !filter.amount || line.debit == filter.amount || line.credit == filter.amount;
    const dateMatch = !filter.date || line.date === filter.date; // Assumes `line.date` exists
    return accountMatch && amountMatch && dateMatch;
  });

  let runningBalance = 0;

  return (
    <div className="ledger-table-wrapper">
      <table className="ledger-table">
        <thead>
          <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Account Name</th>
            <th>Debit</th>
            <th>Credit</th>
            <th>Balance</th>
            <th>Post Ref</th>
          </tr>
        </thead>
        <tbody>
          {filteredLines.map((line, index) => {
            const debit = parseFloat(line.debit || 0);
            const credit = parseFloat(line.credit || 0);
            runningBalance += debit - credit;

            return (
              <tr key={index}>
                <td>{line.date || '-'}</td>
                <td>{line.description || '-'}</td>
                <td>{line.accountName}</td>
                <td>{debit ? debit.toFixed(2) : ''}</td>
                <td>{credit ? credit.toFixed(2) : ''}</td>
                <td>{runningBalance.toFixed(2)}</td>
                <td>
                  <button onClick={() => filter.onClickPostRef(line.journalEntryId)} className="link-button">
                    View Entry
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default LedgerTable;
