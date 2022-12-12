import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../contexts/AuthContext";
import { gsap } from "gsap";



function WishList() {
 
  const auth = useContext(AuthContext);
  const endpoint = "http://localhost:8080/api/travelgenie/wish";
  const [wishes, setWishes] = useState([]);

  useEffect(() => {
      getwish();
  }, []);

  const getwish = () => {
    
    const init = {
      headers: {
        Authorization: `Bearer ${auth.user.token}`,
      },
    };

    fetch(`${endpoint}/user/${auth.user.userId}`, init)
      .then(response => response.json())
      .then(data => setWishes(data))
      .catch(console.error);

  };

  const handleDeleteWish = (wish_id) => {
    const wish = wishes.find(
      (wish) => wish.wishId === wish_id
    );

    if (
      window.confirm(
        `Delete wish ${wish.cityName} ${wish.countryName}?`
      )
    ) {
      const init = {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${auth.user.token}`
        }
      };

      fetch(`${endpoint}/${wish_id}`, init)
        .then((response) => {
          if (response.status === 204) {
            getwish();
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .catch(console.log);
    }
  };

  const animateGenie3 = () => {
    gsap.timeline()
    .from("#genieForm3", { duration: 1, x: -600 })
  };

  return (
    <main>
      {wishes.length==0 ? <div>
                          <h2 id="homeH2">You don't have any wishes, Master {auth.user.nickname}!</h2>
                          <Link to="/WishForm" className="btn btn-dark">Make a Wish</Link>
                          </div> :
      <div className="container">
      <h2 id="wishListH2">Wish List</h2>
        <Link className="btn addWish" to="/WishForm">
          Add Wish
        </Link>
        <table>
          <thead>
            <tr>
              <th>City</th>
              <th>Country</th>
              <th>Entertainment</th>
              <th>Activity Level</th>
              <th>Price Range</th>
              <th>Kids Friendly</th>
              <th>&nbsp;</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            {wishes.map((wish) => (
              <tr key={wish.wishId}>
                <td>{wish.cityName}</td>
                <td>{wish.countryName}</td>
                <td>{wish.entertainmentName}</td>
                <td>{wish.activityLevel}</td>
                <td>{wish.priceRange}</td>
                <td>{wish.kidFriendly ? "Yes" : "No"}</td>
                <td className="buttonContainer">
                  <Link className="btn btn-primary" to={`/${wish.cityName}`}>
                    Details
                  </Link>
                </td>
                <td className="buttonContainer">
                  <button className="btn btn-danger" onClick={() => handleDeleteWish(wish.wishId)}>
                    Delete
                  </button>
                </td>
              </tr>
           ))}
          </tbody>
       </table>
    </div> }
    <div className="logo">
            <img id="genieForm3" src="./images/genie.png" alt="genie" onLoad={animateGenie3} />
        </div>
  </main>
  );
}

export default WishList;