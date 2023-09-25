import React, { useState, useEffect } from "react";
import { Box, Button, Flex, Image, Stack } from "@chakra-ui/react";
import { ChevronLeftIcon, ChevronRightIcon } from "@chakra-ui/icons";
import image1 from "../Images/hostimage1.jpg";
import image2 from "../Images/hostimage2.png";
import image3 from "../Images/hostimage4.png";
import image4 from "../Images/hostimage3.png";

const images = [image1, image2, image3, image4];

const Carousel = () => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const [transition, setTransition] = useState("next");

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) =>
        prevIndex === images.length - 1 ? 0 : prevIndex + 1
      );
      setTransition("next");
    }, 5000);

    return () => clearInterval(interval);
  }, []);

  const handlePrev = () => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === 0 ? images.length - 1 : prevIndex - 1
    );
    setTransition("prev");
  };

  const handleNext = () => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === images.length - 1 ? 0 : prevIndex + 1
    );
    setTransition("next");
  };

  return (
    <Box position="relative" overflow="hidden" m="15% 0">
      <div
        style={{
          position: "relative",
          width: "90%",
          height: "80vh",
          overflow: "hidden",
        }}
      >
        <Flex justify="center" align="center">
          <Button
            onClick={handlePrev}
            style={{
              position: "absolute",
              bottom: "20px",
              left: "20px",
              backgroundColor: "rgba(0, 0, 0, 0.5)",
              color: "white",
              padding: "10px",
              borderRadius: "50%",
              cursor: "pointer",
              zIndex: 1,
            }}
          >
            <ChevronLeftIcon boxSize={6} />
          </Button>
          <Button
            onClick={handleNext}
            style={{
              position: "absolute",
              bottom: "20px",
              right: "20px",
              backgroundColor: "rgba(0, 0, 0, 0.5)",
              color: "white",
              padding: "10px",
              borderRadius: "50%",
              cursor: "pointer",
              zIndex: 1,
            }}
          >
            <ChevronRightIcon boxSize={6} />
          </Button>
        </Flex>
        <div
          style={{
            display: "flex",
            transition: "transform 0.5s ease-in-out",
            transform:
              transition === "next"
                ? `translateX(-${currentImageIndex * 100}%)`
                : transition === "prev"
                ? `translateX(-${(currentImageIndex - 1) * 100}%)`
                : "",
          }}
        >
          {images.map((img, index) => (
            <Image
              key={index}
              src={img}
              alt={`Image ${index + 1}`}
              height="80vh"
              width="auto"
              style={{ flex: "0 0 100%" }}
            />
          ))}
        </div>
      </div>
      <Stack
        direction="row"
        spacing={2}
        position="absolute"
        bottom={4}
        left="50%"
        transform="translateX(-50%)"
      >
        {images.map((_, index) => (
          <Box
            key={index}
            w="8px"
            h="8px"
            bg={currentImageIndex === index ? "blue.500" : "gray.300"}
            borderRadius="full"
            onClick={() => setCurrentImageIndex(index)}
            cursor="pointer"
          />
        ))}
      </Stack>
    </Box>
  );
};

export default Carousel;
