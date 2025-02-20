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
                <label>
                    First Name:
                    <br/>
                    <input className={"TextBoxes"} type="text" name="firstName" value={formData.firstName} onChange={handleChange} />
                </label>
                <br/>
                <label >
                    Last Name:
                    <br/>
                    <input type="text" className={"TextBoxes"} name="lastName" value={formData.lastName} onChange={handleChange} />
                </label>
                <br/>
                <label >
                    Date of Birth:
                    <br/>
                    <input type={"date"} className={"TextBoxes"} name={"DOB"} value={formData.DOB} onChange={handleChange}/>
                </label>
                <br/>
            </div>

            <RequestButton/>
            <OrLines/>
            <SignInButtonToSignPage/>
        </form>
    );
}
