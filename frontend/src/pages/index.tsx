// Import the necessary components

import Archive from "@/Componenets/Archive";
import Cards from "@/Componenets/Cards";
import { Deleted } from "@/Componenets/Deleted";
import Draft from "@/Componenets/Draft";
import Header from "@/Componenets/Header";
import Notes from "@/Componenets/Notes";
import Private from "@/Componenets/Private";
import Sidebar from "@/Componenets/Sidebar";
import TextEditor from "@/Componenets/TextEditor";
import { Geist, Geist_Mono } from "next/font/google";
import { useState } from "react";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export default function Home() {
  const [activeIndex, setActiveIndex] = useState<number | null>(null);
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);
  const [showTextEditor, setShowTextEditor] = useState(false);

  const handleColorChange = (index: number) => {
    setActiveIndex(index);
    setShowTextEditor(false);
  };

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };
  const handleAddClick = () => {
    setShowTextEditor(true);
  };

  const handleCloseTextEditor = () => {
    setShowTextEditor(false);
  };
  // Function to render the appropriate component based on activeIndex
  const renderComponent = () => {
    if (showTextEditor) {
      return <TextEditor onClose={handleCloseTextEditor} />;
    }
    switch (activeIndex) {
      case 0:
        return <Notes onAddClick={handleAddClick} />;
      case 1:
        return <Archive />;
      case 2:
        return <Deleted />;
      case 3:
        return <Draft />;
      case 4:
        return <Private />;
      default:
        return <Cards />;
    }
  };

  return (
    <div
      className={`${geistSans.variable} ${geistMono.variable} flex flex-col`}
    >
      <Header toggleSidebar={toggleSidebar} isSidebarOpen={isSidebarOpen} />
      <div className="flex flex-1">
        {isSidebarOpen && (
          <Sidebar
            activeIndex={activeIndex}
            handleColorChange={handleColorChange}
            isOpen={isSidebarOpen}
          />
        )}
        <div
          className={`flex-1 p-4 ${
            isSidebarOpen ? "ml-[20%]" : "ml-0"
          } transition-all duration-300`}
        >
          {renderComponent()}
        </div>
      </div>
    </div>
  );
}
