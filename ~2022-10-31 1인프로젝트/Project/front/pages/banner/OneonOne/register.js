import { useState } from "react"

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
    } catch {}
  }

  const submitHandler = (e) => {
    e.preventDefault()

    const RegisterJSONData = {
      content,
      writer,
      title,
    }

    RegisterHandler(RegisterJSONData)
  }

  return (
    <div>
      <input
        type="text"
        name="contet"
        placeholder="content"
        value={content}
        onChange={(e) => setContent(e.currentTarget.value)}
      />
      <input
        type="text"
        name="writer"
        placeholder="writer"
        value={writer}
        onChange={(e) => setWriter(e.currentTarget.value)}
      />
      <input
        type="text"
        name="title"
        placeholder="title"
        value={title}
        onChange={(e) => setTitle(e.currentTarget.value)}
      />
      <button onClick={submitHandler}>등록</button>
    </div>
  )
}

export default OneonOneRegister
