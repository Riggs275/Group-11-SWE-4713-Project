import {useState} from "react";
import {OrLines} from "./BackgroundHeader";
import {SignInButtonToSignPage} from "./Buttons";
import {RequestButton} from "./Buttons";

export function TheNameForm(){
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
