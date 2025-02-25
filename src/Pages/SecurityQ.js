import './SecurityQ.css'
import {SecurityQHeader } from '../Components/BackgroundHeader'
import {SecurityQForm } from '../Components/SecurityQForm';

function SecurityQ(){
    return(
        <SecurityQHeader>
            <SecurityQForm/>
        </SecurityQHeader>
    );
}

export default SecurityQ;