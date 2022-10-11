import Image from "next/image";
import Link from "next/link";
import { useState } from "react";
import classes from "../../styles/header.module.css";

const Header = () => {
  const [visible, setVisible] = useState(false);

  const MouseEnter = () => {
    setVisible(true);
  };

  const MouseLeave = () => {
    setVisible(false);
  };

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
          <li onMouseEnter={MouseEnter} onMouseLeave={MouseLeave}>
            <Link href="/banner/SiteMap/intro">SiteMap</Link>
            {visible && (
              <ul className={classes.header__LNB__UL}>
                <li>
                  <Link href="/banner/SiteMap/intro">
                    <a>Intro</a>
                  </Link>
                </li>
                <li>
                  <Link href="/banner/SiteMap/history">
                    <a>History</a>
                  </Link>
                </li>
                <li>
                  <Link href="/banner/SiteMap/support">
                    <a>Support</a>
                  </Link>
                </li>
              </ul>
            )}
          </li>
          <li>
            <Link href="/banner/Notice">
              <a>Notice</a>
            </Link>
          </li>
          <li>
            <Link href="/banner/SchoolMeal">
              <a>SchoolMeal</a>
            </Link>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
