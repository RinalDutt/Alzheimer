import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button } from "@mui/material";
import React from "react";
import { faBars } from "@fortawesome/free-solid-svg-icons";

// In the Header component
const Header = ({ toggleSidebar, isSidebarOpen }) => {
  return (
    <div
      className={`flex w-full items-center justify-between p-4 bg-[#5B3256] h-[45px] transition-all duration-300 ${
        isSidebarOpen ? "" : "ml-0"
      }`}
    >
      <FontAwesomeIcon
        icon={faBars}
        className="text-white cursor-pointer"
        onClick={toggleSidebar}
      />
      <Button className="bg-purple-400 text-white">Click here</Button>
    </div>
  );
};

export default Header;
