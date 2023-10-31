import DashboardLayout from "@/shared/layout/dashboard-layout/dashboard-layout";
import React from "react";

export const metadata = {
  title: 'ixxhaianh',
  description: 'Private project'
}

const RootLayout: React.FC<any> = ({ children }) => {
  return (
    <DashboardLayout>
      {children}
    </DashboardLayout>
  )
}
export default RootLayout;