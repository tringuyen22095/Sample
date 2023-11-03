'use client';

import DashboardLayout from '@/shared/layout/dashboard-layout/dashboard-layout';
import React from 'react';

const RootLayout: React.FC<any> = ({ children }) => {
  return (
    <DashboardLayout>
      {children}
    </DashboardLayout>
  )
}
export default RootLayout;