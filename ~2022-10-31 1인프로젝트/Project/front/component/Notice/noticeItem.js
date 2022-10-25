import Image from "next/image"
import Link from "next/link"

const NoticeItem = (props) => {
  const ObjData = Object.values(props.datas)

  return (
    <tbody>
      {ObjData.map((data, i) => {
        return (
          <tr key={i}>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {data.boardSeq}
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                <Link href={`/banner/Notice/${data.boardSeq}`}>
                  <a>{data.title}</a>
                </Link>
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {data.regName ? data.regName : "관리자"}
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {data.regDate.split(" ")[0]}
              </div>
            </td>
            <td>
              <div style={{ minHeight: "30px", verticalAlign: "middle" }}>
                {data.fileYn ? (
                  <Image
                    alt="file"
                    src="/images/file.png"
                    layout="fixed"
                    width={15}
                    height={15}
                  />
                ) : (
                  ""
                )}
              </div>
            </td>
          </tr>
        )
      })}
    </tbody>
  )
}

export default NoticeItem
