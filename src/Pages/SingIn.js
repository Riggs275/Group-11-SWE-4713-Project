import {SignInHeader} from '../Components/BackgroundHeader'
import {LoginFom} from "../Components/LoginForm";
import './SignIn.css';
function SignIn(){
    return(
        <SignInHeader>
            <LoginFom/>
        </SignInHeader>
    );
}

export default SignIn;

