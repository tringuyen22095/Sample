'use client'

import './globals.css';
import 'bootstrap/dist/css/bootstrap.css';
import { useEffect } from 'react';

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
        {children}
      </body>
    </html>
  );
}
