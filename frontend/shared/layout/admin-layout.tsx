import React, { PropsWithChildren } from "react";

const AdministratorLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
    admin
      <main>{children}</main>
    </>
  )
}

export default AdministratorLayout;