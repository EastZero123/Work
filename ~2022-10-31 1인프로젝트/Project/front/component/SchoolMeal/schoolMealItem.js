import Link from "next/link"

const SchoolMealItem = (props) => {
  console.log(props.datas[0].regDate)
  return (
    <tbody>
      {props.datas
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
              <td>{data.regName ? data.regName : "None"}</td>
              <td>{data.regDate}</td>
              <td>{data.fileYn ? "Y" : "N"}</td>
            </tr>
          )
        })}
    </tbody>
  )
}

export default SchoolMealItem
