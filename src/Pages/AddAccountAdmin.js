import { AddAccountForm } from '../Components/AddAccountForm';
import { AddAccountHeader } from '../Components/BackgroundHeader';
import './AddAccountAdmin.css'

function AddAccount(){
    return(
        <AddAccountHeader>
            <AddAccountForm/>
        </AddAccountHeader>            
            
    );
}


export default AddAccount;