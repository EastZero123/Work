import React from "react"
import classes from "../../styles/notice.module.css"

const Pagination = ({ postsPerPage, totalPosts, paginate, page }) => {
  const pageNumbers = []
  for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
    pageNumbers.push(i)
  }
  return (
    <div className={classes.paginations}>
      <nav>
        <div className="pagination">
          {pageNumbers.map((number) => (
            <div key={number}>
              <div className={`page-item `}>
                <div
                  onClick={() => paginate(number)}
                  className={`page-link ${classes.pagecontent} ${
                    page === number ? classes.active : ""
                  }`}
                >
                  {number}
                </div>
              </div>
            </div>
          ))}
        </div>
      </nav>
    </div>
  )
}

export default Pagination
