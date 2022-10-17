import classes from "../../../styles/schoolmealDetail.module.css"

import { useRouter } from "next/router"
import { useEffect, useState } from "react"
import Image from "next/image"
import Head from "next/head"

const SchoolMealDetaul = () => {
  const router = useRouter()
  const [data, setData] = useState()

  // 동적 페이지 [schoolMealDetail] 값 얻어오기
  const query = router.query.schoolMealDetail

  // 필요한 데이터 가져오기
  const fetchdata = async () => {
    try {
      const res = await fetch(
        `http://localhost:8080/api/cafeteria/${query}`
      ).then((response) => response.json())
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
      <Head>
        <title>{data.title}</title>
      </Head>
      <div className={classes.detail}>
        <table style={{ width: "80%", margin: "auto" }}>
          <thead></thead>
          <tbody>
            {/* 제목, 날짜 */}
            <tr style={{ paddingTop: "5%" }}>
              <td className={classes.title}>{data.title}</td>
              <td className={classes.date}>{data.regDate.split(" ")[0]}</td>
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
              <td className={classes.writer}>{data.regName}</td>
            </tr>
            {/* 내용 */}
            <tr>
              <td
                style={{
                  paddingTop: "2%",
                  paddingLeft: "2%",
                  // fontSize: "24px",
                  paddingBottom: "20%",
                  border: "1px solid black",
                }}
                dangerouslySetInnerHTML={{ __html: data.content }}
              ></td>
            </tr>
            {/* 버튼 */}
            <tr>
              <td>
                <div
                  className={classes.button}
                  style={{ paddingBottom: "10%", paddingTop: "5%" }}
                >
                  <button onClick={() => router.push("/banner/SchoolMeal")}>
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

export default SchoolMealDetaul
