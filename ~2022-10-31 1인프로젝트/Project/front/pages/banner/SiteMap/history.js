import Head from "next/head"
import SiteMapNav from "../../../component/SiteMap/sitemapNav"
import classes from "../../../styles/history.module.css"

const History = (props) => {
  return (
    <div className={classes.history}>
      <Head>
        <title>History</title>
      </Head>
      <SiteMapNav />
      <div className={classes.HistoryMain}>
        <div
          className={classes.HistoryContent}
          dangerouslySetInnerHTML={{ __html: props.data.content }}
        ></div>
      </div>
    </div>
  )
}

export async function getStaticProps() {
  // history 내용 데이터 불러오기
  const res = await fetch("http://10.10.10.201:8617/api2/board/2")

  const data = await res.json()

  return {
    props: {
      data,
    },
  }
}

export default History
