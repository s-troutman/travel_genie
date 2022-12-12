import { Link } from "react-router-dom";
import { gsap } from "gsap";
import { useEffect, useRef} from "react";

function NotFound() {

  const notFound = useRef();

  useEffect(() => {

    let ctx = gsap.context(() => {
        gsap.timeline()
            .from("#notFoundCloud", { opacity: 0, scale: 0 })
            .to("#notFoundCloud", { duration: 2, opacity: 1, scale: 1});
    }, notFound);

    return () => ctx.revert();
  }, notFound);

  return (
    <div className="container">
      <div className="notFound" ref={notFound}>
        <h1 id="notFoundH1">404</h1>
        <img id="notFoundCloud" src="./images/404Cloud.svg" alt="Dark blue cloud"></img>
        <img className="notFoundLamp" src="./images/lamp2.png" alt="lamp" />
        <p className="animate3">
          Click <Link id="hereLink" to="/home">here</Link> to go back home.
        </p>
      </div>
    </div>
  );
}

export default NotFound;