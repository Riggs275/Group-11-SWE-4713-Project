import { useState, useEffect } from 'react';

import { Link, useLocation } from 'react-router-dom';
import logo from '../imgs/Icon.png'
import NavBar from './NavBar';
import { RequestTable, UserTable } from './Tables';
import { getRequestedUsersTable, getUsersTable } from './api';
import pfp from '../imgs/stock.jpg'

export function CreateAccountHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Create Account</header>
                    {children}
                </header>
            </div>

        </div>

    );
}

export function SignInHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Sign In</header>

                    {children}
                </header>
            </div>

        </div>

    );
}

export function SecurityQHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                <header className="App-header">
                    <header className={"Create-Account"}>Security Questions</header>
                    <ErrorParagraph/>

                    {children}
                </header>
            </div>

        </div>

    );
}

export function ResetPasswordHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                
                <header className="App-header">
                    <header className={"Create-Account"}>Reset Password</header>
                    {children}
                </header>
            </div>
        </div>

    );
}

export function ForgotPasswordHeader({children}){
    return(
        <div className="App">
            <Link to={"/signin"}>
            <img src={logo} alt="Logo" className='Logo'/>
            </Link>
            <div className={"DivApp-Header"}>
                
                <header className="App-header">
                    <header className={"Create-Account"}>Forgot Password</header>
                    {children}
                </header>
            </div>
        </div>

    );
}

export function AdminHomeHeader(){
    const userID = localStorage.getItem("userID");
    const userType = localStorage.getItem("userType");
    const firstName = localStorage.getItem("firstName");
    const lastName = localStorage.getItem("lastName");
    console.log(localStorage)
    return(
        
        <div className='Home'>
            <NavBar/>
            <div className='PageContent'>
                <h1>{userID}</h1>
                <h2>{userType}</h2>
                <p>{firstName} {lastName}</p>
            </div>
        </div>
    );
}

export function AdminRequestHeader(){
    const location= useLocation();
    const [requestData, setUserData] = useState([]); // Store fetched user data
    const userID = location.state?.userID || localStorage.getItem("userID"); // Get from state or localStorage
    console.log(userID);
    useEffect(() => {
        if (!userID) {
            console.error("No userID found!");
            return;
        }

        const fetchUserData = async () => {
            try {
                const data = await getRequestedUsersTable({"makerID": userID }); // Call API function
                console.log("Data: " + data.data);
                setUserData(data.data); // Update state with fetched user data
            } catch (error) {
                console.error("Error fetching user data:", error);
            }
        };

        fetchUserData(); // Invoke the function

    }, [userID]); // Re-run when `userID` changes

    return(
        <div className='Home'>
            <NavBar/>
            <div className='PageContent'>
                <RequestTable userData={requestData}/>
            </div>
           

        </div>
    );
}
//<UserPicture username={"Adam S"} userType={"Admin"}/>



export function AdminUserHeader() {
    const location= useLocation();
    const [userData, setUserData] = useState([]); // Store fetched user data
    const userID = location.state?.userID || localStorage.getItem("userID"); // Get from state or localStorage

    useEffect(() => {
        if (!userID) {
            console.error("No userID found!");
            return;
        }

        const fetchUserData = async () => {
            try {
                const data = await getUsersTable({ "makerID": userID }); // Call API function
                setUserData(data.data); // Update state with fetched user data
            } catch (error) {
                console.error("Error fetching user data:", error);
            }
        };

        fetchUserData(); // Invoke the function

    }, [userID]); // Re-run when `userID` changes

    return (
        <div className='Home'>
            <NavBar />
            <div className='PageContent'>
                <UserTable userData={userData} /> {/* Pass fetched user data */}
            </div>
        </div>
    );
}


//<UserPicture username={"Adam S"} userType={"Admin"}/>

export function AddAccountHeader({children}){
    return(
        <div className='Home'>
            <NavBar/>
            <div className='PageContent'>
                {children}
            </div>

        </div>
    );
}

export function OrLines(){
    return(
        <div className={"Lines"}>
            <br/>
            <hr/><span>Or</span><hr/>
            <br/>
        </div>
    );
}



export function ErrorParagraph(){
    return(
        <p className='errorField'>Error Header</p>
    );
}

export function UserPicture({username, userType}){
    return(
        <div className='pfpDiv'>
            <img src={pfp} alt="pfp" className='pfp'/>
            <p>{username}</p>
            <p>{userType}</p>
        </div>
    );
}