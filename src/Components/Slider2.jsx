import "./Slider2.css";
import { useState } from "react";
import Slider from "react-slick";
import car1 from "../Images/car1.png";
import car3 from "../Images/car3.png";
import car5 from "../Images/car5.png";
import car6 from "../Images/car6.png";
import car7 from "../Images/car7.png";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { Box, Image } from "@chakra-ui/react";
import { Link } from "react-router-dom";
import { ChevronLeftIcon, ChevronRightIcon } from "@chakra-ui/icons";
const images = [car1, car3, car5, car6, car7];

function Slider2() {
  const NextArrow = ({ onClick }) => {
    return (
      <div
        className="arrow next"
        onClick={onClick}
        style={{
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          color: "white",
          padding: "10px",
          borderRadius: "50%",
          cursor: "pointer",
          zIndex: 1,
        }}
      >
        <ChevronRightIcon boxSize={7} />
      </div>
    );
  };

  const PrevArrow = ({ onClick }) => {
    return (
      <div
        className="arrow prev"
        onClick={onClick}
        style={{
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          color: "white",
          padding: "10px",
          borderRadius: "50%",
          cursor: "pointer",
          zIndex: 1,
        }}
      >
        <ChevronLeftIcon boxSize={7} />
      </div>
    );
  };

  const [imageIndex, setImageIndex] = useState(0);

  const settings = {
    infinite: true,
    lazyLoad: true,
    speed: 300,
    slidesToShow: 3,
    centerMode: true,
    centerPadding: 0,
    nextArrow: <NextArrow />,
    prevArrow: <PrevArrow />,
    beforeChange: (current, next) => setImageIndex(next),
  };

  return (
    <Box className="Apppp">
      <Slider {...settings}>
        {images.map((img, idx) => (
          <li key={images.idx}>
            <Box className={idx === imageIndex ? "slide activeSlide" : "slide"}>
              <Link>
                <Image src={img} alt={img} />
              </Link>
            </Box>
          </li>
        ))}
      </Slider>
    </Box>
  );
}

export default Slider2;
