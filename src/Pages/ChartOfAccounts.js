/*import { fetchAccounts } from '../Components/api';
import './ChartOfAccounts.css';

function ChartOfAccounts() {
    const [accounts, setAccounts] = useState([]);
    const [filter, setFilter] = useState({ name: '', number: '', category: '' });
    const navigate = useNavigate();

    useEffect(() => {
        fetchAccounts().then(data => setAccounts(data));
    }, []);

    const handleFilterChange = e => {
        setFilter({ ...filter, [e.target.name]: e.target.value });
    };

    const filtered = accounts.filter(acc =>
        acc.name.toLowerCase().includes(filter.name.toLowerCase()) &&
        acc.number.includes(filter.number) &&
        acc.category.toLowerCase().includes(filter.category.toLowerCase())
    );

    return (
        <div className="coa-page">
            <div className="coa-content">
                <h2>Chart of Accounts</h2>
                <div className="coa-filters">
                    <input name="name" placeholder="Account Name" value={filter.name} onChange={handleFilterChange} />
                    <input name="number" placeholder="Account Number" value={filter.number} onChange={handleFilterChange} />
                    <input name="category" placeholder="Category" value={filter.category} onChange={handleFilterChange} />
                </div>
                <table className="coa-table">
                    <thead>
                        <tr>
                            <th>Number</th><th>Name</th><th>Category</th><th>Balance</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filtered.map(acc => (
                            <tr key={acc.id} onClick={() => navigate(`/accounts/${acc.id}`)}>
                                <td>{acc.number}</td>
                                <td>{acc.name}</td>
                                <td>{acc.category}</td>
                                <td>{acc.balance.toLocaleString(undefined, { minimumFractionDigits: 2 })}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default ChartOfAccounts;*/
