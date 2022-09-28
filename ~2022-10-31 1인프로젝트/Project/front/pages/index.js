import Head from "next/head"
import Image from "next/image"
import { useEffect } from "react"
import styles from "../styles/Home.module.css"

export default function Home() {
  const fetchdata = async () => {
    try {
      const response = await fetch(
        "https://jsonplaceholder.typicode.com/posts?_limit=6"
      ).then((response) => response.json())
      console.log(response)
    } catch (error) {
      console.log(error.message)
    }
  }

  useEffect(() => {
    fetchdata()
  }, [])

  return <div className={styles.container}></div>
}
