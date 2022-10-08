import classes from "../../styles/header.module.css";

const Header = () => {
  return (
    <header className={classes.header}>
      <div className={classes.header__home}>Logo</div>
      <nav className={classes.header__LNB}>
        <ul className={classes.header__LNBItem}>
          <li>SiteMap</li>
          <li>Notice</li>
          <li>SchoolMeal</li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
