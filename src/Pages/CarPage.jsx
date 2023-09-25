import { Box, Flex, Text, useMediaQuery } from "@chakra-ui/react";
import React, { useContext, useEffect, useState } from "react";
import { GoPrimitiveDot } from "react-icons/go";
import { BsArrowRight } from "react-icons/bs";
import SortAndFilters from "../Components/SortAndFilters";
import CarCard from "../Components/CarCard";
import DateTimePicker from "../Components/DateTimePicker";
import axios from "axios";
import jwt_decode from "jwt-decode";
import SmallScreenCarCard from "../Components/SmallScreenCarCard";
import { AuthContext } from "../Context/AuthContextProvider";
import SelectLocation from "./SelectLocation";
import Loader from "../Components/Loader";

const addToBooking = async (carId) => {
  // get token from ls
  let token = localStorage.getItem("token");
  var decoded = jwt_decode(token);
  console.log(decoded.sub);
  await axios.post(
    "http://localhost:9393/api/v1/booking/bookCar",
    {
      carId: { ...carId, userId: decoded.sub },
    },
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    }
    )
    .then((res) => {
      console.log(res.data.id);
      if (res.data.image !== null || res.data.image !== undefined) {
        localStorage.setItem("id", res.data.id);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

export default function CarPage() {
  const [isSmallerThan950] = useMediaQuery("(max-width: 950px)");
  const [isSmallerThan650] = useMediaQuery("(max-width: 650px)");
  const [sortOrFilterApplied, setSortOrFilterApplied] = useState(false);
  const [loading, setLoading] = useState(true);

  // to handle select location modal box
  const { location } = useContext(AuthContext);
  const [openModal, setModalStatus] = useState(false);

  // for storing cars data
  const [carsArray, setCarsArray] = useState([]);

  // function to get cars data
  const getAllCars = async () => {
    let token=localStorage.getItem("token");
    await axios
    .get("http://localhost:9393/api/v1/car/getCars",{params:{
      startDateTime:"2017-01-13T18:09:42.411",
      endDateTime:"2017-01-14T16:09:42.411"
  }
})
      .then((res) => {
        setCarsArray(res.data);
        setLoading(false);
        console.log(res.data);
      })
      .catch((err) => console.log(err));
  };

  // function to display searched car
  const displaySearchedCar = async (carName) => {
    let token=localStorage.getItem("token");
    if (carName !== "") {
      let data = await axios
        .get(`http://localhost:9393/api/v1/car/getCarsByName/${carName}`)
        .then(
          (res) =>
            // update carsArray
            res.data
        )
        .catch((err) => "error");

      if (typeof data === "object") {
        console.log(data);
        let car = [data];
        setCarsArray(car);
      } else {
        setCarsArray([]);
      }
    }
  };

  // function to handle sorted cars
  const handleSortedCars = (sortType) => {
    let sortedCars = [...carsArray];
    if (sortType === "lowToHigh") {
      sortedCars.sort(
        (a, b) => Number(a.discount_price) - Number(b.discount_price)
      );
      // update carsArray
      setCarsArray(sortedCars);
    } else if (sortType === "highToLow") {
      sortedCars.sort(
        (a, b) => Number(b.discount_price) - Number(a.discount_price)
      );
      // update carsArray
      setCarsArray(sortedCars);
    } else if (sortType === "bestRated" || sortType === "popularity") {
      sortedCars.sort(
        (a, b) =>
          Number(b.ratings.split(" ")[0]) - Number(a.ratings.split(" ")[0])
      );
      // update carsArray
      setCarsArray(sortedCars);
    } else if (sortType === "distance") {
      sortedCars.sort(
        (a, b) =>
          Number(a.address.split(" ")[0]) - Number(b.address.split(" ")[0])
      );
      // update carsArray
      setCarsArray(sortedCars);
    } else if (sortType === "kmsDriven" || sortType === "carAge") {
      sortedCars.sort((a, b) => {
        console.log(a);
        console.log(b);
        let d1 = `${a.kms}k kms driven`.split(" ")[0].split("");
        let d11 = [...d1];
        d11.pop();
        d11 = d11.join("");
        d1 = Number(d11);

        let d2 = `${b.kms}k kms driven`.split(" ")[0].split("");
        let d22 = [...d2];
        d22.pop();
        d22 = d22.join("");
        d2 = Number(d22);

        // console.log(d1, d2);
        return d1 - d2;
      });
      // update carsArray
      setCarsArray(sortedCars);
    }
  };

  // function to handle filter car
  const handleFilteredCars = (filteredCarsData) => {
    setCarsArray(filteredCarsData);

    if (filteredCarsData === "") {
      setCarsArray([]);
      getAllCars();
    } else {
      setCarsArray(filteredCarsData);
    }
  };

  const filterCarByAgeUsingSlider = async (carAge) => {
    let token=localStorage.getItem("token");
    console.log(carAge);
    let data = await axios
      .get("http://localhost:9393/api/v1/car/getCars",{params:{
        startDateTime:"2017-01-13T18:09:42.411",
        endDateTime:"2017-01-14T16:09:42.411"
    }
  })
      .then((res) => res.data)
      .catch((err) => "error");

    if (data !== "error") {
      let cars = [];
      data.forEach((c) => {
        let d1 = `${c.kms}k kms driven`.split(" ")[0].split("");
        let d11 = [...d1];
        d11.pop();
        d11 = d11.join("");
        d1 = Number(d11);
        if (d1 <= carAge) {
          cars.push(c);
        }
      });

      // update carsArray
      setCarsArray(cars);
    }
  };

  // function to filter car based on kms run using slider
  const filterCarByKmsUsingSlider = async (kmsRun) => {
    console.log(kmsRun);
    let token=localStorage.getItem("token");
    let data = await axios
      .get("http://localhost:9393/api/v1/car/getCars",{params:{
        startDateTime:"2017-01-13T18:09:42.411",
        endDateTime:"2017-01-14T16:09:42.411"
    }
  })
      .then((res) => res.data)
      .catch((err) => "error");

    if (data !== "error") {
      let cars = [];
      data.forEach((c) => {
        let d1 = `${c.kms}k kms driven`.split(" ")[0].split("");
        let d11 = [...d1];
        d11.pop();
        d11 = d11.join("");
        d1 = Number(d11);
        if (d1 <= kmsRun) {
          cars.push(c);
        }
      });

      // update carsArray
      setCarsArray(cars);
    }
  };

  useEffect(() => {
    getAllCars();
  }, []);

  return (
    <>
      <Flex justifyContent="center" gap="4" py="3" flexWrap="wrap" bg="#f5f5f5">
        {!isSmallerThan950 && (
          <SortAndFilters
            handleFilteredCars={handleFilteredCars}
            handleSortedCars={handleSortedCars}
            displaySearchedCar={displaySearchedCar}
            sortOrFilterApplied={sortOrFilterApplied}
            setSortOrFilterApplied={setSortOrFilterApplied}
            filterCarByAgeUsingSlider={filterCarByAgeUsingSlider}
            filterCarByKmsUsingSlider={filterCarByKmsUsingSlider}
          />
        )}
        {/* car list  */}
        <Box w={!isSmallerThan950 ? "68%" : "95%"}>
          {/* top section */}
          {!isSmallerThan650 && (
            <Flex justifyContent="space-between">
              <Flex
                bg="white"
                w="49%"
                p="1"
                alignItems="center"
                gap="2"
                fontSize="13px"
                borderRadius="0.25rem"
                cursor="pointer"
                onClick={() => {
                  setModalStatus(true);
                }}
              >
                <Box bgGradient="linear(to-r, green.50, green.50)">
                  <GoPrimitiveDot color="teal" />
                </Box>
                <Box>{location !== "" && `${location}, `} </Box>
                <Box>Pick Up City, Airport, Address Or Hotel</Box>
              </Flex>
              <Flex
                bg="white"
                w="49%"
                p="1"
                alignItems="center"
                justifyContent="space-evenly"
                gap="2"
                wrap={"wrap"}
                borderRadius="0.25rem"
                cursor="pointer"
              >
                <Box textAlign="left">
                  <Text fontSize="10px">START DATE/TIME</Text>
                  <Text
                    fontSize={{ sm: "9px", md: "10px", lg: "12px" }}
                    fontWeight="bold"
                  >
                    <DateTimePicker />
                  </Text>
                </Box>
                <Box>
                  <BsArrowRight />
                </Box>
                <Box textAlign="right">
                  <Text fontSize="10px">END DATE/TIME</Text>
                  <Text
                    fontSize={{ sm: "9px", md: "10px", lg: "12px" }}
                    fontWeight="bold"
                  >
                    <DateTimePicker />
                  </Text>
                </Box>
              </Flex>
            </Flex>
          )}
          <br />
          {/* car list data */}
          <Box maxH={"83vh"} overflow={!isSmallerThan650 && "auto"}>
            {!isSmallerThan650
              ? carsArray.map((car) => (
                  <CarCard
                    key={car._id}
                    image={car.image}
                    name={car.name}
                    transmission={car.transmission}
                    fuel={car.fuel}
                    seats={car.seats}
                    ratings={car.ratings}
                    kms={car.kms}
                    address={car.address}
                    discount_price={car.discount_price}
                    original_price={car.original_price}
                    carId={car._id}
                    addToBooking={addToBooking}
                  />
                ))
              : carsArray.map((car) => (
                  <SmallScreenCarCard
                    key={car._id}
                    image={car.image}
                    name={car.name}
                    transmission={car.transmission}
                    fuel={car.fuel}
                    seats={car.seats}
                    ratings={car.ratings}
                    kms={car.kms}
                    address={car.address}
                    discount_price={car.discount_price}
                    original_price={car.original_price}
                    carId={car._id}
                    addToBooking={addToBooking}
                  />
                ))}
            {carsArray.length === 0 ? <Loader loading={loading} /> : null}
          </Box>
        </Box>
      </Flex>
      {<SelectLocation openModal={openModal} setModalStatus={setModalStatus} />}
    </>
  );
}
