import Link from "next/link"
import { useRouter } from "next/router"
import classes from "./sitempNav.module.css"

const SiteMapNav = () => {
  const router = useRouter()
  return (
    <div className={classes.SideNav}>
      <table>
        <thead>
          <tr>
            <th className={classes.SideMain}>MVP</th>
          </tr>
          <tr>
            <td>
              <Link href={"/banner/SiteMap/intro"}>
                <a
                  className={
                    router.pathname === "/banner/SiteMap/intro"
                      ? classes.active
                      : ""
                  }
                >
                  Intro
                </a>
              </Link>
            </td>
          </tr>
          <tr>
            <td>
              <Link href={"/banner/SiteMap/history"}>
                <a
                  className={
                    router.pathname === "/banner/SiteMap/history"
                      ? classes.active
                      : ""
                  }
                >
                  History
                </a>
              </Link>
            </td>
          </tr>
          <tr>
            <td>
              <Link href={"/banner/SiteMap/support"}>
                <a
                  className={
                    router.pathname === "/banner/SiteMap/support"
                      ? classes.active
                      : ""
                  }
                >
                  Support
                </a>
              </Link>
            </td>
          </tr>
        </thead>
      </table>
    </div>
  )
}

export default SiteMapNav
