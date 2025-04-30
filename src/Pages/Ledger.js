/*import { fetchLedgerEntries } from '../Components/api';
import './Ledger.css';

function Ledger() {
  const { accountId } = useParams();
  const [entries, setEntries] = useState([]);
  const [filter, setFilter] = useState({ dateFrom: '', dateTo: '', description: '' });
  const navigate = useNavigate();

  useEffect(() => {
    fetchLedgerEntries(accountId).then(data => setEntries(data));
  }, [accountId]);

  const handleFilter = e => {
    setFilter({ ...filter, [e.target.name]: e.target.value });
  };

  const filtered = entries.filter(en => {
    const dt = new Date(en.date);
    const from = filter.dateFrom ? new Date(filter.dateFrom) : null;
    const to   = filter.dateTo   ? new Date(filter.dateTo)   : null;
    return (!from || dt >= from)
        && (!to   || dt <= to)
        && en.description.toLowerCase().includes(filter.description.toLowerCase());
  });

  return (
    <div className="ledger-page">
      <div className="ledger-content">
        <h2>Ledger for Account #{accountId}</h2>
        <div className="ledger-filters">
          <input type="date" name="dateFrom" value={filter.dateFrom} onChange={handleFilter} />
          <input type="date" name="dateTo"   value={filter.dateTo}   onChange={handleFilter} />
          <input name="description" placeholder="Search description" value={filter.description} onChange={handleFilter} />
        </div>
        <table className="ledger-table">
          <thead>
            <tr>
              <th>Date</th><th>Description</th><th>Debit</th><th>Credit</th><th>Balance</th><th>PR</th>
            </tr>
          </thead>
          <tbody>
            {filtered.map(en => (
              <tr key={en.id}>
                <td>{new Date(en.date).toLocaleDateString()}</td>
                <td>{en.description}</td>
                <td>{en.debit.toFixed(2)}</td>
                <td>{en.credit.toFixed(2)}</td>
                <td>{en.balance.toFixed(2)}</td>
                <td>
                  <button onClick={() => navigate(`/journal/${en.journalEntryId}`)}>
                    {en.journalEntryId}
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Ledger;*/
