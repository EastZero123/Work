import axios from "axios"
import { useState } from "react"
import Filter from "../../../component/Notice/filter"
import NoticeItem from "../../../component/Notice/noticeItem"
import Pagination from "../../../component/Notice/pagenation"
import classes from "../../../styles/notice.module.css"

const Notice = (props) => {
  //페이징 처리에 필요한 선언
  const [currentPage, setCurrentPage] = useState(1)
  const [postsPerPage, setPostsPerPage] = useState(10)
  const indexOfLast = currentPage * postsPerPage
  const indexOfFirst = indexOfLast - postsPerPage
  const currentPosts = (posts) => {
    let currentPosts = 0
    currentPosts = posts.slice(indexOfFirst, indexOfLast)
    return currentPosts
  }

  const onFilter = (filter) => {
    setPostsPerPage(filter.target.value)
  }

  //최근순 정렬을 위한 역순 배치
  const ReunitData = props.datas.slice(0).reverse()

  return (
    <div className={classes.notice}>
      <div className={classes.board}>
        <div className={classes.filter}>
          {/* 필터 컴포넌트 */}
          <Filter onFilter={onFilter} />
        </div>
        {/* 페이징 컴포넌트 */}
        <Pagination
          postsPerPage={postsPerPage}
          totalPosts={props.datas.length}
          paginate={setCurrentPage}
          page={currentPage}
        ></Pagination>
        <table>
          <thead>
            <tr>
              <th style={{ width: "10%" }}>번호</th>
              <th style={{ width: "45%" }}>제목</th>
              <th style={{ width: "10%" }}>작성자</th>
              <th style={{ width: "25%" }}>날짜</th>
              <th style={{ width: "10%" }}>첨부파일</th>
            </tr>
          </thead>

          {/* 공지사항 데이터 배치 */}
          <NoticeItem datas={currentPosts(ReunitData)} />
        </table>
      </div>
    </div>
  )
}

export async function getStaticProps() {
  //공지사항 데이터 불러오기
  const res = await axios.get("http://localhost:8080/api2/boardlist/5")

  const data = res.data

  return {
    props: {
      datas: data,
    },
  }
}

export default Notice
