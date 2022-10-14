import Image from "next/image"
import Link from "next/link"

const SchoolMealItem = (props) => {
  return (
    <tbody>
      {!props.visible
        ? props.dateArr
            .slice(0)
            .reverse()
            .map((data, i) => {
              return (
                <tr key={i}>
                  <td>
                    <div style={{ minHeight: "30px" }}>{data.boardSeq}</div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      <Link href={`/banner/SchoolMeal/${data.boardSeq}`}>
                        <a style={{ textDecoration: "none", color: "black" }}>
                          {data.title}
                        </a>
                      </Link>
                    </div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      {data.regName ? data.regName : "관리자"}
                    </div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      {data.regDate.split(" ")[0]}
                    </div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      {data.fileYn ? "Y" : ""}
                    </div>
                  </td>
                </tr>
              )
            })
        : props.datas
            .slice(0)
            .reverse()
            .map((data, i) => {
              return (
                <tr key={i}>
                  <td>
                    <div style={{ minHeight: "30px" }}>{data.boardSeq}</div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      <Link href={`/banner/SchoolMeal/${data.boardSeq}`}>
                        <a style={{ textDecoration: "none", color: "black" }}>
                          {data.title}
                        </a>
                      </Link>
                    </div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      {data.regName ? data.regName : "관리자"}
                    </div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
                      {data.regDate.split(" ")[0]}
                    </div>
                  </td>
                  <td>
                    <div style={{ minHeight: "30px" }}>
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

export default SchoolMealItem
