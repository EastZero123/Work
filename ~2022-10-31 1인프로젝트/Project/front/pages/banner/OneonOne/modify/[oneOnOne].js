import axios from "axios"
import { useRouter, withRouter } from "next/router"
import { useState } from "react"
import { Button, Col, Container, Form, InputGroup, Row } from "react-bootstrap"

const OneonOneModify = (props) => {
  const [title, setTitle] = useState(props.data[0].title)
  const [content, setContent] = useState(props.data[0].content)
  const router = useRouter()
  console.log(props.data)

  const updateSubmitHandler = async (ModifyJSONData) => {
    try {
      await fetch(
        `http://localhost:8080/oneonone/board/update/${props.data[0].bno}`,
        {
          // localhost:3000/
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(ModifyJSONData),
        }
      )
        .then((response) => console.log(response))
        .then((data) => console.log(data))
    } catch (e) {}
  }

  const submitHandler = (e) => {
    e.preventDefault()

    if (content.trim() != "" && title.trim() != "") {
      const ModifyJSONData = {
        content,
        title,
      }
      try {
        updateSubmitHandler(ModifyJSONData)
        router.push(`/banner/OneonOne/${props.data[0].bno}`)
      } catch (error) {}
    } else {
      alert("모두 기입해주세요")
    }
  }

  if (!props) {
    return <div></div>
  }
  return (
    <div>
      <div style={{ width: "90%", margin: "auto" }}>
        <p style={{ fontSize: "32px", fontWeight: "bold" }}>문의 수정</p>
        <hr style={{ height: "10px", border: 0, backgroundColor: "grey" }} />
      </div>
      <div style={{ marginTop: "5%" }}>
        <Container>
          <Row style={{ marginBottom: "2%" }}>
            <Col md={{ span: 4 }}>
              <InputGroup>
                <Form.Control
                  aria-label="First name"
                  placeholder="제목"
                  size="lg"
                  value={title}
                  onChange={(e) => setTitle(e.currentTarget.value)}
                />
              </InputGroup>
            </Col>
            <Col md={{ span: 4, offset: 4 }}>
              <InputGroup>
                <Form.Control
                  aria-label="First name"
                  placeholder={props.data[0].writer}
                  size="lg"
                  disabled
                />
              </InputGroup>
            </Col>
          </Row>
          <Row>
            <InputGroup>
              <InputGroup.Text>내용</InputGroup.Text>
              <Form.Control
                as="textarea"
                rows={8}
                aria-label="With textarea"
                value={content}
                onChange={(e) => setContent(e.currentTarget.value)}
              />
            </InputGroup>
          </Row>
        </Container>
        <Button
          variant="info"
          style={{ float: "right", marginTop: "5%", marginRight: "10%" }}
          onClick={submitHandler}
        >
          수정 완료
        </Button>
      </div>
    </div>
  )
}

export async function getStaticPaths({ params }) {
  //동적 페이지를 구성시키는 과정
  const res = await axios.get("http://localhost:8080/oneonone/board/list")
  const lists = res.data

  //boradSeq를 따로 꺼내서 동적페이지 주소를 미리 만들어둔다
  const paths = lists.map((list) => ({
    params: {
      oneOnOne: String(list.bno),
    },
  }))

  //fallback을 false로 설정해서 위에서 꺼낸 boardSeq가 아니면 에러페이지로 연결시킨다
  return { paths, fallback: false }
}

export async function getStaticProps({ params }) {
  //공지사항 상세 데이터 불러오기
  const fetchdata = await axios.get(
    `http://localhost:8080/oneonone/board/list/${params.oneOnOne}`
  )

  const OneonOneData = fetchdata.data

  //props로 상속시키기
  return {
    props: {
      data: OneonOneData,
    },
  }
}

export default withRouter(OneonOneModify)
