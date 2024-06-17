'use client'

import { Inter } from 'next/font/google';
import 'styles/globals.css';
import 'bootstrap/dist/css/bootstrap.css';
import { useEffect } from 'react';

const inter = Inter({ subsets: ['latin'] });

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
      <body className={inter.className}>{children}</body>
    </html>
  );
}
