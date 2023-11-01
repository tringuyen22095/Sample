'use client';

import React, { PropsWithChildren } from "react";
import "./anonymous-layout.scss";
import Slider from "@/components/slider/slider";

const AnonymousLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
      <div className="anonymous-layout container">
        <div className="left-container">
          <Slider/>
        </div>
        <div className="right-container">
          {children}
        </div>
      </div>
    </>
  )
}

export default AnonymousLayout;