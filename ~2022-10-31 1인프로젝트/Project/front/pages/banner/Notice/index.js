import axios from "axios"
import Head from "next/head"
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
  const CurrentPosts = (posts) => {
    let currentPosts = 0
    currentPosts = posts.slice(indexOfFirst, indexOfLast)
    return currentPosts
  }

  const onFilter = (filter) => {
    setPostsPerPage(filter.target.value)
    setCurrentPage(1)
  }

  //최근 날짜순 정렬을 위한 역순 배치
  const ReunitData = props.datas.slice(0).reverse()

  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!props.datas) {
    return <div></div>
  }

  return (
    <div>
      <Head>
        <title>공지사항</title>
      </Head>
      <div className={classes.notice}>
        <div className={classes.board}>
          <div className={classes.filter}>
            {/* 필터 컴포넌트 */}
            <Filter onFilter={onFilter} />
          </div>

          {/* 테이블 화면 */}
          <table>
            <thead>
              <tr>
                <th style={{ width: "15%" }}>
                  <div style={{ minHeight: "30px" }}>번호</div>
                </th>
                <th style={{ width: "30%" }}>
                  <div style={{ minHeight: "30px" }}>제목</div>
                </th>
                <th style={{ width: "15%" }}>
                  <div style={{ minHeight: "30px" }}>작성자</div>
                </th>
                <th style={{ width: "20%" }}>
                  <div style={{ minHeight: "30px" }}>날짜</div>
                </th>
                <th style={{ width: "10%" }}>
                  <div style={{ minHeight: "30px" }}>첨부파일</div>
                </th>
              </tr>
            </thead>

            {/* 공지사항 데이터 배치 */}
            <NoticeItem datas={CurrentPosts(ReunitData)} />
          </table>
          {/* 페이징 컴포넌트 */}
          <Pagination
            postsPerPage={postsPerPage}
            totalPosts={props.datas.length}
            paginate={setCurrentPage}
            page={currentPage}
          ></Pagination>
        </div>
      </div>
    </div>
  )
}

export async function getStaticProps() {
  //공지사항 데이터 불러오기
  const res = await axios.get("http://10.10.10.201:8617/api2/boardlist/5")

  const data = res.data

  return {
    props: {
      datas: data,
    },
  }
}

export default Notice
