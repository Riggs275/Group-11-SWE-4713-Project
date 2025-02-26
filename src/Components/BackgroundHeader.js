import { Link } from 'react-router-dom';
import logo from '../imgs/Icon.png'
import NavBar from './NavBar';

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

export function AdminHomeHeader({children}){
    return(
        
        <div className='Home'>
            <NavBar/>
            <div className='PageContent'>
                <p>Current User Info</p>
            </div>
        </div>
    );
}


export function AdminUserHeader({children}){
    return(
        
        <div className='Home'>
            <NavBar/>
            <div className='PageContent'>
                <p>User Tables</p>
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