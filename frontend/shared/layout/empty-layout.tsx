import React, { PropsWithChildren } from "react";

const EmptyLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
        no
      <main>{children}</main>
    </>
  )
}

export default EmptyLayout;