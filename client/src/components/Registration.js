import { useState } from "react";
import { Link, useHistory } from "react-router-dom";

function Registration() {

    const [username, setUsername] = useState("");
    const [nickname, setNickname] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);

    const history = useHistory();

    
    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch("http://localhost:8080/create_account", {
          method: "POST",
          headers: {
          "Content-Type": "application/json",
          },
          body: JSON.stringify({
           username,
           nickname,
           password,
          }),
        });
  
        // This code executes if the request is successful
        if (response.status === 201) {
            const user= await response.json();
            setErrors([]);
            confirm(user);  
        } else if (response.status === 400) {
            const err = await response.json();
            setErrors([...err]);
        } else {
            setErrors(["Unknown error."]);
        }
    };

    const confirm = (user) => {
        if (window.confirm(`Account for ${user.username} has been created, would you like to proceed to log in?`))
                history.push("/login");
        else 
                history.push("/");
    };


    return (
        <form className="entireForm" onSubmit={handleSubmit}>
            <img id="welcomeDesert" src="/images/welcomeDesert.jpeg " alt="daytime cartoon desert with camel and sun"></img>
            <div className="loginHeader">Register</div>
            <div className="login mb-2">
                <label htmlFor="username" className="form-label">Username</label>
                <input type="text" id="username" name="username" className="form-control"
                    value={username} onChange={(event) => setUsername(event.target.value)}></input>
            </div>
            <div className="login mb-2">
                <label htmlFor="nickname" className="form-label">Nickname</label>
                <input type="text" id="nickname" name="password" className="form-control"
                    value={nickname} onChange={(event) => setNickname(event.target.value)}></input>
            </div>
            <div className="login mb-2">
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password" id="password" name="password" className="form-control"
                    value={password} onChange={(event) => setPassword(event.target.value)}></input>
            </div>
            {errors.length !== 0 ? (
                <div className="alert alert-danger">
                    {[...errors]}
                </div> ) : (<div></div>)}
            <div className="loginButtons">
                <button className="btn btn-primary me-2" type="submit">Register</button>
                <Link className="btn btn-danger ml-2" to="/">Cancel</Link>
            </div>
        </form>
    );
}

export default Registration;