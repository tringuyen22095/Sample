import HomePage from "components/home-page";
import AboutUs from "components/about-us";
import Header from "components/header";
import OurStory from "components/our-story";
import {
  SnowFall
} from "widgets";
import styles from './styles.module.css';

export default function Home() {
  return (
    <>
      <Header></Header>
      <HomePage></HomePage>
      <AboutUs></AboutUs>
      <OurStory></OurStory>
      {/* back drop */}
      {/* <SnowFall snowElementCount={20}/> */}
    </>
  );
}
