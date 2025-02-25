import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {RequestButtonToRequestPage, SignInButton, ResetPassButton} from "./Buttons";

export function ForgotPassForm(){
    const [formData, setFormData] = useState({email: "", userID: ""});

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
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
