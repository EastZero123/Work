import axios from "axios"
import NoticeItem from "../../../component/Notice/noticeItem"
import classes from "./notice.module.css"

const Notice = (props) => {
  //   console.log(props.datas)

  //   const arrData = JSON.stringify(props.datas)
  //   const arrData2 = JSON.parse(arrData)
  //   console.log(typeof arrData2)
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

  //   var Strdata = JSON.parse(data)

  return {
    props: {
      datas: data,
    },
  }
}

export default Notice
