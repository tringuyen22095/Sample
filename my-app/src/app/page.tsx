import HomePage from 'components/home-page';
import AboutUs from 'components/about-us';
import Header from 'components/header';
import OurStory from 'components/our-story';
import Gallery from 'components/gallery';
import GuestBook from 'components/guestBook';
import {
  SnowFall
} from 'widgets';
import { Fragment } from 'react';


export default function Home() {
  return (
    <Fragment>
      <Header></Header>
      <HomePage></HomePage>
      <AboutUs></AboutUs>
      <OurStory></OurStory>
      <Gallery></Gallery>
      <GuestBook></GuestBook>
      {/* back drop */}
      {/* <SnowFall snowElementCount={20}/> */}
    </Fragment>
  );
}
