import { Carousel, Col, Container, Row } from "react-bootstrap"

const CarouselHome = () => {
  return (
    <Container>
      <Row>
        <Col md={{ span: 12, offset: 0 }}>
          <Carousel>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="/images/carousel1.png"
                alt="carousel1"
                height={400}
              />
            </Carousel.Item>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="/images/carousel2.png"
                alt="carousel2"
                height={400}
              />
            </Carousel.Item>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="/images/carousel3.png"
                alt="carousel3"
                height={400}
              />
            </Carousel.Item>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="/images/carousel4.png"
                alt="carousel4"
                height={400}
              />
            </Carousel.Item>
            <Carousel.Item>
              <img
                className="d-block w-100"
                src="/images/carousel5.png"
                alt="carousel5"
                height={400}
              />
            </Carousel.Item>
          </Carousel>
        </Col>
      </Row>
    </Container>
  )
}

export default CarouselHome
