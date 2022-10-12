import classes from "../../../styles/schoolmealDetail.module.css"

import { useRouter } from "next/router"

const SchoolMealDetaul = () => {
  const router = useRouter()
  console.log(router)
  return (
    <div className={classes.detail}>
      <div className={classes.board}>
        <div className={classes.title}>Title</div>
        <div className={classes.file}></div>
        <div className={classes.content}>Content</div>
      </div>
      <div className={classes.button}></div>
    </div>
  )
}

export default SchoolMealDetaul
