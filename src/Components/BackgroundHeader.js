import logo from '../imgs/Icon.png'

export function CreateAccountHeader({children}){
    return(
        <div className="App">
            <div class="Logo">
                <img src={logo} alt="Logo"/>
            </div>

            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Create Account</header>
                    {children}
                </header>
            </div>

        </div>

    );
}

export function SignInHeader({children}){
    return(
        <div className="App">
            <div class="Logo">
                <img src={logo} alt="Logo"/>
            </div>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Sign In</header>
                    {children}
                </header>
            </div>

        </div>

    );
}

export function ForgotPasswordHeader({children}){
    return(
        <div className="App">
            <div class="Logo">
                <img src={logo} alt="Logo" width={250} height={100}/>
            </div>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Forgot Password</header>
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

export function ForgotPassword(){
    return(
      <div>
          <a className={"ForgotPass"} href={"https://www.google.com"}>Forgot Password</a>
      </div>
    );
}
