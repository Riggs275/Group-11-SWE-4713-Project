import { useState } from "react";
import { CreateNewUser } from "./Buttons";
import { adminAddAccountRequest} from "./api";
import { useNavigate } from "react-router-dom";
import { checkPassword } from "../Helpers/checkpassword";

export function AddAccountForm() {
    const [formData, setFormData] = useState({
        firstName: "", address: "", lastName: "", email: "", DOB: "", password: ""
    });

    const [passwordError, setPasswordError] = useState(""); // Error state for password validation
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));

        if (name === "password") {
            const errorString = checkPassword(value);
            setPasswordError(errorString);
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
            navigate('/signin', { state: result.successMessage });
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className={"FormDiv"}>
                <div className={"CreateTextBox"}>
                    <label>
                        First Name:<br />
                        <input type="text" className={"Box"} name="firstName" value={formData.firstName} onChange={handleChange} />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Last Name:<br />
                        <input type="text" className={"Box"} name="lastName" value={formData.lastName} onChange={handleChange} />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Address:<br />
                        <input type="text" className={"Box"} name="address" value={formData.address} onChange={handleChange} />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Email:<br />
                        <input type="email" className={"Box"} name="email" value={formData.email} onChange={handleChange} />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Date of Birth:<br />
                        <input type="date" className={"Box"} name="DOB" value={formData.DOB} onChange={handleChange} />
                    </label>
                </div>
                <br />
                <div className={"CreateTextBox"}>
                    <label>
                        Password:<br />
                        <input type="password" className={"Box"} name="password" value={formData.password} onChange={handleChange} />
                        <p id="error" className={passwordError ? "error-text" : "success-text"}>
                            {passwordError  ? passwordError : "This password meets the requirements"}
                        </p>
                    </label>
                </div>
                <br/>
                <CreateNewUser />
            </div>
            
        </form>
    );
}
