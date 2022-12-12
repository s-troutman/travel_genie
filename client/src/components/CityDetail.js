import { useState, useEffect, useContext } from "react";
import { Link, useLocation, useHistory} from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import NotFound from "./NotFound";

function CityDetail() {

    const auth = useContext(AuthContext);
    const location = useLocation();
    const cityName = location.pathname.substring(1);

    const history = useHistory();

    const endpoint = "http://localhost:8080/api/travelgenie/entertainment/city";
    const endpoint2 = "http://localhost:8080/api/travelgenie/city";
    
    const [entertainments, setEntertainments] = useState([]);
    const [city, setCity] = useState([]);

    useEffect(() => {
      getCity();  
      getEntertainments();
    }, []);
    
    const getEntertainments = () => {
    
        const init = {
         headers: {
            Authorization: `Bearer ${auth.user.token}`,
            },
        };

        fetch(`${endpoint}/${cityName}`, init)
        .then(response => response.json())
        .then(data => setEntertainments(data))
        .catch(console.error);
    };
    
    const getCity = () => {
    
        const init = {
        headers: {
            Authorization: `Bearer ${auth.user.token}`,
        },
        };

        fetch(`${endpoint2}/${cityName}`, init)
        .then((response) => {
            if (response.status === 200) {              
              return response.json();
            } else {
              return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then((data) => {
            if (data.cityId) {
                setCity(data);
            }
        })
        .catch(console.error);
    };

    const createWish = (newCityName, 
                        newCountryName, 
                        newScenery, 
                        newEntertainmentName, 
                        newActivityLevel, 
                        newPriceRange, 
                        newKidFriendly) => {

                            const wish = {
                                wishId: 0,
                                appUserId: 0,
                                cityName: newCityName,
                                countryName: newCountryName,
                                scenery: newScenery,
                                entertainmentName: newEntertainmentName,
                                activityLevel: newActivityLevel,
                                priceRange: newPriceRange,
                                kidFriendly: newKidFriendly, 
                            }

                            handleAddWish(wish);
                        }

    const handleAddWish = async (wish) => {

        wish.appUserId = auth.user.userId;
        
        if (window.confirm("Add this entertainment to your wish list?")) {
            const init = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${auth.user.token}`
                },
                body: JSON.stringify(wish),
            };
        
            fetch("http://localhost:8080/api/travelgenie/wish", init)
            .then((response) => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then((data) => {
                if (data.wishId) {
                    history.push("/WishList");
                }
            })
            .catch((error) => console.log(error));
        }
        else{
            history.push(`/${cityName}`);
        }
    };
                    
    
    return (
        <main>
            {city.length==0 ? <NotFound /> : 
                <div className="container">
                    <img id="cityImage" src={`./images/${city.cityId}.png`} alt="genie" />
                    <h2 id="wishListH2">{city.cityName}, {city.countryName}</h2>
                    <h5>Things To Do</h5>
                    <table>
                        <thead>
                            <tr>
                                <th>Entertainment Name</th>
                                <th>Activity Level</th>
                                <th>Price Range</th>
                                <th>Kid Friendly</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            {entertainments.map((entertainment) => (
                                <tr key={entertainment.entertainmentId}>
                                    <td>{entertainment.entertainmentName}</td>
                                    <td>{entertainment.activityLevel}</td>
                                    <td>{entertainment.priceRange}</td>
                                    <td>{entertainment.kidFriendly ? "Yes" : "No"}</td>
                                    <td className="buttonContainer">
                                        <button className="btn btn-danger" onClick={() => 
                                            createWish(city.cityName, 
                                                      city.countryName, 
                                                      city.scenery, 
                                                      entertainment.entertainmentName, 
                                                      entertainment.activityLevel, 
                                                      entertainment.priceRange, 
                                                      entertainment.kidFriendly)}>
                                            Add
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    <Link className="btn btn-primary" to="/WishList">
                        Back to Wish List
                    </Link>
                </div>
            }
        </main>
    );
}

export default CityDetail;