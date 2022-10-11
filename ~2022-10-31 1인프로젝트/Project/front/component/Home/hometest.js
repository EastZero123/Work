import Image from "next/image"
// import classes from "./home.module.css"

const HomeContent = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  }

  return (
    <div className={classes.slider}>
      <div className={classes.slides}>
        <div>
          <Image
            src="/image/carousel1.png"
            alt="image1"
            width={500}
            height={300}
            layout="fixed"
            className={classes.image}
          />
        </div>
        <div>
          <Image
            src="/image/carousel2.png"
            alt="image2"
            width={500}
            height={300}
            layout="fixed"
            className={classes.image}
          />
        </div>
        <div>
          <Image
            src="/image/carousel3.png"
            alt="image3"
            width={500}
            height={300}
            layout="fixed"
            className={classes.image}
          />
        </div>
        <div>
          <Image
            src="/image/carousel4.png"
            alt="image4"
            width={500}
            height={300}
            layout="fixed"
            className={classes.image}
          />
        </div>
        <div>
          <Image
            src="/image/carousel5.png"
            alt="image5"
            width={500}
            height={300}
            layout="fixed"
            className={classes.image}
          />
        </div>
      </div>
      <a href="#slide-1">1</a>
      <a href="#slide-2">2</a>
      <a href="#slide-3">3</a>
    </div>
  )
}

export default HomeContent
