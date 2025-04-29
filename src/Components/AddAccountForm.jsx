import React, { useState } from 'react';
import './AddAccountForm.css';

const AddAccountForm = ({ onSubmit, onClose }) => {
  const [formData, setFormData] = useState({
    accountName: '',
    accountNumber: '',
    category: '',
    subCategory: '',
  });

  const [errors, setErrors] = useState({});

  const categories = ['Asset', 'Liability', 'Equity', 'Revenue', 'Expense'];
  const subCategories = {
    Asset: ['Cash', 'Accounts Receivable', 'Inventory'],
    Liability: ['Accounts Payable', 'Loans'],
    Equity: ['Common Stock', 'Retained Earnings'],
    Revenue: ['Sales', 'Service Revenue'],
    Expense: ['Rent', 'Utilities', 'Salaries'],
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
    setErrors((prev) => ({
      ...prev,
      [name]: '',
    }));
  };

  const validate = () => {
    const newErrors = {};
    if (!formData.accountName.trim()) newErrors.accountName = 'Account Name is required';
    if (!formData.accountNumber.trim()) newErrors.accountNumber = 'Account Number is required';
    if (!formData.category) newErrors.category = 'Category is required';
    if (!formData.subCategory) newErrors.subCategory = 'Subcategory is required';
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      onSubmit(formData);
      // Do NOT close the form — just clear the inputs
      setFormData({
        accountName: '',
        accountNumber: '',
        category: '',
        subCategory: '',
      });
    }
  };

  return (
    <form className="add-account-form" onSubmit={handleSubmit}>
      <div className="form-header">
        <h3>Add Account</h3>
        <button type="button" onClick={onClose} className="button secondary">✖</button>
      </div>

      <div className="form-group">
        <label htmlFor="accountName">Account Name</label>
        <input
          type="text"
          id="accountName"
          name="accountName"
          value={formData.accountName}
          onChange={handleChange}
        />
        {errors.accountName && <span className="error">{errors.accountName}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="accountNumber">Account Number</label>
        <input
          type="text"
          id="accountNumber"
          name="accountNumber"
          value={formData.accountNumber}
          onChange={handleChange}
        />
        {errors.accountNumber && <span className="error">{errors.accountNumber}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="category">Category</label>
        <select
          id="category"
          name="category"
          value={formData.category}
          onChange={handleChange}
        >
          <option value="">--Select Category--</option>
          {categories.map((cat) => (
            <option key={cat} value={cat}>{cat}</option>
          ))}
        </select>
        {errors.category && <span className="error">{errors.category}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="subCategory">Subcategory</label>
        <select
          id="subCategory"
          name="subCategory"
          value={formData.subCategory}
          onChange={handleChange}
          disabled={!formData.category}
        >
          <option value="">--Select Subcategory--</option>
          {formData.category &&
            subCategories[formData.category].map((subCat) => (
              <option key={subCat} value={subCat}>{subCat}</option>
            ))}
        </select>
        {errors.subCategory && <span className="error">{errors.subCategory}</span>}
      </div>

      <button type="submit" className="submit-button">
        Add Account
      </button>
    </form>
  );
};

export default AddAccountForm;
