import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {ForgotPassword} from "./Buttons"
import {RequestButtonToRequestPage, SignInButton} from "./Buttons";

export function LoginFom(){
    const [formData, setFormData] = useState({userID: "", password: ""});

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
                    <input type="text" name="password" className={"Box"} value={formData.password} onChange={handleChange} />
                    <ForgotPassword/>
                </label>
                </div>
                <br/>
            </div>
            <SignInButton/>
            <OrLines/>
            <RequestButtonToRequestPage/>
        </form>
    );
}
