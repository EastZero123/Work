import { useRouter } from "next/router"
import { useEffect, useState } from "react"

const NoticeDetail = () => {
  const [data, setData] = useState()
  const router = useRouter()
  const query = router.query.noticeDetail

  console.log(query)

  const fetchdata = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api2/board/${query}`).then(
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
      <div>{data.title}</div>
    </div>
  )
}

export default NoticeDetail
