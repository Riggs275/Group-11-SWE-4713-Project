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



export function RequestButton(){
    return(
        <div className={"buttonContainer"}>
            <Link to={"/createAccount"}>
                <button type="submit" className={"RequestButton"}>Send Request</button>
            </Link>
        </div>
    );
}