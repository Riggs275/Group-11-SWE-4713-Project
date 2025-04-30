/*import React, { useEffect, useState } from 'react';
import { fetchUserProfile, updateUserProfile } from '../Components/api';
import './UserProfile.css';

export default function UserProfile() {
  const [profile, setProfile] = useState({ firstName: '', lastName: '', email: '' });
  
  useEffect(() => {
    fetchUserProfile().then(setProfile);
  }, []);

  const handleChange = e => setProfile({ ...profile, [e.target.name]: e.target.value });
  const handleSave = () => updateUserProfile(profile).then(() => alert('Saved'));

  return (
    <div className="profile-page">
      <div className="profile-content">
        <h2>My Profile</h2>
        <label>
          First Name
          <input name="firstName" value={profile.firstName} onChange={handleChange} />
        </label>
        <label>
          Last Name
          <input name="lastName" value={profile.lastName} onChange={handleChange} />
        </label>
        <label>
          Email
          <input name="email" value={profile.email} onChange={handleChange} />
        </label>
        <button onClick={handleSave}>Save Changes</button>
      </div>
    </div>
  );
}*/
