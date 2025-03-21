import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {ForgotPassword, SignInPost} from "./Buttons"
import {RequestButtonToRequestPage} from "./Buttons";
import { loginUserRequest } from "./api";
import { useNavigate } from "react-router-dom";

export function LoginFom(){
    const [formData, setFormData] = useState({userID: "", password: ""});
    const navigate = useNavigate();
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };
    const handleSubmit =  async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
        const result = await loginUserRequest(formData);
        //Should move them to the admin or accountant page but not coded yet
        if(result.success){
            localStorage.setItem("userID", formData.userID)
            localStorage.setItem("userType", result.userType)
            navigate('/adminusers', {state: {userID: formData.userID}})
        }
    };
    return(
        <form onSubmit={handleSubmit}>
            <div>
                <div className={"TextBoxes"}>
                <label >
                    User ID:
                    <br/>
                    <input type="text" name="userID" className={"Box"} value={formData.userID} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"TextBoxes"}>
                    <label>
                    Password:
                    <br/>
                    <input type="password" name="password" className={"Box"} value={formData.password} onChange={handleChange} />
                    <ForgotPassword/>
                </label>
                </div>
                <br/>
            </div>
            <SignInPost/>
            <OrLines/>
            <RequestButtonToRequestPage/>
        </form>
    );
}
