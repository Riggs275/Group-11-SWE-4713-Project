import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {SignInButtonToSignPage} from "./Buttons";
import {RequestButton} from "./Buttons";
import {createAccountRequest } from "./api";
import { useNavigate } from "react-router-dom";

export function TheNameForm(){
    const [formData, setFormData] = useState({firstName: "", lastName: "",email:"", DOB: ""});
    const navigate = useNavigate();
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Form Data:", formData);
        const result = await createAccountRequest(formData);
        if (result.success){
            navigate('/signin')
        }
    };
    return(
        <form onSubmit={handleSubmit}>
            <div>
                <div className={"CreateTextBox"}>
                <label >
                    First Name:
                    <br/>
                    <input type="text" className={"Box"} name="firstName" value={formData.firstName} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"CreateTextBox"}>
                <label>
                    Last Name:
                    <br/>
                    <input type="text" className={"Box"} name="lastName" value={formData.lastName} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                    <div className={"CreateTextBox"}>
                <label>
                    Email:
                    <br/>
                    <input type="email" className={"Box"} name="email" value={formData.email} onChange={handleChange} />
                </label>
                </div>
                    <br/>
                <div className={"CreateTextBox"}>
                <label>
                    Date of Birth:
                    <br/>
                    <input type={"date"} className={"Box"} name={"DOB"} value={formData.DOB} onChange={handleChange}/>
                </label>
                </div>
                <br/>
            </div>

            <RequestButton/>
            <OrLines/>
            <SignInButtonToSignPage/>
        </form>
    );
}
