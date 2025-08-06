import React from "react";
import bg from "../../public/bg.png";
import Image from "next/image";
import { Button } from "@mui/material";

const Notes = ({ onAddClick }) => {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "100%",
        height: "100%",
        margin: 0,
        padding: 0,
        overflow: "hidden",
        position: "relative",
      }}
    >
      <div
        style={{
          position: "absolute",
          top: "20px",
          right: "20px",
        }}
      >
        <Button className="bg-black text-amber-50" onClick={onAddClick}>
          ADD
        </Button>
      </div>
      <Image
        src={bg}
        alt="Image for no notes"
        style={{
          objectFit: "contain",
          width: "auto",
          height: "auto",
          maxWidth: "70%",
          maxHeight: "70%",
        }}
      />
    </div>
  );
};

export default Notes;
