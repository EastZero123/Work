import Image from "next/image"
import Link from "next/link"

const NoticeItem = (props) => {
  const ObjData = Object.values(props.datas)

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
              <td>
                {data.fileYn ? (
                  <Image
                    alt="file"
                    src="/images/file.png"
                    layout="fixed"
                    width={15}
                    height={15}
                  />
                ) : (
                  "N"
                )}
              </td>
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
