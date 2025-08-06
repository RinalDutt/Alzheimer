import React, { useState } from "react";

const TextEditor = ({ onClose }) => {
  const [text, setText] = useState("");

  const handleTextChange = (event) => {
    setText(event.target.value);
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        height: "100%",
        backgroundColor: "#f5f5f5",
        border: "none",
        boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)",
      }}
    >
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          padding: "10px 20px",
          backgroundColor: "#6200ee",
          color: "white",
        }}
      >
        <h2 style={{ margin: 0 }}>Text Editor</h2>
        <button
          onClick={onClose}
          style={{
            backgroundColor: "transparent",
            border: "none",
            color: "white",
            fontSize: "16px",
            cursor: "pointer",
          }}
        >
          Close
        </button>
      </div>
      <textarea
        value={text}
        onChange={handleTextChange}
        style={{
          flex: 1,
          padding: "20px",
          fontSize: "16px",
          border: "none",
          resize: "none",
          fontFamily: "Arial, sans-serif",
          outline: "none",
        }}
      />
      <div
        style={{
          padding: "10px 20px",
          backgroundColor: "#6200ee",
          display: "flex",
          justifyContent: "flex-end",
        }}
      >
        <button
          onClick={() => console.log(text)}
          style={{
            padding: "10px 20px",
            backgroundColor: "white",
            color: "#6200ee",
            border: "none",
            borderRadius: "4px",
            cursor: "pointer",
            fontWeight: "bold",
          }}
        >
          Save
        </button>
      </div>
    </div>
  );
};

export default TextEditor;
