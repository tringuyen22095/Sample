'use client';

import Loading from '@/shared/common/loading/loading';
import AnonymousLayout from '@/shared/layout/anonymous-layout/anonymous-layout';
import store from '@/shared/redux/store';
import React from 'react';
import { Provider } from 'react-redux';

const RootLayout: React.FC<any> = ({ children }) => {
  return (
    <AnonymousLayout>
      <Provider store={store}>
        <Loading />
        {children}
      </Provider>
    </AnonymousLayout>
  )
}
export default RootLayout;