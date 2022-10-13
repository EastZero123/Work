import classes from "../../../styles/schoolmealDetail.module.css"

import { useRouter } from "next/router"
import { useEffect, useState } from "react"

const SchoolMealDetaul = () => {
  const router = useRouter()
  const [data, setData] = useState()

  const query = router.query.schoolMealDetail
  console.log(query)

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

  console.log(data)

  if (!data) {
    return <div></div>
  }
  return (
    <div className={classes.detail}>
      <div className={classes.board}>
        <div className={classes.title}>{data.title}</div>
        <div className={classes.file}></div>
        <div
          className={classes.content}
          dangerouslySetInnerHTML={{ __html: data.content }}
        ></div>
      </div>
      <div className={classes.button}></div>
    </div>
  )
}

export default SchoolMealDetaul
