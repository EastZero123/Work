import "react-calendar/dist/Calendar.css"
import Calendar from "react-calendar"
import { useState } from "react"
import classes from "./schoolmeal.module.css"

const SchoolMeal = () => {
  const [value, setValue] = useState(new Date())

  return (
    <div>
      <div className={classes.board}>
        <table>
          <thead>
            <tr>
              <th></th>
            </tr>
          </thead>
        </table>
      </div>
      <div className={classes.calendar}>
        <Calendar onChange={setValue} value={value} />
      </div>
    </div>
  )
}

export default SchoolMeal
