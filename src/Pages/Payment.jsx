import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import axios from "axios";
import {
  Box,
  Heading,
  Flex,
  Spacer,
  Text,
  Image,
  FormControl,
  FormLabel,
  Input,
  FormErrorMessage,
  Button,
} from "@chakra-ui/react";

function Payment() {
  const [upiId, setUpiId] = useState("");
  const [isUpiValid, setIsUpiValid] = useState(true);

  // Function to validate UPI ID
  const validateUpiId = () => {
    const upiPattern = /^[\w.-]+@[a-zA-Z0-9.-]+$/;

    return upiPattern.test(upiId);
  };

  const addToPayment = async (carId) => {
    // get token from ls
    let carPayId = localStorage.getItem("id");
    let carPrice = localStorage.getItem("price");

    let token= localStorage.getItem("token");

    await axios
      .post("http://localhost:9393/api/v1/booking/confirmBooking", {
        bookingId: carPayId,
        amount: carPrice
      },
      {
        headers:{
        'Authorization': `Bearer ${token}`
      }
  })
      .then((res) => {
        console.log("success");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const navigate = useNavigate();
  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();

    // Validate UPI ID
    const isValid = validateUpiId();
    setIsUpiValid(isValid);

    if (isValid) {
      console.log("UPI ID is valid:", upiId);
      addToPayment();
      Swal.fire({
        icon: "success",
        title: "Payment successful",
        text: "Thanks for choosing ZoomCar",
      });
      navigate("/car");
    }
  };

  return (
    <div>
      <Spacer h="120px" />

      <Flex ml="100px" mr="100px">
        <Box w="70%">
          <Heading>Choose payment Method</Heading>
          <Text>Choose the payment method you prefer</Text>
        </Box>
        <Box w="30%">
          <Image
            mt="-18px"
            src="https://adn-static1.nykaa.com/media/wysiwyg/Payments/desktop-icons/payment-icon.svg"
          />
        </Box>
      </Flex>
      <Text ml="100px" fontSize="larger" as="b">
        Payment Method Options
      </Text>
      <Flex ml="100px" mr="100px">
        <Flex>
          <Box
            ml="100px"
            mr="100px"
            w="320px"
            bg="#f3f4f5"
            border="1px"
            borderColor="gray.200"
            borderRadius={5}
          >
            <Box p={1} bg="#f3f4f5">
              <Text noOfLines={1}>Credit/Debit Card</Text>
              <Text color="gray" noOfLines={1}>
                Visa,MasterCard,Rupay and More
              </Text>
            </Box>
            <Box p={1} bg="white">
              <Text noOfLines={1}>UPI</Text>
              <Text color="gray" noOfLines={1}>
                Google Pay, PhonePe, Paytm & more
              </Text>
            </Box>
            <Box p={1} bg="#f3f4f5">
              <Text noOfLines={1}>Cash On Delievery</Text>
              <Text color="gray" noOfLines={1}>
                Pay at your doorstep
              </Text>
            </Box>
            <Box p={1} mb={2} bg="#f3f4f5">
              <Text noOfLines={1}>Gift Card</Text>
              <Text color="gray" noOfLines={1}>
                One card for ZoomCar
              </Text>
            </Box>
          </Box>

          <Box
            p={3}
            w="400px"
            border="1px"
            padding={5}
            borderColor="gray.200"
            borderRadius={5}
          >
            <Heading size="lg">UPI</Heading>
            <Text mt="30px">1.Select UPI App</Text>
            <Flex m="auto" mt="10px">
              <Image
                m="auto"
                src="https://adn-static1.nykaa.com/media/wysiwyg/Payments/UPImWeb/GPay_01.svg"
              />
              <Image
                m="auto"
                src="https://adn-static1.nykaa.com/media/wysiwyg/Payments/UPImWeb/PhonePe.svg"
              />
              <Image
                m="auto"
                src="https://adn-static1.nykaa.com/media/wysiwyg/Payments/UPImWeb/Paytm.svg"
              />
              <Image
                m="auto"
                src="https://adn-static1.nykaa.com/media/wysiwyg/Payments/UPImWeb/UPI-Logo-vector%201.svg"
              />
            </Flex>
            <Flex m="auto" ml="-6px">
              <Text m="auto">Google Pay</Text>
              <Text m="auto">Phone Pay</Text>
              <Text m="auto">Paytm</Text>
              <Text m="auto">Other UPI</Text>
            </Flex>
            <Text mt="30px">2.Enter UPI/VPA Id</Text>

            <form onSubmit={handleSubmit}>
              <FormControl isInvalid={!isUpiValid}>
                <FormLabel>UPI ID</FormLabel>
                <Input
                  mt="10px"
                  placeholder="Enter UPI ID"
                  required
                  size="lg"
                  type="text"
                  value={upiId}
                  onChange={(e) => setUpiId(e.target.value)}
                />
                <FormErrorMessage>
                  {isUpiValid ? "" : "Please enter a valid UPI ID"}
                </FormErrorMessage>
              </FormControl>
              <Button mt={4} type="submit" w="100%" color="white" bg="#e80071">
                {`Verify & Pay Rs.${localStorage.getItem("price")}`}{" "}
              </Button>
            </form>
          </Box>
        </Flex>
      </Flex>
    </div>
  );
}
export default Payment;
