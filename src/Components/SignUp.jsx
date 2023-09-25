import { Button, Input, Text } from "@chakra-ui/react";
import React, { useContext, useEffect, useReducer } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../Context/AuthContextProvider";

const initialState = {
  username: "",
  email: "",
  password: "",
};

// actions
const usernameAction = (username) => {
  return {
    type: "username",
    payload: username,
  };
};
const emailAction = (email) => {
  return {
    type: "email",
    payload: email,
  };
};
const passwordAction = (password) => {
  return {
    type: "password",
    payload: password,
  };
};

// reducer function
const reducer = (state, action) => {
  switch (action.type) {
    case "username":
      return { ...state, username: action.payload };
    case "email":
      return { ...state, email: action.payload };
    case "password":
      return { ...state, password: action.payload };
    default:
      return state;
  }
};

export default function Register() {
  const { registered, registerUser } = useContext(AuthContext);
  const [state, dispatch] = useReducer(reducer, initialState);
  const navigate = useNavigate();

  useEffect(() => {
    if (registered) {
      navigate("/login");
    }
  });

  return (
    <div>
      <Text fontSize="lg" fontWeight="bold" color="red.500" mb="3">
        Sign-Up
      </Text>
      <form
        style={{ width: "70%", margin: "auto", marginBottom: "5px" }}
        onSubmit={(e) => {
          e.preventDefault();
          registerUser(state);
        }}
      >
        <Input
          required
          py="5"
          size="sm"
          placeholder="Username"
          type="username"
          value={state.username}
          onChange={(e) => dispatch(usernameAction(e.target.value))}
          marginBottom="3%"
          borderRadius="0.5rem"
        />
        <Input
          required
          py="5"
          size="sm"
          placeholder="Email Address"
          type="email"
          value={state.email}
          onChange={(e) => dispatch(emailAction(e.target.value))}
          borderRadius="0.5rem"
        />
        <Input
          required
          py="5"
          size="sm"
          placeholder="Password"
          type="password"
          value={state.password}
          onChange={(e) => dispatch(passwordAction(e.target.value))}
          borderRadius="0.5rem"
          marginBottom="3%"
          marginTop="3%"
        />
        <Button
          size="sm"
          mt="2"
          backgroundColor="teal"
          _hover={{ background: "red.500", color: "white" }}
          type="submit"
        >
          Submit
        </Button>
      </form>
      <small>
        Already Registered ?,
        <Link to="/login">
          <u style={{ color: "blue" }}>Login Here</u>
        </Link>
      </small>
    </div>
  );
}
