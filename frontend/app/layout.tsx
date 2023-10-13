import { MyAppProps } from "@/shared/layout/types";
import "../style/globals.css";
import React from "react";
import { Layouts } from "@/shared/layout/layouts";
import NoLayout from "@/shared/layout/no-layout";

export const metadata = {
  title: 'ixxhaianh',
  description: 'Private project'
}

const RootLayout: React.FC<MyAppProps> = ({ children, LayoutKey }) => {
  const Layout = Layouts[LayoutKey] || NoLayout;
  return (
    <html lang="en">
      <head>
        <meta charSet="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossOrigin="anonymous"
          rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
          crossOrigin="anonymous"></script>
      </head>
      <body>
        <Layout>
          {children}
        </Layout>
      </body>
    </html>
  )
}
export default RootLayout;