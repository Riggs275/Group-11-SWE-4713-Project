import { fetchAccounts } from '../Components/api';
import './ChartOfAccounts.css';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ChartOfAccounts() {
  const [accounts, setAccounts] = useState([]);
  const [filters, setFilters] = useState({
    name: '',
    number: '',
    category: '',
  });

  const navigate = useNavigate();

  // Load accounts once on component mount
  useEffect(() => {
    fetchAccounts().then(data => {
      setAccounts(data);
    });
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters(prev => ({
      ...prev,
      [name]: value,
    }));
  };

  // Basic client-side filtering logic
  const filteredAccounts = accounts.filter(account => {
    const matchesName = account.name.toLowerCase().includes(filters.name.toLowerCase());
    const matchesNumber = account.number.includes(filters.number);
    const matchesCategory = account.category.toLowerCase().includes(filters.category.toLowerCase());

    return matchesName && matchesNumber && matchesCategory;
  });

  return (
    <div className="coa-page">
      <div className="coa-content">
        <h2>Chart of Accounts</h2>

        <div className="coa-filters">
          {/* These filters are super basic but should be enough for now */}
          <input
            type="text"
            name="name"
            placeholder="Account Name"
            value={filters.name}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="number"
            placeholder="Account Number"
            value={filters.number}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="category"
            placeholder="Category"
            value={filters.category}
            onChange={handleInputChange}
          />
        </div>

        <table className="coa-table">
          <thead>
            <tr>
              <th>Number</th>
              <th>Name</th>
              <th>Category</th>
              <th>Balance</th>
            </tr>
          </thead>
          <tbody>
            {filteredAccounts.map(acc => {
              const formattedBalance = acc.balance.toLocaleString(undefined, {
                minimumFractionDigits: 2,
              });

              return (
                <tr key={acc.id} onClick={() => navigate(`/accounts/${acc.id}`)}>
                  <td>{acc.number}</td>
                  <td>{acc.name}</td>
                  <td>{acc.category}</td>
                  <td>{formattedBalance}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ChartOfAccounts;
