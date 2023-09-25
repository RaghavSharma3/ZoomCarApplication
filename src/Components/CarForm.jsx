import React, { useState, useEffect } from "react";
import {
  Box,
  FormControl,
  FormLabel,
  Input,
  Radio,
  RadioGroup,
  Select,
  Textarea,
  Button,
} from "@chakra-ui/react";
import axios from "axios";
import { ref, uploadBytes, getDownloadURL, listAll } from "firebase/storage";
import { storage } from "./firebase";
import { v4 } from "uuid";
import Swal from "sweetalert2";
import jwt_decode from "jwt-decode";
import { useNavigate } from "react-router-dom";

const CarForm = () => {
  const [formData, setFormData] = useState({
    image: null,
    name: "",
    transmission: "manual",
    fuel: "Petrol",
    seats: "4",
    carType: "hatchback",
    ratings: "0.00 (0)",
    kms: "",
    address: "",
    discount_price: "",
    original_price: "",
    userId: "",
  });

  const [imageUpload, setImageUpload] = useState(null);
  const [imageUrls, setImageUrls] = useState([]);

  const imagesListRef = ref(storage, "images/");
  const uploadFile = () => {
    if (imageUpload == null) return;
    const imageRef = ref(storage, `images/${imageUpload.name + v4()}`);
    uploadBytes(imageRef, imageUpload).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        setImageUrls((prev) => [...prev, url]);
      });
    });
  };

  useEffect(() => {
    listAll(imagesListRef).then((response) => {
      response.items.forEach((item) => {
        getDownloadURL(item).then((url) => {
          setImageUrls((prev) => [...prev, url]);
        });
      });
    });
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  const lastUrl = imageUrls[imageUrls.length - 1];
  // console.log(lastUrl);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleTransmissionChange = (value) => {
    setFormData({
      ...formData,
      transmission: value,
    });
  };
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    let token = localStorage.getItem("token");
    var decoded = jwt_decode(token);
    e.preventDefault();
    try {
      // Update formData
      formData.discount_price = (formData.original_price * 90) / 100;
      formData.image = lastUrl;
      formData.userId = decoded.sub;

      await axios.post("http://localhost:9393/api/v1/car/addCar", formData,{
      headers:{
          'Authorization': `Bearer ${token}`
      }});
      // console.log(formData);

      // Reset the form
      setFormData({
        name: "",
        transmission: "manual",
        fuel: "petrol",
        seats: "4",
        ratings: 0,
        kms: "",
        address: "",
        discount_price: "",
        original_price: "",
        image: null,
        userId: "",
      });
      Swal.fire({
        icon: "success",
        title: "Your Car has been Hosted Successfully",
        text: "Thanks for choosing ZoomCar",
      });
      navigate("/car");
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <Box
      border="1px solid #fff"
      borderRadius="8px"
      boxShadow="0 0 5px rgba(0, 0, 0, 0.2)"
      p="30px"
      margin="50px 0px"
    >
      <form onSubmit={handleSubmit}>
        <FormControl id="name" isRequired>
          <FormLabel>Car Model</FormLabel>
          <Input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
          />
        </FormControl>

        <FormControl id="transmission" isRequired>
          <FormLabel>Car Type</FormLabel>
          <RadioGroup
            name="transmission"
            value={formData.transmission}
            onChange={handleTransmissionChange}
            flexDirection="column"
            spacing={2}
          >
            <Radio value="Manual">Manual</Radio>{" "}
            <Radio value="Auto">Auto</Radio>
          </RadioGroup>
        </FormControl>

        <FormControl id="fuel" isRequired>
          <FormLabel>Fuel Type</FormLabel>
          <Select name="fuel" value={formData.fuel} onChange={handleChange}>
            <option value="Petrol">Petrol</option>
            <option value="Diesel">Diesel</option>
            <option value="EV">EV</option>
            <option value="CNG">CNG</option>
          </Select>
        </FormControl>

        <FormControl id="car_type" isRequired>
          <FormLabel>Car Type</FormLabel>
          <Select
            name="carType"
            value={formData.carType}
            onChange={handleChange}
          >
            <option value="hatchback">Hatchback</option>
            <option value="sedan">Sedan</option>
            <option value="suv">SUV</option>
            <option value="luxury">Luxury</option>
          </Select>
        </FormControl>

        <FormControl id="seats" isRequired>
          <FormLabel>Number of Seats</FormLabel>
          <Select name="seats" value={formData.seats} onChange={handleChange}>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="7">7</option>
          </Select>
        </FormControl>

        <FormControl id="kms" isRequired>
          <FormLabel>Kms Driven (in thousands)</FormLabel>
          <Input
            type="number"
            name="kms"
            value={formData.kms}
            onChange={handleChange}
          />
        </FormControl>

        <FormControl id="original_price" isRequired>
          <FormLabel>Per Day Charge (in Rupees)</FormLabel>
          <Input
            type="number"
            name="original_price"
            value={formData.original_price}
            onChange={handleChange}
          />
        </FormControl>

        <FormControl id="image" isRequired>
          <FormLabel>Picture of Your Car</FormLabel>
          <input
            type="file"
            onChange={(event) => {
              setImageUpload(event.target.files[0]);
            }}
          />
          <button
            style={{
              border: "1px solid",
              padding: "1.5px 10px",
              backgroundColor: "whitesmoke",
              borderRadius: "2px",
            }}
            onClick={() => {
              uploadFile();
            }}
          >
            {" "}
            Upload Image
          </button>
        </FormControl>

        <FormControl id="address" isRequired>
          <FormLabel>Pickup Address</FormLabel>
          <Textarea
            name="address"
            value={formData.address}
            onChange={handleChange}
          />
        </FormControl>

        <Button
          type="submit"
          colorScheme="teal"
          mt={6}
          bgColor="#52d168"
          color="white"
        >
          Host Car
        </Button>
      </form>
      {/* <img src={lastUrl} /> */}
    </Box>
  );
};

export default CarForm;
