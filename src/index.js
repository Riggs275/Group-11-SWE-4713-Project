import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './index.css';
import reportWebVitals from './reportWebVitals';
import CreateAccount from "./Pages/CreateAccount";
import SignIn from "./Pages/SingIn";
import ForgotPass from './Pages/ForgotPassword';
import SecurityQ from './Pages/SecurityQ';
import ResetPassword from './Pages/ResetPassword';
import AdminHomePage from './Pages/AdminHomePage';
import AdminUsers from './Pages/AdminUsers';
import AdminRequestPage from './Pages/AdminRequestPage';
import AddAccount from './Pages/AddAccountAdmin'
import ModifyAccount from './Pages/ModifyAccount';

/*import UserDashboard from "./Pages/UserDashboard";
import UserProfile from "./Pages/UserProfile";
import ChangePassword from "./Pages/ChangePassword";
import ChartOfAccounts from "./Pages/ChartOfAccounts";
import AccountDetails from "./Pages/AccountDetails";
import Ledger from "./Pages/Ledger";
import FinancialReports from "./Pages/FinancialReports";*/

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<SignIn />} />
                <Route path="/createaccount" element={<CreateAccount/>} />
                <Route path="/signin" element={<SignIn/>} />
                <Route path="/forgotpass" element={<ForgotPass/>} />
                <Route path="/securityQ" element={<SecurityQ/>} />
                <Route path="/setpassword" element={<ResetPassword/>}/>
                <Route path="/admin/home" element={<AdminHomePage/>}/>
                <Route path="/admin/users" element={<AdminUsers/>}/>
                <Route path="/admin/requests" element={<AdminRequestPage/>}/>
                <Route path="/admin/addAccountAdmin" element={<AddAccount/>}/>
                <Route path="/admin/modifyAccountAdmin" element={<ModifyAccount/>}/>

                /*<Route path="/userdashboard" element={<UserDashboard/>}/>
                <Route path="/userprofile" element={<UserProfile/>}/>
                <Route path="/changepassword" element={<ChangePassword/>}/>
                <Route path="/chartofaccounts" element={<ChartOfAccounts/>}/>
                <Route path="/accountdetails" element={<AccountDetails/>}/>
                <Route path="/ledger" element={<Ledger/>}/>
                <Route path="/financialreports" element={<FinancialReports/>}/>*/
            </Routes>
        </BrowserRouter>
    </React.StrictMode> 

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
