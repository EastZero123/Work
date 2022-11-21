import "react-calendar/dist/Calendar.css"
import Calendar from "react-calendar"
import { useState } from "react"
import classes from "../../../styles/schoolmeal.module.css"
import SchoolMealItem from "../../../component/SchoolMeal/schoolMealItem"
import moment from "moment"
import Head from "next/head"

const SchoolMeal = (props) => {
  // 급식게시글 데이터를 배열 형태로 재배치
  const ObjData = Object.values(props.datas)

  // 달력 필터링에 필요한 선언
  var dateArr = []
  const [value, setValue] = useState(new Date())
  const [visible, setIsvisible] = useState(true)

  //달력 필터링 함수
  dateArr = ObjData.filter((data) => {
    return data.regDate.split(" ")[0] === moment(value).format("YYYY년MM월DD일")
  })

  // 달력 내용을 누르면 전체 데이터 출력 비활성화
  const dateChange = () => {
    setIsvisible(false)
  }

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

  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!props.datas) {
    return <div></div>
  }

  return (
    <div className={classes.schoolmeal}>
      <Head>
        <title>급식 게시글</title>
      </Head>
      <div className={classes.calendar}>
        {/* 달력 컴포넌트 */}
        <Calendar onChange={setValue} value={value} onClickDay={dateChange} />
      </div>
      <div className={classes.board}>
        <table>
          <thead>
            <tr>
              <th style={{ width: "15%" }}>번호</th>
              <th style={{ width: "33%" }}>제목</th>
              <th style={{ width: "13%" }}>작성자</th>
              <th style={{ width: "20%" }}>날짜</th>
              <th style={{ width: "10%" }}>첨부파일</th>
            </tr>
          </thead>
          {/* 급식 게시글 데이터 배치 */}
          <SchoolMealItem datas={ObjData} dateArr={dateArr} visible={visible} />
        </table>
      </div>
    </div>
  )
}
export async function getStaticProps() {
  //급식 게시글 데이터 불러오기
  const res = await fetch("http://10.10.10.201:8617/api2/boardlist/4")

  const data = await res.json()

  return {
    props: {
      datas: data,
    },
  }
}

export default SchoolMeal
