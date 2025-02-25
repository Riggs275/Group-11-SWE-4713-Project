import {useState} from "react";
import { SetPassword } from "./Buttons";
export function ResetPassForm(){
    const [formData, setFormData] = useState({newPassword: "", retype: "", DOB: ""});

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
                <p className="text">Hey UserID type in your new password in the boxes below</p>
                <div className={"CreateTextBox"}>
                <label >
                    New Password:
                    <br/>
                    <input type="text" className={"Box"} name="newPassword" value={formData.newPassword} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"CreateTextBox"}>
                <label>
                    Retype New Password:
                    <br/>
                    <input type="text" className={"Box"} name="retype" value={formData.retype} onChange={handleChange} />
                </label>
                </div>
                <br/>
                <SetPassword/>
            </div>


        </form>
    );
}
