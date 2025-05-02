import React, { useEffect, useState } from 'react';
import { fetchUserProfile, updateUserProfile } from '../Components/api';
import './UserProfile.css';

export default function UserProfile() {
  const [userInfo, setUserInfo] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  // Pull user profile from server once on mount
  useEffect(() => {
    fetchUserProfile().then(data => {
      setUserInfo(data);
    });
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserInfo(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const saveProfile = () => {
    updateUserProfile(userInfo).then(() => {
      alert('Profile updated successfully!');
    }).catch(err => {
      console.error('Error saving profile:', err);
      alert('Something went wrong while saving.');
    });
  };

  return (
    <div className="profile-page">
      <div className="profile-content">
        <h2>My Profile</h2>

        <label>
          First Name
          <input
            type="text"
            name="firstName"
            value={userInfo.firstName}
            onChange={handleInputChange}
          />
        </label>

        <label>
          Last Name
          <input
            type="text"
            name="lastName"
            value={userInfo.lastName}
            onChange={handleInputChange}
          />
        </label>

        <label>
          Email
          <input
            type="email"
            name="email"
            value={userInfo.email}
            onChange={handleInputChange}
          />
        </label>

        <button onClick={saveProfile}>Save Changes</button>
      </div>
    </div>
  );
}
