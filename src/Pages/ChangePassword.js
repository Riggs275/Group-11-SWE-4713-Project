/*import React, { useState } from 'react';
import { changePassword } from '../Components/api';
import './ChangePassword.css';

export default function ChangePassword() {
  const [oldPw, setOldPw] = useState('');
  const [newPw, setNewPw] = useState('');
  const [error, setError] = useState('');

  const validate = pw => /^(?=^[A-Za-z])(?=.*\d)(?=.*\W).{8,}$/.test(pw);

  const handleSubmit = () => {
    if (!validate(newPw)) {
      setError('Password must start with a letter, be at least 8 chars, include a number and special character.');
      return;
    }
    changePassword({ oldPw, newPw }).then(res => {
      if (res.success) alert('Password changed');
      else setError(res.message);
    });
  };

  return (
    <div className="change-pw-page">
      <div className="change-pw-content">
        <h2>Change Password</h2>
        {error && <div className="error">{error}</div>}
        <label>
          Old Password
          <input type="password" value={oldPw} onChange={e => setOldPw(e.target.value)} />
        </label>
        <label>
          New Password
          <input type="password" value={newPw} onChange={e => setNewPw(e.target.value)} />
        </label>
        <button onClick={handleSubmit}>Submit</button>
      </div>
    </div>
  );
}*/
