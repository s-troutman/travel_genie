import { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom";
import jwtDecode from "jwt-decode";
import Welcome from "./components/Welcome";
import Navigation from "./components/Navigation";
import Login from "./components/Login";
import Registration from "./components/Registration";
import Home from "./components/Home";
import Account from "./components/Account";
import WishList from "./components/WishList";
import WishForm from "./components/WishForm";
import CityDetail from "./components/CityDetail";
import NotFound from "./components/NotFound";
import AuthContext from "./contexts/AuthContext";


const LOCAL_STORAGE_TOKEN_KEY = "travelGenieToken";

function App() {

  const [user, setUser] = useState(null);

  const [restoreLoginAttemptCompleted, setRestoreLoginAttemptCompleted] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
    if (token) {
      login(token);
    }
    setRestoreLoginAttemptCompleted(true);
  }, []);

  const login = (token) => {
    localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);


    const { sub: nickname, app_user_id: userId, authorities: authoritiesString } = jwtDecode(token);
      
    const roles = authoritiesString.split(',');
      
    const user = {
      nickname,
      userId,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      }
    };

    console.log(user);

    setUser(user);

    return user;

  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  }

  const auth = {
    user: user ? {...user} : null,
    login,
    logout
  }

  if (!restoreLoginAttemptCompleted) {
    return null;
  }

  return (
    <AuthContext.Provider value={auth}>
      <Router>
        <Navigation />

          <Switch>
            <Route exact path="/">
              {!user ? <Welcome /> : <Redirect to="/home" />}
            </Route>
            
            <Route exact path="/login">
              {!user ? <Login /> : <Redirect to="/home" />}
            </Route>

            <Route exact path="/registration">
              {!user ? <Registration /> : <Redirect to="/home" />}
            </Route>
            
            <Route exact path="/home">
              {user ? <Home /> : <Redirect to="/" />}
            </Route>

            <Route exact path="/Account">
              {user ? <Account /> : <Redirect to="/" />}
            </Route>

            <Route exact path="/wishlist">
              {user ? <WishList /> : <Redirect to="/" />}
            </Route>

            <Route exact path="/WishForm">
              {user ? <WishForm /> : <Redirect to="/" />}
            </Route>

            <Route exact path="/:handle">
              {user ? <CityDetail /> : <Redirect to="/" />}
            </Route>

            <Route path="/404">
              {user ? <NotFound /> : <Redirect to="/" />}
            </Route>

            <Route path="*">
              {user ? <NotFound /> : <Redirect to="/" />}
            </Route>
          
          </Switch>
        
      </Router>
    </AuthContext.Provider>
  );
}

export default App;