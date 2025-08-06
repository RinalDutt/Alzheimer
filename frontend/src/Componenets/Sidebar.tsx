import React from "react";

const Sidebar = ({ activeIndex, handleColorChange, isOpen }) => {
  const SidebarItems = ["Notes", "Archive", "Deleted", "Draft", "Private"];

  return (
    // In the Sidebar component
    <div
      className={`fixed flex flex-col w-[20%] h-screen gap-2 p-4 rounded-b-sm transition-all duration-300 ${
        isOpen ? "translate-x-0" : "-translate-x-full"
      }`}
      style={{
        background: "linear-gradient(to bottom, #F2BDCD, #997A8D, #5B3256)",
      }}
    >
      {SidebarItems.map((item, index) => (
        <div
          key={index}
          onClick={() => handleColorChange(index)}
          className={`flex w-full justify-center p-3 cursor-pointer transition-all duration-300 text-white ${
            activeIndex === index
              ? "bg-[#5B3256] text-white"
              : "hover:bg-[#997A8D] hover:text-black"
          }`}
          style={{
            fontSize: "1.1rem",
            fontWeight: "bold",
            borderRadius: "4px",
            boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
          }}
        >
          {item}
        </div>
      ))}
    </div>
  );
};

export default Sidebar;
