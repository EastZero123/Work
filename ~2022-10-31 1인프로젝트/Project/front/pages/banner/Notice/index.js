import axios from "axios"
import NoticeItem from "../../../component/Notice/noticeItem"
import classes from "../../../styles/notice.module.css"

const Notice = (props) => {
  return (
    <div className={classes.notice}>
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

          <NoticeItem datas={props.datas} />
        </table>
      </div>
    </div>
  )
}

export async function getStaticProps() {
  const res = await axios.get("http://localhost:8080/api2/boardlist/5")

  const data = res.data

  return {
    props: {
      datas: data,
    },
  }
}

export default Notice
