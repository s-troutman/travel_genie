import { useContext } from "react";
import { Link } from "react-router-dom";
import { NavLink } from 'react-router-dom';
import AuthContext from "../contexts/AuthContext";

function Navigation() {

    const auth = useContext(AuthContext);

    return (
        <main>
        {auth.user ? (
            <nav className="navbar navbar-dark bg-dark mb-2">
                <div className="container">
                    <Link to="/Home" className="navbar-brand"><img id="logo" src="./images/TravelGenieLogo.png" alt="Travel Genie logo" /></Link>
                    <div className="col d-flex justify-content-end">
                        <NavLink to='/Home' className="nav" activeClassName="active" activeStyle={{color: "#ecce32"}} style={{textDecoration: "none", padding: "10px", color: "#fff"}}>Home</NavLink>
                        <NavLink to='/Account' className="nav" activeClassName="active" activeStyle={{color: "#ecce32"}} style={{textDecoration: "none", padding: "10px", color: "#fff"}}>Account</NavLink>
                        <NavLink to='/WishList' className="nav" activeClassName="active" activeStyle={{color: "#ecce32"}} style={{textDecoration: "none", padding: "10px", color: "#fff"}}>Wish List</NavLink>
                        <button className="btn btn-light logout" onClick={() =>auth.logout()}>Log Out</button>
                    </div>
            </div>
        </nav>) : (<div></div>)}
        </main>
    );
}

export default Navigation;
