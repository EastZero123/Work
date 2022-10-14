import HomeContent from "../component/Home/home"

import axios from "axios"

const Home = (props) => {
  // 패치 함수 예시
  // const fetchdata = async () => {
  //   try {
  //     const response = await fetch(
  //       "https://jsonplaceholder.typicode.com/posts?_limit=6"
  //     ).then((response) => response.json());
  //     console.log(response);
  //   } catch (error) {
  //     console.log(error.message);
  //   }
  // };

  // useEffect(() => {
  //   fetchdata();
  // }, []);

  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!props) {
    return <div></div>
  }
  return (
    <div>
      <HomeContent notice={props.notice} schoolmeal={props.schoolmeal} />
    </div>
  )
}

export async function getStaticProps() {
  //공지사항 데이터 불러오기
  const notice = await axios.get("http://localhost:8080/api2/boardlist/5")

  const noticeData = notice.data

  // 급식 게시글 데이터 불러오기
  const schoolmeal = await axios.get("http://localhost:8080/api2/boardlist/4")

  const schoolmealData = schoolmeal.data

  return {
    props: {
      notice: noticeData,
      schoolmeal: schoolmealData,
    },
  }
}

export default Home
