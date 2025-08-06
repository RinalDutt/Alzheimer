import React, { useEffect, useState } from "react";
import {
  FaChartBar,
  FaClock,
  FaCheckCircle,
  FaExclamationTriangle,
} from "react-icons/fa";

const Cards = () => {
  const [inquiryCounts, setInquiryCounts] = useState({
    totalInquiry: 120,
    pendingAtAssttChq: 35,
    pendingAtVo: 65,
    completed: 20,
  });

  const [loading, setLoading] = useState(true);

  const countBoxConfig = [
    {
      key: "totalInquiry",
      label: "Total Applications for Gemstone",
      icon: FaChartBar,
      gradient: "linear-gradient(90deg, #007BA7 0%, #72A0C1 100%)",
    },
    {
      key: "pendingAtAssttChq",
      label: "Pending Applications",
      icon: FaClock,
      gradient: "linear-gradient(40deg, #DE3163 0%, #FF91A4 100%)",
    },
    {
      key: "pendingAtVo",
      label: "Approve Applications",
      icon: FaCheckCircle,
      gradient: "linear-gradient(40deg, #007A74 0%, #85B09A 100%)",
    },
    {
      key: "completed",
      label: "Rejected Applications",
      icon: FaExclamationTriangle,
      gradient: "linear-gradient(90deg, #8e4585 0%, #C9A0DC 100%)",
    },
  ];

  useEffect(() => {
    // Simulate loading data
    const timer = setTimeout(() => {
      setLoading(false);
    }, 1000);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="flex flex-col w-full overflow-hidden">
      <div className="overflow-hidden flex flex-col mb-6 p-6 bg-gradient-to-r from-slate-50 to-gray-50 border-gray-100">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
          {countBoxConfig.map((box) => {
            const IconComponent = box.icon;
            return (
              <div
                key={box.key}
                className={`
                  group relative p-6 rounded-2xl h-64
                  text-white shadow-2xl
                  cursor-pointer
                  transition-transform duration-300 ease-in-out
                  transform hover:scale-105 hover:shadow-xl
                  flex flex-col justify-between
                `}
                style={{
                  background: box.gradient,
                }}
              >
                <div className="absolute left-1/6 transform -translate-y-1/2">
                  <IconComponent className="w-10 h-10 opacity-90" />
                </div>
                <div className="text-center mt-4">
                  {loading ? (
                    <div className="animate-pulse bg-gray-300 h-12 w-20 mx-auto rounded"></div>
                  ) : (
                    <span className="text-5xl font-extrabold tabular-nums">
                      {inquiryCounts[box.key] || 0}
                    </span>
                  )}
                </div>
                <div className="text-center mb-6">
                  <span className="text-xl font-medium tracking-wide">
                    {box.label}
                  </span>
                </div>
                <div className="absolute bottom-0 left-0 right-0 h-1 bg-gradient-to-r from-blue-400 to-purple-400 rounded-b-2xl opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default Cards;
