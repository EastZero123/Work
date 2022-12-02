import classes from "../../../styles/schoolmealDetail.module.css"

import { useRouter, withRouter } from "next/router"
import Image from "next/image"
import Head from "next/head"
import axios from "axios"

const SchoolMealDetaul = (props) => {
  const router = useRouter()

  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!props) {
    return <div></div>
  }

  return (
    <div>
      <Head>
        <title>{props.data.title}</title>
      </Head>
      <div className={classes.detail}>
        <table style={{ width: "80%", margin: "auto" }}>
          <thead></thead>
          <tbody>
            {/* 제목, 날짜 */}
            <tr style={{ paddingTop: "5%" }}>
              <td className={classes.title}>{props.data.title}</td>
              <td className={classes.date}>
                {props.data.regDate.split(" ")[0]}
              </td>
              {/* 파일 */}
            </tr>
            {props.data.fileYn === "Y" ? (
              <tr>
                <td>
                  <Image
                    alt="file"
                    src="/images/file.png"
                    layout="fixed"
                    width={30}
                    height={30}
                  />
                </td>
              </tr>
            ) : (
              ""
            )}
            {/* 작성자 */}
            <tr>
              <td className={classes.writer}>{props.data.regName}</td>
            </tr>
            {/* 내용 */}
            <tr>
              <td
                style={{
                  paddingTop: "2%",
                  paddingLeft: "2%",
                  paddingBottom: "20%",
                  border: "1px solid black",
                }}
                dangerouslySetInnerHTML={{ __html: props.data.content }}
              ></td>
            </tr>
            {/* 버튼 */}
            <tr>
              <td>
                <div
                  className={classes.button}
                  style={{ paddingBottom: "10%", paddingTop: "5%" }}
                >
                  <button onClick={() => router.push(`/`)}>초기 화면</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  )
}

export async function getStaticPaths({ params }) {
  const res = await axios.get("http://10.10.10.201:8617/api2/boardlist/4")
  const lists = res.data

  // 가져온 데이터에서 boardSeq만을 꺼내서 동적페이지를 미리 구성한다
  const paths = lists.map((list) => ({
    params: {
      schoolMealDetail: String(list.boardSeq),
    },
  }))

  //fallback을 false로 설정해서 위에서 꺼낸 boardSeq가 아니면 에러페이지로 연결시킨다
  return { paths, fallback: false }
}

export async function getStaticProps({ params }) {
  // 급식게시글 상세 데이터 불러오기
  const fetchdata = await axios.get(
    `http://10.10.10.201:8617/api/cafeteria/${params.schoolMealDetail}`
  )
  const schoolmealData = fetchdata.data

  // props로 상세 데이터를 상속시킨다
  return {
    props: {
      data: schoolmealData,
    },
  }
}

export default withRouter(SchoolMealDetaul)
