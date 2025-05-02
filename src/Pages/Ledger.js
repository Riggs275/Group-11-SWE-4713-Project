import { fetchLedgerEntries } from '../Components/api';
import './Ledger.css';
import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function Ledger() {
  const { accountId } = useParams();
  const navigate = useNavigate();

  const [entries, setEntries] = useState([]);
  const [filters, setFilters] = useState({
    dateFrom: '',
    dateTo: '',
    description: '',
  });

  useEffect(() => {
    fetchLedgerEntries(accountId).then(data => {
      setEntries(data);
    });
  }, [accountId]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFilters(prev => ({
      ...prev,
      [name]: value,
    }));
  };

  // Filtering entries
  const getFilteredEntries = () => {
    return entries.filter(entry => {
      const entryDate = new Date(entry.date);
      const fromDate = filters.dateFrom ? new Date(filters.dateFrom) : null;
      const toDate = filters.dateTo ? new Date(filters.dateTo) : null;

      const dateMatch = (!fromDate || entryDate >= fromDate) && (!toDate || entryDate <= toDate);
      const descMatch = entry.description.toLowerCase().includes(filters.description.toLowerCase());

      return dateMatch && descMatch;
    });
  };

  const filteredEntries = getFilteredEntries();

  return (
    <div className="ledger-page">
      <div className="ledger-content">
        <h2>{`Ledger for Account #${accountId}`}</h2>

        <div className="ledger-filters">
          {/* TODO: Could wrap these in a form */}
          <input
            type="date"
            name="dateFrom"
            value={filters.dateFrom}
            onChange={handleInputChange}
          />
          <input
            type="date"
            name="dateTo"
            value={filters.dateTo}
            onChange={handleInputChange}
          />
          <input
            name="description"
            placeholder="Search description"
            value={filters.description}
            onChange={handleInputChange}
          />
        </div>

        <table className="ledger-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Description</th>
              <th>Debit</th>
              <th>Credit</th>
              <th>Balance</th>
              <th>PR</th>
            </tr>
          </thead>
          <tbody>
            {filteredEntries.map(entry => {
              const formattedDate = new Date(entry.date).toLocaleDateString();
              return (
                <tr key={entry.id}>
                  <td>{formattedDate}</td>
                  <td>{entry.description}</td>
                  <td>{entry.debit.toFixed(2)}</td>
                  <td>{entry.credit.toFixed(2)}</td>
                  <td>{entry.balance.toFixed(2)}</td>
                  <td>
                    <button onClick={() => navigate(`/journal/${entry.journalEntryId}`)}>
                      {entry.journalEntryId}
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Ledger;
