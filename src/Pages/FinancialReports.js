/*import React, { useState } from 'react';
import { generateReport } from '../Components/api';
import './FinancialReports.css';

export default function FinancialReports() {
  const [range, setRange] = useState({ from: '', to: '' });
  const [reportType, setReportType] = useState('trial_balance');
  const [data, setData] = useState(null);

  const handleChange = e => {
    setRange({ ...range, [e.target.name]: e.target.value });
  };

  const loadReport = async () => {
    const rpt = await generateReport(reportType, range);
    setData(rpt);
  };

  return (
    <div className="fr-page">
      <div className="fr-content">
        <h2>Financial Reports</h2>
        <div className="fr-controls">
          <select value={reportType} onChange={e => setReportType(e.target.value)}>
            <option value="trial_balance">Trial Balance</option>
            <option value="income_statement">Income Statement</option>
            <option value="balance_sheet">Balance Sheet</option>
            <option value="retained_earnings">Retained Earnings</option>
          </select>
          <input type="date" name="from" value={range.from} onChange={handleChange} />
          <input type="date" name="to"   value={range.to}   onChange={handleChange} />
          <button onClick={loadReport}>Generate</button>
        </div>
        {data && (
          <pre className="fr-output">
            {JSON.stringify(data, null, 2)}
          </pre>
        )}
      </div>
    </div>
  );
}*/
