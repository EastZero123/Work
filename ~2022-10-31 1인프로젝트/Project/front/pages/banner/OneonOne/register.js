import { useState } from "react"
import { Button, Col, Container, Form, InputGroup, Row } from "react-bootstrap"

const OneonOneRegister = () => {
  const [content, setContent] = useState()
  const [writer, setWriter] = useState()
  const [title, setTitle] = useState()

  const RegisterHandler = async (RegisterJSONData) => {
    try {
      await fetch("http://localhost:8080/oneonone/board/register", {
        // localhost:3000/
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(RegisterJSONData),
      })
        .then((response) => console.log(response))
        .then((data) => console.log(data))
    } catch (e) {}
  }

  const submitHandler = (e) => {
    e.preventDefault()

    if (content.trim() != "" && title.trim() != "" && writer.trim() != "") {
      const RegisterJSONData = {
        content,
        writer,
        title,
      }
      try {
        RegisterHandler(RegisterJSONData)
        router.push(`/banner/OneonOne`)
      } catch (error) {}
    } else {
      alert("모두 기입해주세요")
    }
  }

  return (
    <div>
      <div style={{ width: "90%", margin: "auto" }}>
        <p style={{ fontSize: "32px", fontWeight: "bold" }}>문의 작성</p>
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
                  placeholder="작성자"
                  size="lg"
                  value={writer}
                  onChange={(e) => setWriter(e.currentTarget.value)}
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
          onClick={submitHandler}
          style={{ float: "right", marginTop: "5%", marginRight: "15%" }}
        >
          등록
        </Button>
      </div>
    </div>
  )
}

export default OneonOneRegister
