/*import { fetchAccountById, updateAccount, fetchEventLog } from '../Components/api';
import './AccountDetails.css';

function AccountDetails() {
  const { id } = useParams();
  const [account, setAccount] = useState(null);
  const [eventLog, setEventLog] = useState([]);
  const [editing, setEditing] = useState(false);
  const [form, setForm] = useState({});

  useEffect(() => {
    fetchAccountById(id).then(acc => {
      setAccount(acc);
      setForm(acc);
    });
    fetchEventLog('ACCOUNT', id).then(setEventLog);
  }, [id]);

  if (!account) return <div>Loading…</div>;

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });
  const save = () => updateAccount(id, form).then(updated => {
    setAccount(updated);
    setEditing(false);
  });

  return (
    <div className="acct-page">
      <div className="acct-content">
        <h2>Account Details</h2>
        <div className="acct-info">
          {editing
            ? <div>
                <label>Name<input name="name" value={form.name} onChange={handleChange}/></label>
                <label>Number<input name="number" value={form.number} onChange={handleChange}/></label>
                {}
                <button onClick={save}>Save</button>
                <button onClick={() => setEditing(false)}>Cancel</button>
              </div>
            : <div>
                <p><strong>Name:</strong> {account.name}</p>
                <p><strong>Number:</strong> {account.number}</p>
                <p><strong>Category:</strong> {account.category}</p>
                <p><strong>Balance:</strong> {account.balance.toFixed(2)}</p>
                <button onClick={() => setEditing(true)}>Edit</button>
              </div>
          }
        </div>
        <h3>Event Log</h3>
        <table className="log-table">
          <thead>
            <tr><th>Timestamp</th><th>User</th><th>Before</th><th>After</th></tr>
          </thead>
          <tbody>
            {eventLog.map(evt => (
              <tr key={evt.id}>
                <td>{new Date(evt.timestamp).toLocaleString()}</td>
                <td>{evt.userId}</td>
                <td><pre>{JSON.stringify(evt.before, null, 2)}</pre></td>
                <td><pre>{JSON.stringify(evt.after, null, 2)}</pre></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AccountDetails;*/
