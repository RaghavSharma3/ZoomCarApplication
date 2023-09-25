import { Box, Img, Text } from "@chakra-ui/react";
import React from "react";
import SignUp from "../Components/SignUp";
import { NavLink } from "react-router-dom";
import { BsArrowLeft } from "react-icons/bs";

export default function SignUpPage() {
  return (
    <>
      <div style={{ marginBottom: "100px" }}>
        <Box
          w={{ sm: "60%", md: "45%", lg: "30%" }}
          h="600px"
          margin="auto"
          marginTop="20px"
        >
          <NavLink to="/login">
            <BsArrowLeft fontSize="30px" />
          </NavLink>
          <Box w="100%" margin="auto">
            <Img
              boxSize="full"
              src="https://www.zoomcar.com/build/fb65fcc43b8bededb813e093ea2d47d3.svg"
              alt="logo"
            />
          </Box>

          <Box w="100%" textAlign="start" padding="10px">
            <Text fontSize={{ sm: "13px", md: "17px" }} fontWeight="700">
              Enter Details To Sign Up{" "}
            </Text>
          </Box>
          <SignUp />
        </Box>
      </div>
    </>
  );
}
