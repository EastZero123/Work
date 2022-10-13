import { useReducer } from "react"
import Carousel from "./carousel"

import classes from "../../styles/home.module.css"
import HomeBanner from "./homeContent"

const homebanner = (states, action) => {
  switch (action.type) {
    case "notice":
      return (states = 1)
    case "schoolmeal":
      return (states = 2)
    default:
      return (states = 1)
  }
}

const HomeContent = ({ notice, schoolmeal }) => {
  const [state, dispatch] = useReducer(homebanner, 1)

  const noticeAction = () => {
    dispatch({
      type: "notice",
    })
  }

  const schoolmealAction = () => {
    dispatch({
      type: "schoolmeal",
    })
  }

  return (
    <div style={{ zIndex: "0", marginTop: "10%" }}>
      <Carousel />
      <div className={classes.Mainboard} style={{ borderRadius: "20%" }}>
        <table border="0.1px solid black">
          <thead>
            <tr>
              <th
                onClick={noticeAction}
                className={state === 1 ? classes.active : ""}
              >
                공지사항
              </th>
              <th
                onClick={schoolmealAction}
                className={state === 2 ? classes.active : ""}
              >
                급식 게시글
              </th>
            </tr>
          </thead>
          <tbody>
            <HomeBanner
              states={state}
              {...(state === 1
                ? (notice = { notice })
                : (schoolmeal = { schoolmeal }))}
            />
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default HomeContent
