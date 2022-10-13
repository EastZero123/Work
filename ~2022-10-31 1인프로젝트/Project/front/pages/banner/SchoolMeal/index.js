import "react-calendar/dist/Calendar.css"
import Calendar from "react-calendar"
import { useEffect, useState } from "react"
import classes from "../../../styles/schoolmeal.module.css"
import SchoolMealItem from "../../../component/SchoolMeal/schoolMealItem"
import moment from "moment"

const SchoolMeal = (props) => {
  const ObjData = Object.values(props.datas)
  console.log(ObjData)
  const [value, setValue] = useState(new Date())
  const [visible, setIsvisible] = useState(true)

  var dateArr = []
  dateArr = ObjData.filter((data) => {
    return data.regDate.split(" ")[0] === moment(value).format("YYYY년MM월DD일")
  })

  // const Alldate = () => {
  //   dateArr
  // }

  const dateChange = () => {
    setIsvisible(false)
  }

  return (
    <div>
      <div className={classes.board}>
        {/* <button onClick={Alldate}>전체보기</button> */}
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
          <SchoolMealItem datas={ObjData} dateArr={dateArr} visible={visible} />
        </table>
      </div>
      <div className={classes.calendar}>
        <Calendar onChange={setValue} value={value} onClickDay={dateChange} />
        {/* <div>{moment(value).format("YYYY년MM월DD일")}</div> */}
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
