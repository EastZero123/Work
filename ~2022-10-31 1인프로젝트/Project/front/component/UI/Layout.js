import { Fragment } from "react";
import Footer from "./footer";
import Header from "./header";
import classes from "../../styles/footer.module.css";

const Layout = (props) => {
  return (
    <Fragment className={classes.body_wrapper}>
      <Header />
      <div className={classes.body_content}>{props.children}</div>
      <Footer className={classes.footer} />
    </Fragment>
  );
};

export default Layout;
