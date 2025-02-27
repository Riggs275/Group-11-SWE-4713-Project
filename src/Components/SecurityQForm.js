import {useState} from "react";
import {SecurityResetPass} from "./Buttons";
import { checkSecurityQ } from "./api";
import { useNavigate, useLocation } from "react-router-dom";

export function SecurityQForm(){
    const [formData, setFormData] = useState({sec1: "", sec2: "", sec3: ""});
    const navigate = useNavigate();
    const location = useLocation();
    const userID = location.state;
    if(userID != null){
            document.getElementById('welcomeUser').innerText = `Hey ${userID} we found your account answer these security questions to reset your password`
    }

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
        const result = await checkSecurityQ(formData);
        if (result.success){
            navigate('/setpassword', {state: userID})
        }
    };
    return(
        <form onSubmit={handleSubmit}>
            <div>
                <p className={"text"} id="welcomeUser">Hey UserID we found your account answer these security questions to reset your password</p>
                <div className={"TextBoxes"}>
                <label >
                    Security Question 1:
                    <br/>
                    What is your dogs name
                    <br/>
                    <input type="text" name="securityQ1" className={"Box"} value={formData.sec1} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"TextBoxes"}>
                <label>
                    Security Question 2:
                    <br/>
                    What high school did you go to
                    <br/>
                    <input type="text" name="securityQ2" className={"Box"} value={formData.sec2} onChange={handleChange} />
                </label>
                </div>
                <div className={"TextBoxes"}>
                <label>
                    Security Question 3:
                    <br/>
                    Who was your childhood best friend
                    <br/>
                    <input type="text" name="securityQ3" className={"Box"} value={formData.sec3} onChange={handleChange} />
                </label>
                </div>
                <br/>
            </div>
            <SecurityResetPass/>
        
            
        </form>
    );
}
