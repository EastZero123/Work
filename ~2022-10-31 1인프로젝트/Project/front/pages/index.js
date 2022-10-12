import Head from "next/head"
import Image from "next/image"
import { useEffect } from "react"
import HomeContent from "../component/Home/home"
import Layout from "../component/UI/Layout"
import "bootstrap/dist/css/bootstrap.css"
import axios from "axios"

const Home = (props) => {
  // const fetchdata = async () => {
  //   try {
  //     const response = await fetch(
  //       "https://jsonplaceholder.typicode.com/posts?_limit=6"
  //     ).then((response) => response.json());
  //     console.log(response);
  //   } catch (error) {
  //     console.log(error.message);
  //   }
  // };

  // useEffect(() => {
  //   fetchdata();
  // }, []);

  return (
    <div>
      <HomeContent notice={props.notice} schoolmeal={props.schoolmeal} />
    </div>
  )
}

export async function getStaticProps() {
  const notice = await axios.get("http://localhost:8080/api2/boardlist/5")

  const noticeData = notice.data

  const schoolmeal = await axios.get("http://localhost:8080/api2/boardlist/4")

  const schoolmealData = schoolmeal.data

  return {
    props: {
      notice: noticeData,
      schoolmeal: schoolmealData,
    },
  }
}

export default Home
