import { Box, Button, Flex, Img } from "@chakra-ui/react";
import { NavLink } from "react-router-dom";
import React, { useState } from "react";
import { useEffect } from "react";

const links = [
  {
    path: "/",
    title: "Request Demo",
  },
];

function ZmsNavbar() {
  const [colorChange, setColorchange] = useState(false);
  const changeNavbarColor = () => {
    if (window.scrollY >= 2 && window.scrollY <= 720) {
      setColorchange(true);
    } else {
      setColorchange(false);
    }
  };
  useEffect(() => {
    changeNavbarColor();
    window.addEventListener("scroll", changeNavbarColor);
  });

  const [show, setShow] = useState(true);
  const controlNavbar = () => {
    if (window.scrollY > 720) {
      setShow(false);
    } else {
      setShow(true);
    }
  };

  useEffect(() => {
    window.addEventListener("scroll", controlNavbar);
    return () => {
      window.removeEventListener("scroll", controlNavbar);
    };
  }, []);

  return (
    <nav>
      <Box className={show ? "nav" : "nav__show"}>
        <Box
          className={colorChange ? "Navbar colorChange" : "Navbar "}
          width="100%"
          margin="auto"
        >
          <Box w="100%" height="60px">
            <Flex
              w="60%"
              margin="auto"
              alignItems="center"
              justifyContent="space-around"
            >
              <NavLink to="/">
                <Box marginTop="10px">
                  <Img
                    boxSize="65%"
                    src="https://www.zoomcar.com/build/98e56e8b0b91e8806885a22ac2bf69a7.png"
                  />
                </Box>
              </NavLink>

              <Box marginTop="10px">
                {links.map((link) => (
                  <NavLink key={link.path} to={link.path}>
                    <Button
                      color="red"
                      className="NavbarHover"
                      fontSize="1.1vw"
                    >
                      {link.title}
                    </Button>
                  </NavLink>
                ))}
              </Box>
            </Flex>
          </Box>
        </Box>
      </Box>
    </nav>
  );
}

export default ZmsNavbar;
