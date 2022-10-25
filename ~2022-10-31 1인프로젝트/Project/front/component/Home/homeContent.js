import Link from "next/link"
import { Fragment } from "react"
import classes from "../../styles/home.module.css"

const HomeBanner = (props) => {
  //선택적으로 출력하기 위한 함수
  var arrContent = []

  if (props.states === 1) {
    for (var i = 0; i < 10; i++) {
      arrContent.push(props.notice[i])
    }
  } else {
    for (var i = 0; i < 10; i++) {
      arrContent.push(props.schoolmeal[i])
    }
  }

  return (
    <Fragment>
      {arrContent
        .slice(0)
        .reverse()
        .map((data, i) => {
          return (
            <tr key={i}>
              <td
                style={{
                  borderRight: "none",
                  textAlign: "left",
                  paddingLeft: "3%",
                  fontWeight: "bold",
                }}
              >
                {props.states === 1 ? (
                  <Link href={`/banner/Notice/${data.boardSeq}`}>
                    <a>{data.title}</a>
                  </Link>
                ) : (
                  <Link href={`/banner/SchoolMeal/${data.boardSeq}`}>
                    <a>{data.title}</a>
                  </Link>
                )}
                {/* { data.title } */}
              </td>
              <td
                style={{
                  borderLeft: "none",
                  textAlign: "right",
                  paddingRight: "5%",
                  fontSize: "10px",
                  "@media (maxWidth: 900px)": {
                    fontSize: "8px",
                  },
                }}
                className={classes.date}
              >
                {data.regDate.split(" ")[0]}
              </td>
            </tr>
          )
        })}
    </Fragment>
  )
}

export default HomeBanner
