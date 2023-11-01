import '../style/globals.css';
import React from 'react';
import { ToastContainer } from 'react-toastify';

export const metadata = {
  title: 'ixxhaianh',
  description: 'Private project'
}

const RootLayout = (props: React.PropsWithChildren) => {
  return (
    <html lang='en'>
      <head>
        <meta charSet='utf-8' />
        <meta name='viewport' content='width=device-width, initial-scale=1' />
        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css'
          integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC'
          crossOrigin='anonymous'
          rel='stylesheet' />
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'
          integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM'
          crossOrigin='anonymous'></script>
        <link href='https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css' rel='stylesheet' />
        <script src='https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js'></script>
      </head>
      <body>
        <main>
          {props.children}
          <ToastContainer/>
        </main>
      </body>
    </html>
  )
}
export default RootLayout;