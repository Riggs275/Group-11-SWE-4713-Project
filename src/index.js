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
import { ChartOfAccountsPage, JournalEntryPage, LedgerPage } from './Pages/AccountingPages';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
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
                <Route path="/accounts" element={<ChartOfAccountsPage />} />
                <Route path="/journal" element={<JournalEntryPage />} />
                <Route path="/ledger" element={<LedgerPage />} />
                <Route path="*" element={<SignIn/>}/>
            </Routes>
        </BrowserRouter>
    </React.StrictMode> 

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
