import Link from "next/link"

const SchoolMealItem = (props) => {
  // console.log(typeof props.datas)
  return (
    <tbody>
      {!props.visible
        ? props.dateArr
            .slice(0)
            .reverse()
            .map((data, i) => {
              return (
                <tr key={i}>
                  <td>{data.boardSeq}</td>
                  <td>
                    <Link href={`/banner/SchoolMeal/${data.boardSeq}`}>
                      <a style={{ textDecoration: "none", color: "black" }}>
                        {data.title}
                      </a>
                    </Link>
                  </td>
                  <td>{data.regName ? data.regName : "관리자"}</td>
                  <td>{data.regDate.split(" ")[0]}</td>
                  <td>{data.fileYn ? "Y" : ""}</td>
                </tr>
              )
            })
        : props.datas
            .slice(0)
            .reverse()
            .map((data, i) => {
              return (
                <tr key={i}>
                  <td>{data.boardSeq}</td>
                  <td>
                    <Link href={`/banner/SchoolMeal/${data.boardSeq}`}>
                      <a style={{ textDecoration: "none", color: "black" }}>
                        {data.title}
                      </a>
                    </Link>
                  </td>
                  <td>{data.regName ? data.regName : "관리자"}</td>
                  <td>{data.regDate.split(" ")[0]}</td>
                  <td>{data.fileYn ? "Y" : ""}</td>
                </tr>
              )
            })}
    </tbody>
  )
}

export default SchoolMealItem
