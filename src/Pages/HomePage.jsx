import {
  Box,
  Heading,
  Grid,
  GridItem,
  Center,
  Flex,
  Image,
  Text,
} from "@chakra-ui/react";
import { Tabs, TabList, TabPanels, Tab, TabPanel } from "@chakra-ui/react";
import { useContext } from "react";
import HomePageBox from "../Components/HomePageBox";
import { AuthContext } from "../Context/AuthContextProvider";
import Slider from "../Components/Slider";
import Slider2 from "../Components/Slider2";
export default function HomePage() {
  const { location } = useContext(AuthContext);

  return (
    <div>
      <HomePageBox />

      <Box w="70%" margin="auto">
        <Heading
          fontWeight="bold"
          fontSize={["lg", "2xl", "2xl", "3xl"]}
          mt="5"
          textAlign="left"
        >
          SELF DRIVE CARS IN {location !== "" ? location : "BANGLORE"}
        </Heading>
        <Text fontSize="20px" textAlign="left" mt="1">
          Zoomcar provides you with a car on rent in{" "}
          {location !== "" ? location : "BANGLORE"} without a driver. Book your
          car conveniently through online booking or on your smartphone. The
          vehicles have all-India permits, and Zoomcar provides multiple parking
          sites across the country from where you can pick up your chosen car.
          You also have the flexibility of choosing from various packages,
          depending on whether you need the vehicle for a longer time or a
          longer distance.
        </Text>
      </Box>
      <br />
      <br />
      <br />
      <Center style={{ height: "80px" }}>
        <Heading fontWeight="bold" fontSize={["lg", "3xl", "3xl", "4xl"]}>
          CHOOSE FROM OUR WIDE RANGE OF CARS
        </Heading>
      </Center>

      <Slider2 />

      <Box style={{ height: "130px", marginTop: "12px" }}>
        <Center p={30}>
          <Flex flexDirection={"column"}>
            <Box>
              <Heading
                textAlign="center"
                color={"black"}
                fontWeight=""
                margin="0"
                size={["md", "lg", "xl"]}
              >
                UNMATCHED BENEFITS
              </Heading>
            </Box>
            <Center>
              <Box>
                <Heading
                  textAlign="center"
                  color={"black"}
                  fontWeight="xl"
                  margin="0"
                  size={["md", "md", "md"]}
                >
                  Drive everywhere with freedom
                </Heading>
              </Box>
            </Center>
          </Flex>
        </Center>
      </Box>

      <Slider />

      <br />
      <br />
      <br />
      <Box bg="rgb(244,244,244)" p={5}>
        <Box w="70%" m="auto">
          <Grid
            templateRows="repeat(2, 1fr)"
            templateColumns="repeat(2, 1fr)"
            gap={4}
          >
            <GridItem>
              <Image
                src="https://zoomcar-assets.zoomcar.com/pictures/original/d70e7f58da384df0f3ba169cf19e8e931879c66c.jpeg?1652354515"
                borderRadius="10px"
              />
            </GridItem>
            <GridItem>
              <Image
                src="https://zoomcar-assets.zoomcar.com/pictures/original/e3dfe0ed91abc29facaf56a327b4498ce4e9b75e.jpeg?1652363808"
                borderRadius="10px"
              />
            </GridItem>
            <GridItem>
              <Image
                src="https://zoomcar-assets.zoomcar.com/pictures/original/e3dfe0ed91abc29facaf56a327b4498ce4e9b75e.jpeg?1652363808"
                borderRadius="10px"
                w="100%"
              />
            </GridItem>
            <GridItem>
              <Image
                src="https://zoomcar-assets.zoomcar.com/pictures/original/55c3970a33c8f4d10dd3c9ace39d20e32af5c8ba.jpg?1651758755"
                borderRadius="10px"
                h="100%"
              />
            </GridItem>
          </Grid>
        </Box>
      </Box>

      <br />
      <br />
      <br />
      <Box pt={"2rem"} bg={"#383838"} color={"white"}>
        <Tabs w={"90%"} margin="auto" bg="#383838" variant="unstyled">
          <TabList justifyContent={"space-between"}>
            <Tab
              _selected={{ borderBottom: "2px solid white" }}
              fontWeight="bold"
            >
              <Heading size={["xs", "md"]} color={"white"}>
                ABOUT US
              </Heading>
            </Tab>
            <Tab
              _selected={{ borderBottom: "2px solid white" }}
              fontWeight="bold"
            >
              <Heading size={["xs", "md"]} color={"white"}>
                BLOGS
              </Heading>
            </Tab>
            <Tab
              _selected={{ borderBottom: "2px solid white" }}
              fontWeight="bold"
            >
              <Heading size={["xs", "md"]} color={"white"}>
                CAREERS
              </Heading>
            </Tab>
            <Tab
              _selected={{ borderBottom: "2px solid white" }}
              fontWeight="bold"
            >
              <Heading size={["xs", "md"]} color={"white"}>
                HELP & SUPPORT
              </Heading>
            </Tab>
          </TabList>
          <br />
          <TabPanels>
            <TabPanel style={{ textAlign: "left" }}>
              <Heading size="md"> Drive your dreams with Zoomcar </Heading>
              <br />

              <Text>
                Are you ready to discover the vibrant city of Lucknow in a
                Zoomcar? Rent a car in Lucknow and with Zoomcar and enjoy
                convenient and affordable rides on your own. With Zoomcar, you
                can explore the city at your own pace and enjoy the freedom of
                choosing your own itinerary.
                <br /> <br /> Whether you're in Lucknow for business or leisure,
                Zoomcar in Lucknow offers you the perfect mode of
                transportation. Explore the city's rich cultural heritage,
                sample its delicious cuisine, and immerse yourself in its
                vibrant street life.
              </Text>
              <br />
              <Heading size="md">Want A Specific Car?</Heading>
              <br />
              <Text>
                We've Got It All: Rent Tata Nexon | Rent Honda Jazz | Rent
                Hyundai Creta | Rent Maruti Brezza | Rent Mahindra | Rent XUV500
                | Rent Hyundai Verna | Rent Maruti Swift | Rent Toyota Innova |
                Rent Reanult Kwid | Rent Maruti Baleno | Rent Mahindra TUV300|
                Rent Maruti Swift | Rent Hyundai i20 | Rent Maruti Ertiga | Rent
                Volkswagen Polo | Rent Hyundai Venue | Rent Hyundai Eon | Rent
                Maruti S-Cross | Rent Maruti | Rent Dzire | Rent Honda Amaze |
                Rent Hyundai Verna | Rent Maruti Ciaz | Rent Hyundai Creta |
                Rent Renault Triber | Rent Maruti S-Cross | Rent Toyota Innova |
                Rent Mahindra TUV300 | Rent Tata Tiago | Rent Maruti Wagon R |
                Rent Datson Redi-Go | Rent Maruti Alto | Rent Hyundai Grand i10
                | Rent Hyundai Xcent | Rent Maruti S-Presso | Rent Hyundai
                Santro | Rent Hyundai Verna | Rent Maruti Ignis | Rent Nissan
                Magnite | Rent Toyota Glanza | Rent Honda City | Rent Maruti
                Dzire | Rent Datson GO T | Rent Nissan Sunny | Rent Renault
                Kiger | Rent Hyundai Aura | Rent Renault Duster | Rent Mahindra
                Bolero | Rent Tata Altroz | Rent Ford EcoSport
              </Text>
              <br />
            </TabPanel>
            <TabPanel>
              <Heading size="md">
                Find more information about Zoomcar Blogs{" "}
                <a href="https://www.zoomcar.com/blog/">
                  <u>here</u>
                </a>
              </Heading>
            </TabPanel>
            <TabPanel>
              <Heading size="md">
                Find more information about Zoomcar Careers{" "}
                <a href="https://www.zoomcar.com/careers">
                  <u>here</u>
                </a>
              </Heading>
            </TabPanel>
            <TabPanel>
              <Heading size="md">
                Find more information about Zoomcar Help and Support{" "}
                <a href="https://www.zoomcar.com/faq">
                  <u>here</u>
                </a>
              </Heading>
            </TabPanel>
          </TabPanels>
        </Tabs>
      </Box>
    </div>
  );
}
