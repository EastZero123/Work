import "react-calendar/dist/Calendar.css"
import Calendar from "react-calendar"
import { useState } from "react"
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

  // 파일 유무 테스트용 더미 데이터

  // const Dummy_Data = [
  //   {
  //     boardManagementSeq: 4,
  //     boardCode: "cafeteria",
  //     boardName: "급식식단표 관리",
  //     delYn: "N",
  //     updId: null,
  //     updDate: null,
  //     regId: "happyict",
  //     regDate: "2021-11-15 22:28:46.0",
  //     boardSeq: 18,
  //     title: "2021년 11월 15일 급식식단표",
  //     content: "",
  //     regName: "관리자",
  //     fileYn: "Y",
  //   },
  // ]

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
              <th style={{ width: "10%" }}>번호</th>
              <th style={{ width: "40%" }}>제목</th>
              <th style={{ width: "10%" }}>작성자</th>
              <th style={{ width: "20%" }}>날짜</th>
              <th style={{ width: "10%" }}>첨부파일</th>
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
