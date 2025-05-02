import { fetchAccountById, updateAccount, fetchEventLog } from '../Components/api';
import './AccountDetails.css';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

function AccountDetails() {
  const { id } = useParams();
  const [account, setAccount] = useState(null);
  const [eventLog, setEventLog] = useState([]);
  const [editing, setEditing] = useState(false);
  const [formData, setFormData] = useState({});

  // Grab account and event log data on load
  useEffect(() => {
    fetchAccountById(id).then(acc => {
      setAccount(acc);
      setFormData(acc);
    });

    fetchEventLog('ACCOUNT', id).then(setEventLog);
  }, [id]);

  if (!account) return <div>Loading account info…</div>;

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value,
    }));
  };

  const saveChanges = () => {
    updateAccount(id, formData).then(updated => {
      setAccount(updated);
      setEditing(false);
    });
  };

  return (
    <div className="acct-page">
      <div className="acct-content">
        <h2>Account Details</h2>

        <div className="acct-info">
          {editing ? (
            <div>
              <label>
                Name
                <input
                  type="text"
                  name="name"
                  value={formData.name || ''}
                  onChange={handleInputChange}
                />
              </label>

              <label>
                Number
                <input
                  type="text"
                  name="number"
                  value={formData.number || ''}
                  onChange={handleInputChange}
                />
              </label>

              <button onClick={saveChanges}>Save</button>
              <button onClick={() => setEditing(false)}>Cancel</button>
            </div>
          ) : (
            <div>
              <p><strong>Name:</strong> {account.name}</p>
              <p><strong>Number:</strong> {account.number}</p>
              <p><strong>Category:</strong> {account.category}</p>
              <p><strong>Balance:</strong> {account.balance.toFixed(2)}</p>
              <button onClick={() => setEditing(true)}>Edit</button>
            </div>
          )}
        </div>

        <h3>Event Log</h3>
        <table className="log-table">
          <thead>
            <tr>
              <th>Timestamp</th>
              <th>User</th>
              <th>Before</th>
              <th>After</th>
            </tr>
          </thead>
          <tbody>
            {eventLog.map(log => {
              const ts = new Date(log.timestamp).toLocaleString();
              return (
                <tr key={log.id}>
                  <td>{ts}</td>
                  <td>{log.userId}</td>
                  <td><pre>{JSON.stringify(log.before, null, 2)}</pre></td>
                  <td><pre>{JSON.stringify(log.after, null, 2)}</pre></td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AccountDetails;
