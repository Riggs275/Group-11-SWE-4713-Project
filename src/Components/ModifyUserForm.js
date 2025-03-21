import { useState, useEffect } from "react";
import { ModifyUser } from "./Buttons";
import { editUserRequest } from "./api";
import { useLocation, useNavigate } from "react-router-dom";

export function ModifyAccountForm() {
    const location = useLocation();
    const row = location.state; // Retrieve passed data
    const navigate = useNavigate();
    const makerID = location.state?.userID || localStorage.getItem("userID"); // Get from state or localStorage
    const [userName, setUserName] = useState("");

    useEffect(() => {
        if (row?.userName) {
            setUserName(row.userName);
        }
    }, [row]);


    const [formData, setFormData] = useState({
        firstName: "", 
        lastName: "", 
        address: "", 
        email: "", 
        DOB: "", 
        userType: "",
        "makerID": makerID,
        "userID": row.userName
    });


    useEffect(() => {
        if (row) {
            setFormData({
                firstName: row.firstName || "",
                lastName: row.lastName || "",
                address: row.address || "",
                email: row.email || "",
                DOB: row.dob || "",
                userType: row.userType,
                "makerID": makerID, 
                "userID": row.userName
            });
        }
    }, [row]); 

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));

    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);


        const result = await editUserRequest(formData);
        if (result.success) {
            navigate('/adminusers', { state: result.successMessage });
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className={"FormDiv"}>
                <h3 id="userID">Modifying {userName}</h3>
 

                <br />
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

                
                <br/>
                <ModifyUser />
            </div>
        </form>
    );
}
