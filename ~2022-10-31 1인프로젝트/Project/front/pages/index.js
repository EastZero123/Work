import HomeContent from "../component/Home/home"

import axios from "axios"

const Home = (props) => {
  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!props) {
    return <div></div>
  }

  const ReunitNotice = props.notice.slice(0).reverse()
  const ReunitSchoolMeal = props.schoolmeal.slice(0).reverse()
  return (
    <div>
      <HomeContent notice={ReunitNotice} schoolmeal={ReunitSchoolMeal} />
    </div>
  )
}

export async function getStaticProps() {
  //공지사항 데이터 불러오기
  const notice = await axios.get("http://10.10.10.201:8617/api2/boardlist/5")

  const noticeData = notice.data

  // 급식 게시글 데이터 불러오기
  const schoolmeal = await axios.get(
    "http://10.10.10.201:8617/api2/boardlist/4"
  )

  const schoolmealData = schoolmeal.data

  return {
    props: {
      notice: noticeData,
      schoolmeal: schoolmealData,
    },
  }
}

export default Home
