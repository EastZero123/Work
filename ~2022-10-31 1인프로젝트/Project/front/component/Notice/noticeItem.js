import Link from "next/link"
import { format } from "path"

const NoticeItem = (props) => {
  //   console.log(JSON.stringify(Object.values(props.datas)))
  //   console.log(Object.values(props.datas[0]))

  const ObjData = Object.values(props.datas)

  console.log(ObjData)

  //   const arr = () => {
  //     for (var i = 0; i < props.datas.length; i++) {
  //       return (
  //         <tr>
  //           <td>1</td>
  //           <td>2</td>
  //           <td>3</td>
  //           <td>4</td>
  //           <td>5</td>
  //         </tr>
  //       )
  //     }
  //   }
  return (
    <tbody>
      {ObjData.slice(0)
        .reverse()
        .map((data, i) => {
          return (
            <tr key={i}>
              <td>{data.boardSeq}</td>
              <td>
                <Link href={`/banner/Notice/${data.boardSeq}`}>
                  <a>{data.title}</a>
                </Link>
              </td>
              <td>{data.regName ? data.regName : "None"}</td>
              <td>{data.regDate}</td>
              <td>{data.fileYn ? "Y" : "N"}</td>
            </tr>
          )
        })}
    </tbody>
  )
}

// export async function getStaticProps() {
//   const res = await axios.get("http://localhost:8080/api2/boardlist/5")

//   const data = res.data

//   return {
//     props: {
//       datas: data,
//     },
//   }
// }

export default NoticeItem
