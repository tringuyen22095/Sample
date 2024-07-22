'use client'

import './globals.css';
import 'bootstrap/dist/css/bootstrap.css';
import { useEffect } from 'react';
import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter';

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
        <script src='https://kit.fontawesome.com/0705440da3.js' crossOrigin='anonymous'></script>
      </head>
      <body>
        <AppRouterCacheProvider>
          {children}
        </AppRouterCacheProvider>
      </body>
    </html>
  );
}
