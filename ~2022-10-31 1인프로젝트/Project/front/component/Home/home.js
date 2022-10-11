import Image from "next/image"
import { Fragment } from "react"
import Carousel from "./carousel"
// import Carousel from "react-bootstrap/Carousel"
import classes from "./home.module.css"

const HomeContent = () => {
  return (
    <Fragment>
      <Carousel />
      <div className={classes.Mainboard}>
        <table border="1px solid black">
          <thead>
            <tr>
              <th>공지사항</th>
              <th>급식 게시글</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colSpan={2}>Something content</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Fragment>
  )
}

export default HomeContent
