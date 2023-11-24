'use client';

import Loading from '@/shared/common/loading/loading';
import AnonymousLayout from '@/shared/layout/anonymous-layout/anonymous-layout';
import store from '@/shared/redux/store';
import React from 'react';
import { Provider } from 'react-redux';

const RootLayout: React.FC<any> = ({ children }) => {
  return (
    <Provider store={store}>
      <AnonymousLayout>
        <Loading />
        {children}
      </AnonymousLayout>
    </Provider>
  )
}
export default RootLayout;