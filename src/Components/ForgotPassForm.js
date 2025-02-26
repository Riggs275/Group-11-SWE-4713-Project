import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {RequestButtonToRequestPage, SignInButton, ResetPassButton} from "./Buttons";
import {forgotAccountRequest } from "./api";
import { useNavigate } from "react-router-dom";

export function ForgotPassForm(){
    const [formData, setFormData] = useState({email: "", userID: ""});
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
        const result = await forgotAccountRequest(formData);
        if (result.success){
            navigate('/securityQ', {state: formData.userID})
        }
    };

    return(
        <form onSubmit={handleSubmit}>
            <div>
                <div className={"TextBoxes"}>
                <label >
                    Email:
                    <br/>
                    <input type="text" name="email" className={"Box"} value={formData.email} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"TextBoxes"}>
                <label>
                    User ID:
                    <br/>
                    <input type="text" name="userID" className={"Box"} value={formData.userID} onChange={handleChange} />
                </label>
                </div>
                <br/>
            </div>
            <ResetPassButton/>
            <OrLines/>    
            <div style={{ display: "flex",justifyContent: "center", gap:"25px"}}>
                <SignInButton/>
                <RequestButtonToRequestPage/>
            </div>
        
            
        </form>
    );
}
