import {useState} from "react";
import { SetPassword } from "./Buttons";
import { setNewPassword } from "./api";
import { useNavigate, useLocation } from "react-router-dom";
export function ResetPassForm(){
    const [formData, setFormData] = useState({newPassword: "", retype: "", DOB: ""});
    const navigate = useNavigate();
    const location = useLocation();
    const userID = location.state;
    if(userID != null){
    document.getElementById('welcomeUser').innerText = `Hey ${userID} in your new password in the boxes below`
    }
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
        const result = await setNewPassword(formData);
        if(result.success){
            navigate('/signin', {state: result.successMessage})
        }
    };

    return(
        <form onSubmit={handleSubmit}>
            <div>
                <p className="text" id="welcomeUser">Hey UserID type in your new password in the boxes below</p>
                <div className={"CreateTextBox"}>
                <label >
                    New Password:
                    <br/>
                    <input type="password" className={"Box"} name="newPassword" value={formData.newPassword} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"CreateTextBox"}>
                <label>
                    Retype New Password:
                    <br/>
                    <input type="password" className={"Box"} name="retype" value={formData.retype} onChange={handleChange} />
                </label>
                </div>
                <br/>
                <SetPassword/>
            </div>


        </form>
    );
}
