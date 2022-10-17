import Head from "next/head"
import SiteMapNav from "../../../component/SiteMap/sitemapNav"
import classes from "../../../styles/intro.module.css"

const Intro = (props) => {
  return (
    <div className={classes.intro}>
      <Head>
        <title>Intro</title>
      </Head>
      <SiteMapNav />
      <div className={classes.IntroMain}>
        <div
          className={classes.IntroContent}
          dangerouslySetInnerHTML={{ __html: props.data.content }}
        ></div>
      </div>
    </div>
  )
}

export async function getStaticProps() {
  // intro 내용 데이터 불러오기
  const res = await fetch("http://localhost:8080/api2/board/1")

  const data = await res.json()

  return {
    props: {
      data,
    },
  }
}

export default Intro
