import AnonymousLayout from "@/shared/layout/anonymous-layout/anonymous-layout";
import React from "react";

export const metadata = {
  title: 'ixxhaianh',
  description: 'Private project'
}

const RootLayout: React.FC<any> = ({ children }) => {
  return (
    <AnonymousLayout>
      {children}
    </AnonymousLayout>
  )
}
export default RootLayout;