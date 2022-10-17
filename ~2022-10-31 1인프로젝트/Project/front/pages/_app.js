//헤더,푸터 css
import Layout from "../component/UI/Layout"

//Html에서의 head역할
import Head from "next/head"

// 공통 css
import "../styles/globals.css"

// 부트스트랩 적용을 위해선 다음 css를 넣어야함
import "bootstrap/dist/css/bootstrap.css"

function MyApp({ Component, pageProps }) {
  return (
    <Layout>
      <Head>
        <title>MVP</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      </Head>
      <Component {...pageProps} />
    </Layout>
  )
}

export default MyApp
