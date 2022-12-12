import { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { gsap } from "gsap";

function WishForm() {

    const WISH_DEFAULT = {
        scenery: "METROPOLITAN",
        activityLevel: "LOW",
        priceRange: "$",
        kidFriendly: true
    };

    const auth = useContext(AuthContext);
    const endpoint = `http://localhost:8080/api/travelgenie/wish/match`;
    const [errs, setErrs] = useState({});
    const [matches, setMatches] = useState([]);
    const [submitted, setSubmitted] = useState(false);
    const [wish, setWish] = useState({...WISH_DEFAULT});
    const [wishScenery, setWishScenery] = useState(wish.scenery);
    const [wishActivityLevel, setWishActivityLevel] = useState(wish.activityLevel);
    const [wishPriceRange, setWishPriceRange] = useState(wish.priceRange);
    
    const history = useHistory();
        
    const getMatches = () => {
        
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(wish),
        };

        fetch(endpoint, init)
          .then(res => res.json())
          .then(data => setMatches(data))
          .catch((error) => console.log(error));
    };

    function handleChange(evt) {
        const nextChoice = { ...wish };
        
        if (evt.target.name === "scenery") {
            setWishScenery(evt.target.value);
            nextChoice.scenery = evt.target.value;
        }

        if (evt.target.name === "activity-level") {
            setWishActivityLevel(evt.target.value);
            nextChoice.activityLevel = evt.target.value;
        }

        if (evt.target.name === "price-range") {
            setWishPriceRange(evt.target.value);
            nextChoice.priceRange = evt.target.value;
        }

        if (evt.target.name === "kidFriendly") {
            nextChoice.kidFriendly = evt.target.checked;
        }

        setWish(nextChoice);
    }

    function handleSubmit(evt) {
        evt.preventDefault();
    
        setSubmitted(true);
            
        getMatches();
    }

    const handleAddWish = async (wish) => {

        wish.appUserId = auth.user.userId;
        
        if (window.confirm("Add this wish to your wish list?")) {
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
                } else {
                    setErrs(data);
                }
            })
            .catch((error) => console.log(error));
        }
        else{
            history.push("/Home");
        }
    };

    const resetState = () => {
        setWish(WISH_DEFAULT);
        setWishScenery(WISH_DEFAULT.scenery);
        setWishActivityLevel(WISH_DEFAULT.activityLevel);
        setWishPriceRange(WISH_DEFAULT.priceRange);
        setErrs([]);
        setSubmitted(false);
    };

    const animateGenie = () => {
        gsap.timeline()
        .from("#genieForm", { duration: 1, x: -600 })
      };

      const animateGenie2 = () => {
        gsap.timeline()
        .from("#genieForm2", { opacity: -1, scale: 0 })
        .to("#genieForm2", { duration: 1.5, opacity: 1, scale: 1});
      };
      
    return (
        <main>
        {!submitted ?
            <form onSubmit={handleSubmit}>
                <div className="container">
                <h2>What is your wish?</h2>
                <div className="form-group">
                    <div className="scenery-select mb-5 mt-4">
                        <h3 className="wishFormLabel">Scenery</h3>
                        <input type="radio" id="scenery" name="scenery" value="METROPOLITAN" checked={wishScenery==='METROPOLITAN'} onChange={handleChange}></input>
                        <label htmlFor="scenery">Metropolitan</label>
                        <input type="radio" id="scenery" name="scenery" value="BEACH" checked={wishScenery==='BEACH'} onChange={handleChange}></input>
                        <label htmlFor="scenery">Beach</label>
                        <input type="radio" id="scenery" name="scenery" value="MOUNTAIN" checked={wishScenery==='MOUNTAIN'} onChange={handleChange}></input>
                        <label htmlFor="scenery">Mountains</label>
                        <input type="radio" id="scenery" name="scenery" value="DESERT" checked={wishScenery==='DESERT'} onChange={handleChange}></input>
                        <label htmlFor="scenery">Desert</label>
                        <input type="radio" id="scenery" name="scenery" value="SNOW" checked={wishScenery==='SNOW'} onChange={handleChange}></input>
                        <label htmlFor="scenery">Snow</label>
                    </div>

                    <div className="activity-level-select mb-5">
                        <h3>Activity Level</h3>
                        <input type="radio" id="activity-level" name="activity-level" value="LOW" checked={wishActivityLevel==='LOW'} onChange={handleChange}></input>
                        <label htmlFor="activity-level">Relaxed</label>
                        <input type="radio" id="activity-level" name="activity-level" value="MEDIUM" checked={wishActivityLevel==='MEDIUM'} onChange={handleChange}></input>
                        <label htmlFor="activity-level">Take some effort</label>
                        <input type="radio" id="activity-level" name="activity-level" value="HIGH" checked={wishActivityLevel==='HIGH'} onChange={handleChange}></input>                    
                        <label htmlFor="activity-level">Sweat it out</label>
                    </div>
                    

                    <div className="price-range-select mb-5">
                        <h3>Price Range</h3>
                        <input type="radio" id="price-range" name="price-range" value="$" checked={wishPriceRange==='$'} onChange={handleChange}></input>
                        <label htmlFor="price-range">$</label>
                        <input type="radio" id="price-range" name="price-range" value="$$" checked={wishPriceRange==='$$'} onChange={handleChange}></input>
                        <label htmlFor="price-range">$$</label>
                        <input type="radio" id="price-range" name="price-range" value="$$$" checked={wishPriceRange==='$$$'} onChange={handleChange}></input>                    
                        <label htmlFor="price-range">$$$</label>
                    </div>

                    <div className="kidFriendly-select mb-5">  
                        <label htmlFor="kidFriendly">Kid Friendly?</label>
                        <input type="checkbox" id="kidFriendly" name="kidFriendly" checked={wish.kidFriendly} onChange={handleChange}></input>
                    </div>
                    <div>
                    <button type="submit" className="btn btn-primary">Find Destination</button>
                    <Link to="/Home" className="btn btn-danger ml-2">Cancel</Link>
                    </div>
                </div>
                </div>
                <div className="logo">
            <img id="genieForm" src="./images/genie.png" alt="genie" onLoad={animateGenie} />
        </div>
            </form> 
            
            : 

            <div className="search-result container">  
                {matches.length === 0 ? (<div className="no-result">
                                       <h3>Sorry, Master {auth.user.nickname}!</h3>
                                       <p className="sorry"> I have failed to find a suitable destination.</p>
                                       <p className="sorry"> Please try again!</p>
                                       <button onClick={resetState} className="btn btn-dark">Make a Wish</button></div>) 
                    :
                    (<div className="yes-result">
                        <h2>Master {auth.user.nickname}, here is what I found ...</h2>
                        <table>
                            <thead>
                                <tr>
                                <th>City</th>
                                <th>Country</th>
                                <th>Scenery</th>
                                <th>Entertainment</th>
                                <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                            {matches.map((wish) => (
                                <tr key={matches.indexOf(wish)}>
                                    <td>{wish.cityName}</td>
                                    <td>{wish.countryName}</td>
                                    <td>{wish.scenery}</td>
                                    <td>{wish.entertainmentName}</td>
                                    <td className="buttonContainer">
                                        <button className="btn btn-warning" onClick={() => handleAddWish(wish)}>
                                            Add
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        
                        <button onClick={resetState} className="btn btn-dark">Start Over</button>
                    </div>)
                }
                <div className="logo">
            <img id="genieForm2" src="./images/genie.png" alt="genie" onLoad={animateGenie2} />
        </div>
            </div>}
        </main> 
    );
}

export default WishForm;