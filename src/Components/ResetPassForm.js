import { useState, useEffect } from "react";
import { SetPassword } from "./Buttons";
import { setNewPassword } from "./api";
import { useNavigate, useLocation } from "react-router-dom";
import { checkPassword } from "../Helpers/checkpassword";

export function ResetPassForm() {
    const [formData, setFormData] = useState({ newPassword: "", retype: ""});
    const [passwordError, setPasswordError] = useState(""); // Stores password validation errors
    const [matchError, setMatchError] = useState(""); // Stores mismatch error
    const navigate = useNavigate();
    const location = useLocation();
    const userID = location.state;

    useEffect(() => {
        if (userID) {
            const welcomeText = document.getElementById('welcomeUser');
            if (welcomeText) {
                welcomeText.innerText = `Hey ${userID}, type in your new password below`;
            }
        }
    }, [userID]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });

        if (name === "newPassword") {
            const errorMsg = checkPassword(value);
            setPasswordError(errorMsg);
        }

        if (name === "retype") {
            setMatchError(value !== formData.newPassword ? "Passwords do not match" : "");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (passwordError || matchError) {
            alert("Please fix errors before submitting.");
            return;
        }

        console.log("Form Data:", {...formData,userID});
        const result = await setNewPassword({...formData, userID});
        if (result.success) {
            navigate('/signin', { state: result.successMessage });
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <p className="text" id="welcomeUser">Hey UserID, type in your new password below</p>
                <div className="CreateTextBox">
                    <label>
                        New Password:
                        <br />
                        <input 
                            type="password" 
                            className="Box" 
                            name="newPassword" 
                            value={formData.newPassword} 
                            onChange={handleChange} 
                        />
                        {passwordError && <p className="error-text">{passwordError}</p>}
                    </label>
                </div>
                <br />
                <div className="CreateTextBox">
                    <label>
                        Retype New Password:
                        <br />
                        <input 
                            type="password" 
                            className="Box" 
                            name="retype" 
                            value={formData.retype} 
                            onChange={handleChange} 
                        />
                        {matchError && <p className="error-text">{matchError}</p>}
                    </label>
                </div>
                <br />
                <SetPassword />
            </div>
        </form>
    );
}
