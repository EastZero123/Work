import SiteMapNav from "../../../component/SiteMap/sitemapNav"
import classes from "../../../styles/history.module.css"

const History = (props) => {
  return (
    <div>
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
  const res = await fetch("http://localhost:8080/api2/board/2")

  const data = await res.json()

  return {
    props: {
      data,
    },
  }
}

export default History
