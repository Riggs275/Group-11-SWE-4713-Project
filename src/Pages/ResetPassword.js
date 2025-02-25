import './ResetPassword.css'
import {ResetPasswordHeader } from '../Components/BackgroundHeader'
import {ResetPassForm } from '../Components/ResetPassForm.js';


function ResetPassword() {
  return (
        <ResetPasswordHeader>
            <ResetPassForm/>
        </ResetPasswordHeader>
  );
}

export default ResetPassword;
