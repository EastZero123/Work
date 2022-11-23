import Image from "next/image"
import Link from "next/link"

const OneonOneItem = (props) => {
  const ObjData = Object.values(props.datas)
  console.log(ObjData)
  return (
    <tbody>
      {ObjData.map((data, i) => {
        return (
          <tr key={i}>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {data.bno}
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                <Link href={`/banner/OneonOne/${data.bno}`}>
                  <a>{data.title}</a>
                </Link>
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {data.writer ? data.writer : "관리자"}
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {`${data.regDate[0]}-${data.regDate[1]}-${data.regDate[2]}`}
              </div>
            </td>
          </tr>
        )
      })}
    </tbody>
  )
}

export default OneonOneItem
