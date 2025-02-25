import './ForgotPassword.css'
import { ForgotPasswordHeader } from '../Components/BackgroundHeader'
import { ForgotPassForm } from '../Components/ForgotPassForm';
function ForgotPass(){
    return(
        <ForgotPasswordHeader>
            <ForgotPassForm/>
        </ForgotPasswordHeader>
    );
}

export default ForgotPass;