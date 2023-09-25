import { useState } from "react";
import { createContext } from "react";
import axios from "axios";
import Swal from "sweetalert2";
export const AuthContext = createContext();

export default function AuthContextProvider({ children }) {
  const [isAuth, setAuthStatus] = useState(false);
  const [token, setToken] = useState(null);
  const [registered, setRegistrationStatus] = useState(false);
  const [location, setLocation] = useState("");

  const loginUser = (userCredential) => {
    axios
      .post("http://ec2-65-2-129-27.ap-south-1.compute.amazonaws.com:9393/api/v1/auth/authenticate", {
        email: userCredential.email,
        password: userCredential.password,
      })
      .then((res) => {
        Swal.fire({
          icon: "success",
          title: "Logged In",
          showConfirmButton: false,
          timer: 2000,
        });
        setAuthStatus(true);
        setToken(res.data.token);
        localStorage.setItem("token", res.data.token);
        let loggedIn = localStorage.getItem("isLoggedIn");
        if (loggedIn === undefined) {
          localStorage.setItem("isLoggedIn", true);
        }
        localStorage.setItem("isLoggedIn", true);
      })
      .catch((err) => {
        if (err.response.status === 403) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Invalid email or password",
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "User is Blocked, Contact Admin",
          });
        }
        console.log(err);
      });
  };

  const registerUser = (userDetails) => {
    axios
      .post("http://ec2-65-2-129-27.ap-south-1.compute.amazonaws.com:9393/api/v1/auth/register", {
        username: userDetails.username,
        email: userDetails.email,
        password: userDetails.password,
      })
      .then(() => {
        Swal.fire({
          icon: "success",
          title: "Registration completed",
          showConfirmButton: false,
          timer: 2000,
        });
        setTimeout(() => {
          setRegistrationStatus(true);
        }, 2000);
        setTimeout(() => {
          setRegistrationStatus(false);
        }, 3000);
      })
      .catch((err) => {
        if (err.response.status === 401) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Email Already Exists, Register using a new Email",
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Registration failed",
          });
        }
        console.log(err);
      });
  };

  const logOutUser = () => {
    setAuthStatus(false);
    setToken(null);
    localStorage.setItem("isLoggedIn", false);
    localStorage.setItem("token", null);
    Swal.fire({
      icon: "success",
      title: "Logged Out",
      showConfirmButton: false,
      text: "Thanks for choosing ZoomCar",
      timer: 2200,
    });
  };

  const changeLocation = (newLocation) => {
    setLocation(newLocation);
  };

  return (
    <AuthContext.Provider
      value={{
        isAuth,
        token,
        loginUser,
        registered,
        registerUser,
        logOutUser,
        location,
        changeLocation,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
