import { useRouter } from "next/router"

const NoticeDetail = () => {
  const router = useRouter()
  console.log(router)
  return (
    <div>
      <div>Detail</div>
    </div>
  )
}

export default NoticeDetail
