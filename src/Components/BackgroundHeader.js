import { Link } from 'react-router-dom';
import logo from '../imgs/Icon.png'

export function CreateAccountHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Create Account</header>
                    <ErrorParagraph/>
                    {children}
                </header>
            </div>

        </div>

    );
}

export function SignInHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Sign In</header>
                    <ErrorParagraph/>

                    {children}
                </header>
            </div>

        </div>

    );
}

export function SecurityQHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Security Questions</header>
                    <ErrorParagraph/>

                    {children}
                </header>
            </div>

        </div>

    );
}

export function ResetPasswordHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                
                <header className="App-header">
                    <header className={"Create-Account"}>Reset Password</header>
                    <ErrorParagraph/>
                    {children}
                </header>
            </div>
        </div>

    );
}

export function ForgotPasswordHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                
                <header className="App-header">
                    <header className={"Create-Account"}>Forgot Password</header>
                    <ErrorParagraph/>
                    {children}
                </header>
            </div>
        </div>

    );
}

export function OrLines(){
    return(
        <div className={"Lines"}>
            <br/>
            <hr/><span>Or</span><hr/>
            <br/>
        </div>
    );
}



export function ErrorParagraph(){
    return(
        <p className='errorField'>Error Header</p>
    );
}