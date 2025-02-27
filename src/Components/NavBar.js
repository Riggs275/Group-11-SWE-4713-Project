import { Link } from 'react-router-dom';
import logo from '../imgs/Icon.png'

export default function NavBar(){
    return(
        <nav className="nav">
            <Link to = {"/adminhome"} className="siteTitle">
            <img src={logo} alt="Logo" className='navLogo'/>
            </Link>
            <ul>
                <li>
                    <Link to={"/adminusers"} className='links'>
                    Users
                    </Link>
                </li>
                <li>
                    <Link to={"/adminrequests"} className='links'>
                    Requests
                    </Link>
                </li>
            </ul>

        </nav>
    );
}