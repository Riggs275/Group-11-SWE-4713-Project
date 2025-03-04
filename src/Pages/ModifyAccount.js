import { AddAccountHeader } from '../Components/BackgroundHeader';
import { ModifyAccountForm } from '../Components/ModifyUserForm';
import './AddAccountAdmin.css'

function ModifyAccount(){
    return(
        <AddAccountHeader>
            <ModifyAccountForm/>
        </AddAccountHeader>            
            
    );
}


export default ModifyAccount;