'use client';

import DashboardLayout from '@/shared/layout/dashboard-layout/dashboard-layout';
import React from 'react';
import store from '@/shared/redux/store';
import { Provider } from 'react-redux';

const RootLayout: React.FC<any> = ({ children }) => {
  return (
    <Provider store={store}>
      <DashboardLayout>
        {children}
      </DashboardLayout>
    </Provider>
  )
}
export default RootLayout;