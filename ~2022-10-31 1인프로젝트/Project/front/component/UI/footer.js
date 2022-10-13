import Link from "next/link"
import classes from "../../styles/footer.module.css"

const Footer = () => {
  return (
    <footer style={{ textAlign: "center" }} className={classes.footer}>
      <div className={classes.banner}>MVP</div>
      <div className={classes.text}>
        서울특별시 성동구 성수일로 6길 33, 2층(성수동2가, 아연디지털타워)
        <br /> THE HAPPY ICT FOUNDATION. ALL RIGHTS RESERVED.
      </div>
      <div className={classes.map}>
        <div>
          <Link href="/banner/SiteMap/intro">소개</Link>
        </div>
        <div>
          <Link href="/banner/Notice">공지사항</Link>
        </div>
        <div>
          <Link href="/banner/SchoolMeal">급식 게시글</Link>
        </div>
      </div>
      <div className={classes.icon}></div>
    </footer>
  )
}

export default Footer
