import React, { useState } from 'react';
import AddAccountForm from '../Components/AddAccountForm.jsx';
import AccountTable from '../Components/AccountTable.jsx';
import JournalEntryForm from '../Components/JournalEntryForm.jsx';
import LedgerTable from '../Components/LedgerTable.jsx';
import './AccountingPages.css';

/* ================= Chart Of Accounts Page ================= */
export const ChartOfAccountsPage = () => {
  const [accounts, setAccounts] = useState([]);
  const [showForm, setShowForm] = useState(false);

  const handleAddAccount = (newAccount) => {
    const accountWithZeroBalance = {
      ...newAccount,
      balance: 0,
    };
    setAccounts((prev) => [...prev, accountWithZeroBalance]);
  };

  const handleSelectAccount = (acc) => {
    console.log('Clicked account:', acc);
  };

  return (
    <div className="container">
      <h2>Chart of Accounts</h2>

      {!showForm && (
        <button onClick={() => setShowForm(true)} className="button">+ Add Account</button>
      )}

      {showForm && (
        <div className="form-wrapper">
          <AddAccountForm onSubmit={handleAddAccount} onClose={() => setShowForm(false)} />
        </div>
      )}

      <AccountTable accounts={accounts} onSelectAccount={handleSelectAccount} />
    </div>
  );
};

/* ================= Journal Entry Page ================= */
export const JournalEntryPage = () => {
  const handleSubmitJournal = (entry) => {
    console.log('Submitted journal entry:', entry);
  };

  return (
    <div className="container">
      <h2>Journal Entry</h2>
      <JournalEntryForm onSubmit={handleSubmitJournal} />
    </div>
  );
};

/* ================= Ledger Page ================= */
export const LedgerPage = () => {
  const [selectedEntryId, setSelectedEntryId] = useState(null);
  const mockJournalLines = [
    {
      date: '2025-04-17',
      accountName: 'Cash',
      debit: '1000',
      credit: '',
      journalEntryId: 1,
      description: 'Initial deposit',
    },
    {
      date: '2025-04-18',
      accountName: 'Rent Expense',
      debit: '',
      credit: '500',
      journalEntryId: 2,
      description: 'Paid rent',
    },
  ];

  const handleViewEntry = (id) => {
    setSelectedEntryId(id);
    alert(`Go to journal entry ID: ${id}`);
  };

  return (
    <div className="container">
      <h2>Ledger</h2>
      <LedgerTable journalLines={mockJournalLines} filter={{ account: '', amount: '', date: '', onClickPostRef: handleViewEntry }} />
    </div>
  );
};
