const Filter = ({ onFilter }) => {
  return (
    <select onChange={onFilter}>
      <option value="10">10개씩 보기</option>
      <option value="20">20개씩 보기</option>
      <option value="50">50개씩 보기</option>
    </select>
  )
}

export default Filter
