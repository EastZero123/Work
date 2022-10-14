import Image from "next/image"
import { useRouter } from "next/router"
import { useEffect, useState } from "react"
import classes from "../../../styles/noticeDetail.module.css"

const NoticeDetail = () => {
  const [data, setData] = useState()
  const router = useRouter()

  //동적 페이지 경로 [noticeDetail] 값 추출
  const query = router.query.noticeDetail

  // 공지사항 디테일 데이터 가져오기
  const fetchdata = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api/notice/${query}`).then(
        (response) => response.json()
      )
      setData(res)
    } catch {}
  }

  useEffect(() => {
    fetchdata()
  }, [])

  // 데이터 불러오는 동안 빈 화면으로 대체하기
  // 이 작업을 안하면 데이터를 받기도 전에 화면에 뿌릴려고 해서 에러가 난다
  if (!data) {
    return <div></div>
  }
  return (
    <div>
      <div className={classes.detail}>
        {/* 테이블 전치 */}
        <table style={{ width: "80%", margin: "auto" }}>
          <thead></thead>
          <tbody>
            {/* 제목, 날짜 */}
            <tr style={{ paddingTop: "5%" }}>
              <td
                style={{
                  paddingTop: "5%",
                  fontSize: "48px",
                  fontWeight: "bold",
                }}
              >
                {data.title}
              </td>
              <td style={{ float: "right", paddingTop: "50%" }}>
                {data.regDate.split(" ")[0]}
              </td>
              {/* 파일 */}
            </tr>
            {data.fileYn === "Y" ? (
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
              <td
                style={{
                  paddingTop: "10%",
                  fontSize: "32px",
                  paddingBottom: "5%",
                }}
              >
                {data.regName}
              </td>
            </tr>
            {/* 내용 */}
            <tr>
              <td
                style={{
                  paddingTop: "2%",
                  paddingLeft: "2%",
                  fontSize: "24px",
                  paddingBottom: "20%",
                  border: "1px solid black",
                }}
              >
                {data.content}
              </td>
            </tr>
            {/* 버튼 */}
            <tr>
              <td>
                <div
                  className={classes.button}
                  style={{ paddingBottom: "10%", paddingTop: "5%" }}
                >
                  <button onClick={() => router.push("/banner/Notice")}>
                    이전으로
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default NoticeDetail
