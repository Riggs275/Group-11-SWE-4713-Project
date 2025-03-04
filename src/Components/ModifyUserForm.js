import { useState, useEffect } from "react";
import { ModifyUser } from "./Buttons";
import { adminAddAccountRequest } from "./api";
import { useLocation, useNavigate } from "react-router-dom";
import { checkPassword } from "../Helpers/checkpassword";

export function ModifyAccountForm() {
    const location = useLocation();
    const row = location.state; // Retrieve passed data
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        firstName: "", 
        address: "", 
        lastName: "", 
        email: "", 
        DOB: "", 
        password: ""
    });

    const [passwordError, setPasswordError] = useState(""); // Password validation error

    useEffect(() => {
        if (row) {
            setFormData({
                firstName: row.FirstName || "",
                lastName: row.LastName || "",
                address: row.Address || "",
                email: row.Email || "",
                DOB: row.DOB || "",
                password: "", // Keep password empty for security reasons
                userType: row.UserType
            });
        }
    }, [row]); 

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));

        if (name === "password") {
            setPasswordError(checkPassword(value));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);

        if (passwordError.trim().length > 0) {
            alert("Fix password errors before submitting.");
            return;
        }

        const result = await adminAddAccountRequest(formData);
        if (result.success) {
            navigate('/adminusers', { state: result.successMessage });
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className={"FormDiv"}>
                <div>Modify Account</div>
                <br/>

                <div className={"CreateTextBox"}>
                    <label>
                        First Name:<br />
                        <input 
                            type="text" 
                            className={"Box"} 
                            name="firstName" 
                            value={formData.firstName} 
                            onChange={handleChange} 
                        />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Last Name:<br />
                        <input 
                            type="text" 
                            className={"Box"} 
                            name="lastName" 
                            value={formData.lastName} 
                            onChange={handleChange} 
                        />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Address:<br />
                        <input 
                            type="text" 
                            className={"Box"} 
                            name="address" 
                            value={formData.address} 
                            onChange={handleChange} 
                        />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Email:<br />
                        <input 
                            type="email" 
                            className={"Box"} 
                            name="email" 
                            value={formData.email} 
                            onChange={handleChange} 
                        />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Date of Birth:<br />
                        <input 
                            type="date" 
                            className={"Box"} 
                            name="DOB" 
                            value={formData.DOB} 
                            onChange={handleChange} 
                        />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        User Type:<br />
                        <select 
                            className={"Box"} 
                            name="userType" 
                            value={formData.userType} 
                            onChange={handleChange}
                        >
                            <option value="Admin">Admin</option>
                            <option value="Accountant">Accountant</option>
                            <option value="Manager">Manager</option>
                        </select>
                    </label>
                </div>
                <br />

                <div className={"CreateTextBox"}>
                    <label>
                        Password:<br />
                        <input 
                            type="password" 
                            className={"Box"} 
                            name="password" 
                            value={formData.password} 
                            onChange={handleChange} 
                        />
                        <p id="error" className={passwordError ? "error-text" : "success-text"}>
                            {passwordError ? passwordError : "This password meets the requirements"}
                        </p>
                    </label>
                </div>
                
                <br/>
                <ModifyUser />
            </div>
        </form>
    );
}
