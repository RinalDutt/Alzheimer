import React from "react";
import { Editor } from "primereact/editor";

const TextEditor = ({ text, setText }: any) => {
  return (
    <div>
      <div className="card">
        <Editor
          value={text}
          onTextChange={(e) => setText(e.htmlValue || "")}
          style={{ height: "710px", width: "100%" }}
        />
      </div>
    </div>
  );
};

export default TextEditor;
