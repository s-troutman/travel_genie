import { useContext } from "react";
import { Link } from "react-router-dom";
import LogoAnimation from "./LogoAnimation";
import AuthContext from "../contexts/AuthContext";

function Welcome() {

  const auth = useContext(AuthContext);

  return (
    <main>
      {!auth.user ? (
        <div className="welcome_page">
          <img id="welcomeDesert" src="/images/welcomeDesert.jpeg " alt="daytime cartoon desert with camel and sun"></img>
          <LogoAnimation />
            <Link className="btn btn-light" to="/registration">Register</Link>
            <Link className="btn btn-dark" to="/login">Login</Link>
        </div> ) : (<div></div>)}
    </main>    
  );
}

export default Welcome;