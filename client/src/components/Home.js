import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { gsap } from "gsap";

function Home() {

    const auth = useContext(AuthContext);

    const animateGenie = () => {
        gsap.timeline()
        .from("#genieHome", { opacity: -1, scale: 0 })
        .to("#genieHome", { duration: 5, opacity: 1, scale: 1});
      };

      const animateHomeCloud = () => {
        gsap.timeline()
            .from("#homeCloud", { duration: .5, y: 600 })
      };

      const animateHomeCloud2 = () => {
        gsap.timeline()
            .from("#homeCloud2", { duration: .5, y: 600 })
      };

      const animateText = () => {
        gsap.timeline()
            .to("#homeHover", {opacity: 1, scale: 1})
      };

      const animateText2 = () => {
        gsap.timeline()
            .to("#homeHover", { opacity: 0, scale: 0 });
      };

    return (
        <main>
            <h2 id="homeH2">Greetings Master {auth.user.nickname}!</h2>
            <Link onMouseOver={animateText} onMouseLeave={animateText2} to="/WishForm" className="btn btn-dark">Make a Wish</Link>
            <div>
            <h2 id="homeHover">Your wish is my command!</h2>
            <img id="homeCloud" src="./images/404Cloud.svg" alt="Dark blue cloud" onLoad={animateHomeCloud}></img>
            </div>
            <div>
            <img id="homeCloud2" src="./images/404Cloud.svg" alt="Dark blue cloud" onLoad={animateHomeCloud2}></img>
            </div>
        <div className="logo">
            <img id="genieHome" src="./images/genie.png" alt="genie" onLoad={animateGenie} />
        </div>
        </main>
    );
}

export default Home;