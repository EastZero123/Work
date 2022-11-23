import { useRouter } from "next/router"
import { Table } from "react-bootstrap"
import classes from "../../../styles/oneononeDetail.module.css"

const ReplyContent = (props) => {
  const ObjData = Object.values(props.reply)
  console.log(ObjData)
  return (
    <Table striped>
      <thead></thead>
      <tbody>
        {ObjData.map((data, i) => {
          return (
            <tr key={i}>
              <td>{data.replyWriter}</td>
              <td>{data.replyText}</td>
              <td>
                {`${data.updateDate[0]}-${data.updateDate[1]}-${data.updateDate[2]}`}
              </td>
            </tr>
          )
        })}
      </tbody>
    </Table>
  )
}

export default ReplyContent
