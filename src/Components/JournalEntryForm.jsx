import React, { useState } from 'react';
import './JournalEntryForm.css';

const initialLine = { accountName: '', debit: '', credit: '' };

const JournalEntryForm = ({ onSubmit }) => {
  const [date, setDate] = useState('');
  const [submittedBy, setSubmittedBy] = useState('');
  const [lines, setLines] = useState([initialLine]);
  const [comment, setComment] = useState('');
  const [errors, setErrors] = useState({});

  const handleLineChange = (index, field, value) => {
    const updated = [...lines];
    updated[index][field] = value;
    setLines(updated);
  };

  const addLine = () => {
    setLines([...lines, { ...initialLine }]);
  };

  const removeLine = (index) => {
    if (lines.length > 1) {
      const updated = lines.filter((_, i) => i !== index);
      setLines(updated);
    }
  };

  const validate = () => {
    let valid = true;
    const errs = {};

    const totalDebit = lines.reduce((sum, line) => sum + parseFloat(line.debit || 0), 0);
    const totalCredit = lines.reduce((sum, line) => sum + parseFloat(line.credit || 0), 0);

    if (!date) errs.date = 'Date required';
    if (!submittedBy) errs.submittedBy = 'Submitter name required';
    lines.forEach((line, i) => {
      if (!line.accountName) {
        errs[`accountName_${i}`] = 'Account name required';
        valid = false;
      }
    });

    if (totalDebit !== totalCredit) {
      errs.balance = 'Debits and credits must match';
      valid = false;
    }

    setErrors(errs);
    return valid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      onSubmit({ date, submittedBy, comment, lines });
      setDate('');
      setSubmittedBy('');
      setLines([initialLine]);
      setComment('');
    }
  };

  return (
    <form className="journal-form" onSubmit={handleSubmit}>
      <div className="form-group">
        <label>Date</label>
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
        {errors.date && <span className="error">{errors.date}</span>}
      </div>

      <div className="form-group">
        <label>Submitted By</label>
        <input type="text" value={submittedBy} onChange={(e) => setSubmittedBy(e.target.value)} />
        {errors.submittedBy && <span className="error">{errors.submittedBy}</span>}
      </div>

      <label>Journal Lines</label>
      {lines.map((line, index) => (
        <div className="journal-line-row" key={index}>
          <input
            type="text"
            placeholder="Account Name"
            value={line.accountName}
            onChange={(e) => handleLineChange(index, 'accountName', e.target.value)}
          />
          <input
            type="number"
            placeholder="Debit"
            value={line.debit}
            onChange={(e) => handleLineChange(index, 'debit', e.target.value)}
          />
          <input
            type="number"
            placeholder="Credit"
            value={line.credit}
            onChange={(e) => handleLineChange(index, 'credit', e.target.value)}
          />
          <button type="button" onClick={() => removeLine(index)}>
            ✖
          </button>
          {errors[`accountName_${index}`] && (
            <span className="error">{errors[`accountName_${index}`]}</span>
          )}
        </div>
      ))}

      <button type="button" onClick={addLine} className="button secondary">
        + Add Line
      </button>

      {errors.balance && <p className="error">{errors.balance}</p>}

      <div className="form-group">
        <label>Comment (optional)</label>
        <textarea value={comment} onChange={(e) => setComment(e.target.value)}></textarea>
      </div>

      <button type="submit" className="submit-button">Submit Journal Entry</button>
    </form>
  );
};

export default JournalEntryForm;
