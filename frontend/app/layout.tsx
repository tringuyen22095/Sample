"use client";

import "../style/globals.css";
import React from "react";
import { Backdrop, CircularProgress } from '@mui/material';

const RootLayout: React.FC<any> = ({ children }) => {
  const [isLoading, setLoading] = React.useState(false);
  const childrenParameter = {
    setLoading
  }
  return (
    <html lang="en">
      <head>
        <meta charSet="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>ixxhaianh</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossOrigin="anonymous"
          rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
          crossOrigin="anonymous"></script>
        <link href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css" rel="stylesheet" />
        <script src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
      </head>
      <body>
        <main>
          <Backdrop open={isLoading}
            sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}>
            <CircularProgress color="inherit" />
          </Backdrop>
          {children}
        </main>
      </body>
    </html>
  )
}
export default RootLayout;