import React, { useState } from "react";
import bg from "../../public/bg.png";
import Image from "next/image";
import axios from "axios";
import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import TextEditor from "./TextEditor"; // Adjust the import path as necessary

const Notes = () => {
  const [openTitleDialog, setOpenTitleDialog] = useState(false);
  const [openTextEditor, setOpenTextEditor] = useState(false);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleClickOpen = () => {
    setOpenTitleDialog(true);
  };

  const handleCloseTitleDialog = () => {
    setOpenTitleDialog(false);
  };

  const handleOpenTextEditor = () => {
    setOpenTitleDialog(false);
    setOpenTextEditor(true);
  };

  const handleCloseTextEditor = () => {
    setOpenTextEditor(false);
  };

  const handleSaveNote = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/notes/create",
        {
          title,
          content,
          status: "DRAFT",
          private: false,
          deleted: false,
          archived: false,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      alert("Note created successfully!");
      console.log("Note created:", response.data);
      setTitle("");
      setContent("");
      handleCloseTextEditor();
    } catch (error) {
      console.error("Error creating note:", error);
    }
  };

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
        <Button className="bg-black text-amber-50" onClick={handleClickOpen}>
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

      <Dialog open={openTitleDialog} onClose={handleCloseTitleDialog}>
        <DialogTitle>Add New Note</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            style={{ width: "500px" }}
            label="Title"
            fullWidth
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseTitleDialog} color="primary">
            Cancel
          </Button>
          <Button onClick={handleOpenTextEditor} color="primary">
            Next
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog
        open={openTextEditor}
        onClose={handleCloseTextEditor}
        maxWidth="md"
        fullWidth
      >
        <DialogTitle>Edit Note Content</DialogTitle>
        <DialogContent>
          <TextEditor text={content} setText={setContent} />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseTextEditor} color="primary">
            Cancel
          </Button>
          <Button onClick={handleSaveNote} color="primary">
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default Notes;
