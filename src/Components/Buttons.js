import {Link} from 'react-router-dom'

//Sends user to the request page
export function RequestButtonToRequestPage(){
    return(
        <div className={"buttonContainer"}>
            <Link to={"/createAccount"}>
                <button className={"RequestButton"}>Send Request</button>
            </Link>
        </div>
    );
}
//Sends user to the sign in page
export function SignInButton(){
    return(
        <div className={"submitContainer"}>
            <Link to={"/signin"}>
                <button className={"signInButton"}>Sign In</button>
            </Link>
        </div>
    );
}
//Button that sends user ID and password
export function SignInPost(){
    return(
        <div className={"submitContainer"}>
            <button type={"submit"} className={"signInButton"}>Sign In</button>
        </div>
    );
}

//Button that sends first name, last name, DOB, email 

export function RequestButton(){
    return(
        <div className={"buttonContainer"}>
            <button type="submit" className={"RequestButton"}>Send Request</button>
        </div>
    );
}
//Sends to forgot password page
export function ForgotPassword(){
    return(
      <div>
            <Link to={"/forgotpass"}>
                <p className={"ForgotPass"}>Forgot Password</p>
            </Link>
      </div>
    );
}

//Button that sends user id and email 
export function ResetPassButton(){
    return(
        <div className={"resetContainer"}>
            <button type={"submit"} className={"resetButton"}>Reset Password</button>
        </div>
    );
}

//Button that sends all 3 security question answers to make sure they are correct 
export function SecurityResetPass(){
    return(
     <div className={"resetContainer"}>
            <button type="submit" className={"RequestButton"}>Reset</button>
        </div>
    );
}

//Button that sends new password 
export function SetPassword(){
    return(
     <div className={"setPassContainer"}>
            <button type="submit" className={"setPassButton"}>Set New Password</button>
    </div>
    );
}