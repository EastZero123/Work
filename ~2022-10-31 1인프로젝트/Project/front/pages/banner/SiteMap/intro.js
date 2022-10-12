import { useRouter } from "next/router"
import { useState } from "react"
import SiteMapNav from "../../../component/SiteMap/sitemapNav"
import classes from "./intro.module.css"

const Intro = (props) => {
  const router = useRouter()
  console.log(router)
  return (
    <div>
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
  const res = await fetch("http://localhost:8080/api2/board/1")

  const data = await res.json()

  return {
    props: {
      data,
    },
  }
}

export default Intro
