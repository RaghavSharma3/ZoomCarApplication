import React from "react";
import CarForm from "../Components/CarForm";
import SideSlider from "../Components/SideSlider";

const HostPage = () => {
  return (
    <div>
      <div style={{ display: "flex", justifyContent: "space-evenly" }}>
        <CarForm style={{ width: "30%" }} />
        <div style={{ width: "55%" }}>
          <SideSlider />
        </div>
      </div>
    </div>
  );
};

export default HostPage;
