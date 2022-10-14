import Image from "next/image"
import { useRouter } from "next/router"
import { useEffect, useState } from "react"
import classes from "../../../styles/noticeDetail.module.css"

const NoticeDetail = () => {
  const [data, setData] = useState()
  const router = useRouter()

  //동적 페이지 경로 [noticeDetail] 값 추출
  const query = router.query.noticeDetail

  // console.log(query)

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

  console.log(data)
  if (!data) {
    return <div></div>
  }
  return (
    <div>
      <div className={classes.detail}>
        <table style={{ width: "80%", margin: "auto" }}>
          <thead></thead>
          <tbody style={{}}>
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
            <tr>
              <td style={{ paddingTop: "10%", fontSize: "32px" }}>
                {data.regName}
              </td>
            </tr>
            <tr>
              <td
                style={{
                  paddingTop: "10%",
                  fontSize: "24px",
                  paddingBottom: "20%",
                }}
              >
                {data.content}
              </td>
            </tr>
            <tr>
              <td>
                <div
                  className={classes.button}
                  style={{ paddingBottom: "10%" }}
                >
                  <button>이전으로</button>
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
