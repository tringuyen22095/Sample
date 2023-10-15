import React, { PropsWithChildren } from "react";

const NoLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
        no
      <main>{children}</main>
    </>
  )
}

export default NoLayout;