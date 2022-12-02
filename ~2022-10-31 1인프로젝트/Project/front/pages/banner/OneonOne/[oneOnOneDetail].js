import axios from "axios"
import Head from "next/head"
import { useRouter, withRouter } from "next/router"
import { useRef } from "react"
import ReplyContent from "../../../component/OneonOne/Reply/ReplyContent"
import classes from "../../../styles/oneononeDetail.module.css"

const OneonOneDetail = (props) => {
  const replyref = useRef()
  const router = useRouter()
  console.log(props.data)

  const ReplyConfirm = async (ReplyRegisterJSONData) => {
    try {
      await fetch("http://localhost:8080/oneonone/reply/register", {
        // localhost:3000/
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(ReplyRegisterJSONData),
      })
        .then((response) => console.log(response))
        .then((data) => console.log(data))
    } catch (e) {}
  }

  const submitHandler = (e) => {
    e.preventDefault()
    const enteredReply = replyref.current.value

    const ReplyRegisterJSONData = {
      replyText: enteredReply,
      replyWriter: "Someone",
      bno: Number(props.data[0].bno),
    }
    replyref.current.value = ""
    try {
      ReplyConfirm(ReplyRegisterJSONData)
      router.replace(router.asPath)
    } catch (error) {
      console.log(error.message)
    }
  }

  const deleteHandler = async (e) => {
    e.preventDefault()
    try {
      const response = await fetch(
        `http://localhost:8080/oneonone/board/delete/${props.data[0].bno}`,
        {
          method: "DELETE",
        }
      )
      const data = await response.json()
      console.log(data)
    } catch (e) {}
    router.replace("/banner/OneonOne")
  }

  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!props) {
    return <div></div>
  }
  return (
    <div>
      <Head>
        <title>{props.data[0].title}</title>
      </Head>
      <div className={classes.detail}>
        {/* 테이블 전체 */}
        <table style={{ width: "80%", margin: "5% 10%" }}>
          <thead></thead>
          <tbody>
            {/* 제목, 날짜 */}
            <tr style={{ paddingTop: "5%" }}>
              <td className={classes.title}>{props.data[0].title}</td>
              <td
                className={classes.date}
              >{`${props.data[0].regDate[0]}-${props.data[0].regDate[1]}-${props.data[0].regDate[2]}`}</td>
            </tr>
            {/* 파일 */}

            {/* 작성자 */}
            <tr>
              <td
                style={{
                  paddingTop: "10%",
                  fontSize: "32px",
                  paddingBottom: "5%",
                }}
              >
                {props.data[0].writer}
              </td>
            </tr>
            {/* 내용 */}
            <tr>
              <td
                style={{
                  width: "100%",
                  paddingTop: "2%",
                  paddingLeft: "2%",
                  fontSize: "24px",
                  paddingBottom: "20%",
                  border: "1px solid black",
                }}
              >
                {props.data[0].content}
              </td>
            </tr>
            {/* 버튼 */}
            <tr>
              <td>
                <div
                  className={classes.button}
                  style={{
                    paddingBottom: "10%",
                    paddingTop: "5%",
                    textAlign: "right",
                  }}
                >
                  <button
                    style={{ margin: "1%" }}
                    onClick={() => router.push(`/`)}
                  >
                    초기 화면
                  </button>
                  <button
                    style={{ margin: "1%" }}
                    className={classes.modifybutton}
                    onClick={() =>
                      router.push(
                        `/banner/OneonOne/modify/${props.data[0].bno}`
                      )
                    }
                  >
                    수정
                  </button>
                  <button
                    style={{ margin: "1%" }}
                    className={classes.deletebutton}
                    onClick={deleteHandler}
                  >
                    삭제
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div style={{ width: "80%", margin: "auto" }}>
        <hr style={{ height: "10px", border: 0, backgroundColor: "black" }} />
        <p style={{ fontSize: "32px", fontWeight: "bold" }}>댓글</p>
        <div>
          <input
            type="testarea"
            className={classes.replyinput}
            ref={replyref}
          />
          <button className={classes.replybutton} onClick={submitHandler}>
            등록
          </button>
        </div>
        <div style={{ marginTop: "5%" }}>
          <ReplyContent reply={props.reply} />
        </div>
      </div>
    </div>
  )
}

export async function getStaticPaths({ params }) {
  //동적 페이지를 구성시키는 과정
  const res = await axios.get("http://localhost:8080/oneonone/board/list")
  const lists = res.data

  //boradSeq를 따로 꺼내서 동적페이지 주소를 미리 만들어둔다
  const paths = lists.map((list) => ({
    params: {
      oneOnOneDetail: String(list.bno),
    },
  }))

  //fallback을 false로 설정해서 위에서 꺼낸 boardSeq가 아니면 에러페이지로 연결시킨다
  return { paths, fallback: false }
}

export async function getStaticProps({ params }) {
  //공지사항 상세 데이터 불러오기
  const fetchdata = await axios.get(
    `http://localhost:8080/oneonone/board/list/${params.oneOnOneDetail}`
  )

  const replydata = await axios.get(
    `http://localhost:8080/oneonone/reply/${params.oneOnOneDetail}`
  )

  const OneonOneData = fetchdata.data

  const OneonReplyData = replydata.data

  //props로 상속시키기
  return {
    props: {
      data: OneonOneData,
      reply: OneonReplyData,
    },
  }
}

export default withRouter(OneonOneDetail)
