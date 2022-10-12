const SchoolMealItem = (props) => {
  return (
    <tbody>
      {props.datas
        .slice(0)
        .reverse()
        .map((data, i) => {
          return (
            <tr key={i}>
              <td>{data.boardSeq}</td>
              <td>{data.title}</td>
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
