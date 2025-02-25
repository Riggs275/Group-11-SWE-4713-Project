import {Link} from 'react-router-dom'

export function SignInButtonToSignPage(){
    return(
        <div className={"submitContainer"}>
            <Link to={"/signin"}>
            <button type={"submit"} className={"signInButton"}>Sign In</button>
            </Link>
        </div>
    );
}

export function RequestButtonToRequestPage(){
    return(
        <div className={"buttonContainer"}>
            <Link to={"/createAccount"}>
                <button type="submit" className={"RequestButton"}>Send Request</button>
            </Link>
        </div>
    );
}

export function SignInButton(){
    return(
        <div className={"submitContainer"}>
            <Link to={"/signin"}>
                <button type={"submit"} className={"signInButton"}>Sign In</button>
            </Link>
        </div>
    );
}

export function ResetPassButton(){
    return(
        <div className={"resetContainer"}>
            <Link to={"/securityQ"}>
                <button type={"submit"} className={"resetButton"}>Reset Password</button>
            </Link>
        </div>
    );
}

export function RequestButton(){
    return(
        <div className={"buttonContainer"}>
            <Link to={"/createAccount"}>
                <button type="submit" className={"RequestButton"}>Send Request</button>
            </Link>
        </div>
    );
}

export function ForgotPassword(){
    return(
      <div>
            <Link to={"/forgotpass"}>
                <p className={"ForgotPass"}>Forgot Password</p>
            </Link>
      </div>
    );
}

export function SecurityResetPass(){
    return(
     <div className={"resetContainer"}>
            <Link to={"/setpassword"}>
                <button type="submit" className={"RequestButton"}>Reset</button>
            </Link>
        </div>
    );
}


export function SetPassword(){
    return(
     <div className={"setPassContainer"}>
            <Link to={"/signin"}>
                <button type="submit" className={"setPassButton"}>Set New Password</button>
            </Link>
        </div>
    );
}