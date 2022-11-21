/* eslint-disable @next/next/no-page-custom-font */

//헤더,푸터 css
import Layout from "../component/UI/Layout"

//Html에서의 head역할
import Head from "next/head"

// 공통 css
//test
import "../styles/globals.css"

// 부트스트랩 적용을 위해선 다음 css를 넣어야함
import "bootstrap/dist/css/bootstrap.css"

function MyApp({ Component, pageProps }) {
  return (
    <div className="App">
      <Layout>
        <Head>
          <title>MVP</title>
          <link rel="preconnect" href="https://fonts.googleapis.com" />
          <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
          <link
            href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap"
            rel="stylesheet"
          />
          <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0"
          />
        </Head>
        <Component {...pageProps} />
      </Layout>
    </div>
  )
}

export default MyApp
