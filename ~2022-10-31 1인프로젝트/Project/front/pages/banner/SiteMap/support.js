import SiteMapNav from "../../../component/SiteMap/sitemapNav"
import classes from "../../../styles/support.module.css"

const Support = (props) => {
  return (
    <div>
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
  const res = await fetch("http://localhost:8080/api2/board/5")

  const data = await res.json()

  return {
    props: {
      data,
    },
  }
}

export default Support
