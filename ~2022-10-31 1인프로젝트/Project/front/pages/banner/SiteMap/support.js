import Head from "next/head"
import SiteMapNav from "../../../component/SiteMap/sitemapNav"
import classes from "../../../styles/support.module.css"

const Support = (props) => {
  const goPrint = () => {
    console.log("goPrint")
  }

  return (
    <div className={classes.support}>
      <Head>
        <title>Support</title>
      </Head>
      <SiteMapNav />
      <div className={classes.SupportMain}>
        <div
          className={classes.SupportContent}
          dangerouslySetInnerHTML={{ __html: props.data.content }}
        ></div>
      </div>
    </div>
  )
}

export async function getStaticProps() {
  //support 내용 데이터 불러오기
  const res = await fetch("http://10.10.10.201:8617/api2/board/5")

  const data = await res.json()

  return {
    props: {
      data,
    },
  }
}

export default Support
