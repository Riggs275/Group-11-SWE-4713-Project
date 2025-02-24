import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './index.css';
import reportWebVitals from './reportWebVitals';
import CreateAccount from "./Pages/CreateAccount";
import SignIn from "./Pages/SingIn";
import ForgotPass from './Pages/ForgotPassword';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    /*<React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/createAccount" element={<CreateAccount/>} />
                <Route path="/signin" element={<SignIn/>} />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>*/  
    ForgotPass()

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
