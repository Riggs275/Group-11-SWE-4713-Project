import './CreateAccount.css';
import  {TheNameForm} from '../Components/CreateAccountForm.js'
import {CreateAccountHeader} from '../Components/BackgroundHeader'
function CreateAccount() {
  return (
        <CreateAccountHeader>
            <TheNameForm/>
        </CreateAccountHeader>
  );
}

export default CreateAccount;
