import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {ForgotPassword} from "./BackgroundHeader"
import {RequestButtonToRequestPage, SignInButton} from "./Buttons";

export function ForgotPassForm(){
    const [formData, setFormData] = useState({firstName: "", lastName: "", DOB: ""});

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
                    <input type="text" name="firstName" className={"Box"} value={formData.firstName} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div>
                <label className={"TextBoxes"}>
                    Password:
                    <br/>
                    <input type="text" name="lastName" className={"Box"} value={formData.lastName} onChange={handleChange} />
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
