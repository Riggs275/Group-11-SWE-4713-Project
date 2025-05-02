import React, { useState } from 'react';
import { generateReport } from '../Components/api';
import './FinancialReports.css';

export default function FinancialReports() {
  const [range, setRange] = useState({ from: '', to: '' });
  const [reportType, setReportType] = useState('trial_balance');
  const [reportData, setReportData] = useState(null); // Renamed for clarity

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRange(prev => ({
      ...prev,
      [name]: value,
    }));
  };

  const loadReport = async () => {
    try {
      const result = await generateReport(reportType, range);
      setReportData(result);
    } catch (err) {
      console.error('Error fetching report:', err);
      setReportData({ error: 'Could not load report. Try again later.' });
    }
  };

  return (
    <div className="fr-page">
      <div className="fr-content">
        <h2>Financial Reports</h2>

        <div className="fr-controls">
          <select
            value={reportType}
            onChange={e => setReportType(e.target.value)}
          >
            <option value="trial_balance">Trial Balance</option>
            <option value="income_statement">Income Statement</option>
            <option value="balance_sheet">Balance Sheet</option>
            <option value="retained_earnings">Retained Earnings</option>
          </select>

          {/* Date range inputs */}
          <input
            type="date"
            name="from"
            value={range.from}
            onChange={handleInputChange}
          />
          <input
            type="date"
            name="to"
            value={range.to}
            onChange={handleInputChange}
          />

          <button onClick={loadReport}>Generate</button>
        </div>

        {reportData && (
          <pre className="fr-output">
            {JSON.stringify(reportData, null, 2)}
          </pre>
        )}
      </div>
    </div>
  );
}
