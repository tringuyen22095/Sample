import React, { PropsWithChildren } from "react";

const AnonymousLayout: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
    anonymous
      <main>{children}</main>
    </>
  )
}

export default AnonymousLayout;