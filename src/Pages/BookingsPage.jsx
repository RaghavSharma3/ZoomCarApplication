import { Box, Heading,useMediaQuery } from "@chakra-ui/react";
import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import CarCard from "../Components/CarCard";
import { BsArrowLeft } from "react-icons/bs";
import { NavLink } from "react-router-dom";
import jwt_decode from "jwt-decode";
import SmallScreenCarCard from "../Components/SmallScreenCarCard";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import Loader from "../Components/Loader";

const getBookingsData = async () => {
  let token = localStorage.getItem("token");
  var decoded = jwt_decode(token);
  return await axios.get(`http://localhost:9393/api/v1/booking/listBookings/${decoded.sub}`,{
  headers:{
      'Authorization': `Bearer ${token}`
  }});
};

export default function BookingsPage() {
  // const [isSmallerThan950] = useMediaQuery("(max-width: 950px)");
  const nav = useNavigate();
  const [isSmallerThan650] = useMediaQuery("(max-width: 650px)");
  const [carData, setData] = useState([]);

  useEffect(() => {
    fetchAndUpdateCarData();
  }, []);

  const fetchAndUpdateCarData = () => {
    getBookingsData().then((res) => setData(res.data));
  };

  const cancelBooking = async (id) => {
    let token= localStorage.getItem("token");
    return await axios
      .delete(`http://localhost:9393/api/v1/booking/cancelCarBooking/${id}`,{
      headers:{
          'Authorization': `Bearer ${token}`
      }})
      .then(() => {
        fetchAndUpdateCarData();
        Swal.fire({
          icon: "success",
          title: "Booking has been cancelled",
          text: "Thanks for choosing ZoomCar",
        });
        nav("/car");
      });
  };

  return (
    <>
      <Box marginLeft="4%">
        <NavLink to="/car">
          <BsArrowLeft fontSize="30px" />
        </NavLink>
      </Box>
      <Heading m="2" size="md" marginBottom="15px">
        BookingsPage
      </Heading>
      <Box margin="auto">
        <Box
          // maxH={!isSmallerThan650 && "500px"}
          maxH={"83vh"}
          overflow={!isSmallerThan650 && "auto"}
        >
          {!isSmallerThan650
            ? carData.map((car) => (
                <CarCard
                  key={car.id}
                  image={car.carId.image ? car.carId.image : "dummy-image-url"}
                  name={car.carId.name ? car.carId.name : "Renault Duster"}
                  transmission={
                    car.carId.transmission ? car.carId.transmission : "Manual"
                  }
                  fuel={car.carId.fuel ? car.carId.fuel : "Diesel"}
                  seats={car.carId.seats ? car.carId.seats : "5 Seats"}
                  ratings={car.carId.ratings ? car.carId.ratings : "5.00 (8)"}
                  kms={car.carId.kms ? car.carId.kms : "60k kms driven"}
                  address={
                    car.carId.address
                      ? car.carId.address
                      : "7.5 km T Block, 4th T Block East, 4th Block, Jayanagar, Bengaluru, Karnataka 560041, India"
                  }
                  discount_price={
                    car.carId.discount_price ? car.carId.discount_price : "3973"
                  }
                  original_price={
                    car.carId.original_price ? car.carId.original_price : "4775"
                  }
                  isBooked={true}
                  delete_id={car.id}
                  cancelBooking={cancelBooking}
                />
              ))
            : carData.map((car) => (
                <SmallScreenCarCard
                  key={car.id}
                  image={car.carId.image ? car.carId.image : "dummy-image-url"}
                  name={car.carId.name ? car.carId.name : "Renault Duster"}
                  transmission={
                    car.carId.transmission ? car.carId.transmission : "Manual"
                  }
                  fuel={car.carId.fuel ? car.carId.fuel : "Diesel"}
                  seats={car.carId.seats ? car.carId.seats : "5 Seats"}
                  ratings={car.carId.ratings ? car.carId.ratings : "5.00 (8)"}
                  kms={car.carId.kms ? car.carId.kms : "60k kms driven"}
                  address={
                    car.carId.address
                      ? car.carId.address
                      : "7.5 km T Block, 4th T Block East, 4th Block, Jayanagar, Bengaluru, Karnataka 560041, India"
                  }
                  discount_price={
                    car.carId.discount_price ? car.carId.discount_price : "3973"
                  }
                  original_price={
                    car.carId.original_price ? car.carId.original_price : "4775"
                  }
                  isBooked={true}
                  delete_id={car.id}
                  cancelBooking={cancelBooking}
                />
              ))}
          {carData.length === 0 ? <Loader /> : null}
        </Box>
      </Box>
    </>
  );
}
