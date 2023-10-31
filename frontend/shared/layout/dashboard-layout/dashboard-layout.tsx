import React, { PropsWithChildren } from "react";

const DashboardLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
      dashboard
      {children}
    </>
  )
}

export default DashboardLayout;