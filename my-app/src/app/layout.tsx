'use client'

import './globals.css';
import 'bootstrap/dist/css/bootstrap.css';
import { useEffect } from 'react';
import { Provider } from 'react-redux';
import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter';
import store from '../../common/redux/store';

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  useEffect(() => {
    import('bootstrap/dist/js/bootstrap');
  }, []);

  return (
    <html lang='en'>
      <head>
        <meta charSet='UTF-8' />
        <title>Wedding Invitation</title>
      </head>
      <body>
        <Provider store={store}>
          <AppRouterCacheProvider>
            {children}
          </AppRouterCacheProvider>
        </Provider>
      </body>
    </html>
  );
}
