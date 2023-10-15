import React, { PropsWithChildren } from "react";

const NoLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
      {children}
    </>
  )
}

export default NoLayout;