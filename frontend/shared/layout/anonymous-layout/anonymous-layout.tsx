import React, { PropsWithChildren } from "react";
import "./anonymous-layout.scss";
import Slider from "@/components/slider/slider";
import useMediaQuery from '@mui/material/useMediaQuery';

const AnonymousLayout: React.FC<PropsWithChildren> = ({ children }) => {
  const matches = useMediaQuery('(min-width:900px)');

  return (
    <div className="anonymous-layout container">
      {
        matches &&
        <div className="left-container">
          <Slider />
        </div>
      }
      <div className="right-container">
        {children}
      </div>
    </div>
  )
}

export default AnonymousLayout;