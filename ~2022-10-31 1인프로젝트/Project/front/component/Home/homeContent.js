import Link from "next/link"
import { Fragment, useEffect, useReducer } from "react"

const HomeBanner = (props) => {
  var arrContent = []

  const newContent = () => {
    if (props.states === 1) {
      for (var i = 0; i < 10; i++) {
        arrContent.push(props.notice[i])
      }
    } else {
      for (var i = 0; i < 10; i++) {
        arrContent.push(props.schoolmeal[i])
      }
    }
  }
  newContent()

  console.log(arrContent)
  //   const ObjSchoolMeal = Object.values(props.SchoolMeal)
  //   const ObjNotice = Object.values(props.Notice)
  return (
    <Fragment>
      {arrContent
        .slice(0)
        .reverse()
        .map((data, i) => {
          return (
            <tr key={i}>
              {" "}
              <td
                style={{
                  borderRight: "none",
                  textAlign: "left",
                  paddingLeft: "5%",
                  fontWeight: "bold",
                  cursor: "pointer",
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
                  fontSize: "12px",
                }}
              >
                {data.regDate}
              </td>
            </tr>
          )
        })}
    </Fragment>
  )
}

export default HomeBanner
