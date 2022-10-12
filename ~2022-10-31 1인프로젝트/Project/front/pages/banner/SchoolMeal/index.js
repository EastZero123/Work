import "react-calendar/dist/Calendar.css"
import Calendar from "react-calendar"
import { useState } from "react"
import classes from "./schoolmeal.module.css"
import SchoolMealItem from "../../../component/SchoolMeal/schoolMealItem"

const SchoolMeal = (props) => {
  console.log(Object.values(props.datas))
  const ObjData = Object.values(props.datas)
  const [value, setValue] = useState(new Date())

  return (
    <div>
      <div className={classes.board}>
        <table>
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
              <th>첨부파일</th>
            </tr>
          </thead>
          <SchoolMealItem datas={ObjData} />
        </table>
      </div>
      <div className={classes.calendar}>
        <Calendar onChange={setValue} value={value} />
      </div>
    </div>
  )
}

export async function getStaticProps() {
  const res = await fetch("http://localhost:8080/api2/boardlist/4")

  const data = await res.json()

  return {
    props: {
      datas: data,
    },
  }
}

export default SchoolMeal
