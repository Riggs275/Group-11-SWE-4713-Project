import React, { useState } from 'react';
import { changePassword } from '../Components/api';
import './ChangePassword.css';

export default function ChangePassword() {
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  // Password must start with a letter, include a number and symbol, min 8 chars
  const isValidPassword = (pw) => {
    return /^(?=^[A-Za-z])(?=.*\d)(?=.*\W).{8,}$/.test(pw);
  };

  const handleSubmit = () => {
    if (!isValidPassword(newPassword)) {
      setErrorMsg(
        'Password must start with a letter, be at least 8 characters, and include a number & special character.'
      );
      return;
    }

    // Actually call the API to attempt password change
    changePassword({ oldPw: oldPassword, newPw: newPassword })
      .then(res => {
        if (res.success) {
          alert('Password changed successfully!');
          setOldPassword('');
          setNewPassword('');
          setErrorMsg('');
        } else {
          setErrorMsg(res.message || 'Something went wrong.');
        }
      })
      .catch(err => {
        setErrorMsg('Unexpected error. Please try again.');
        console.error(err);
      });
  };

  return (
    <div className="change-pw-page">
      <div className="change-pw-content">
        <h2>Change Password</h2>

        {errorMsg && <div className="error">{errorMsg}</div>}

        <label>
          Old Password
          <input
            type="password"
            value={oldPassword}
            onChange={e => setOldPassword(e.target.value)}
          />
        </label>

        <label>
          New Password
          <input
            type="password"
            value={newPassword}
            onChange={e => setNewPassword(e.target.value)}
          />
        </label>

        <button onClick={handleSubmit}>Submit</button>
      </div>
    </div>
  );
}
