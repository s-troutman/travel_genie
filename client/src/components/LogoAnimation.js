import React from "react";
import { gsap } from "gsap";
import "../index.scss";
   
function LogoAnimation() {
  
  const animateGenie = () => {
    gsap.timeline()
        .from("#genie", { opacity: -1, scale: 0 })
        .to("#genie", { duration: 1.5, opacity: 1, scale: 1});
  };

  const animateText = () => {
    gsap.timeline()
        .from("#travelGenieText", { duration: 1.5, x: -600 })
  };
  
  return (
    <div className="flex-row">
      <div className="logo">
        <img id="genie" src="./images/genie.png" alt="genie" />
        <img id="lamp" src="./images/lamp.png" alt="lamp" onMouseOver={animateGenie} />
      </div>
      <p id="animationText">Polish the magical lamp!</p>
      <p className="Introduction"><img id="travelGenieText" src="./images/TravelGenie.png" alt="Travel Genie" onLoad={animateText}/></p>
    </div>
  );
}

export default LogoAnimation;