import { Link } from 'react-router-dom';
import logo from '../imgs/Icon.png'

export default function NavBar(){
    return(
        <nav className="nav">
            <Link to = {"/admin/home"} className="siteTitle">
            <img src={logo} alt="Logo" className='navLogo'/>
            </Link>
            <ul>
                <li>
                    <Link to={"/admin/users"} className='links'>
                    Users
                    </Link>
                </li>
                <li>
                    <Link to={"/admin/requests"} className='links'>
                    Requests
                    </Link>
                </li>
            </ul>

        </nav>
    );
}