import Image from "next/image"
import Link from "next/link"
import { useRouter } from "next/router"
import { useState } from "react"
import classes from "../../styles/header.module.css"

const Header = () => {
  const [visible, setVisible] = useState(false)
  const router = useRouter()

  const MouseEnter = () => {
    setVisible(true)
  }

  const MouseLeave = () => {
    setVisible(false)
  }

  return (
    <header className={classes.header}>
      <div className={classes.header__home}>
        <Link href="/">
          <a>
            <Image
              src="/images/Home.png"
              width={40}
              height="40px"
              layout="fixed"
            />
          </a>
        </Link>
      </div>
      <nav className={classes.header__LNB}>
        <ul className={classes.header__LNBItem}>
          <li>
            <Link href="/banner/OneonOne">
              <a
                className={
                  router.pathname === "/banner/OneonOne" ||
                  router.asPath === `/banner/Notice/${router.query.OneonOne}`
                    ? classes.active
                    : ""
                }
              >
                1:1문의
              </a>
            </Link>
          </li>
          <li>
            <Link href="/banner/SchoolMeal">
              <a
                className={
                  router.pathname === "/banner/SchoolMeal" ||
                  router.asPath ===
                    `/banner/SchoolMeal/${router.query.schoolMealDetail}`
                    ? classes.active
                    : ""
                }
              >
                SchoolMeal
              </a>
            </Link>
          </li>
          <li>
            <Link href="/banner/Notice">
              <a
                className={
                  router.pathname === "/banner/Notice" ||
                  router.asPath ===
                    `/banner/Notice/${router.query.noticeDetail}`
                    ? classes.active
                    : ""
                }
              >
                Notice
              </a>
            </Link>
          </li>
          <li onMouseEnter={MouseEnter} onMouseLeave={MouseLeave}>
            <Link href="/banner/SiteMap/intro">
              <a
                className={
                  router.asPath === `/banner/SiteMap/intro` ||
                  router.asPath === `/banner/SiteMap/history` ||
                  router.asPath === `/banner/SiteMap/support`
                    ? classes.active
                    : ""
                }
              >
                About
              </a>
            </Link>
            {visible && (
              <ul className={classes.header__LNB__UL}>
                <li>
                  <Link href="/banner/SiteMap/intro">
                    <a
                      className={
                        router.asPath === `/banner/SiteMap/intro`
                          ? classes.active
                          : ""
                      }
                    >
                      Intro
                    </a>
                  </Link>
                </li>
                <li>
                  <Link href="/banner/SiteMap/history">
                    <a
                      className={
                        router.asPath === `/banner/SiteMap/history`
                          ? classes.active
                          : ""
                      }
                    >
                      History
                    </a>
                  </Link>
                </li>
                <li>
                  <Link href="/banner/SiteMap/support">
                    <a
                      className={
                        router.asPath === `/banner/SiteMap/support`
                          ? classes.active
                          : ""
                      }
                    >
                      Support
                    </a>
                  </Link>
                </li>
              </ul>
            )}
          </li>
        </ul>
      </nav>
    </header>
  )
}

export default Header
