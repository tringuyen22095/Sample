import HomePage from 'components/home-page';
import AboutUs from 'components/about-us';
import Header from 'components/header';
import OurStory from 'components/our-story';
import Gallery from 'components/gallery';
import GuestBook from 'components/guest-book';
import ThankYou from 'components/thank-you';
import {
  SnowFall
} from 'widgets';
import { Fragment } from 'react';

export default function Home() {

  return (
    <Fragment>
      <SnowFall snowElementCount={8}/>
      <Header></Header>
      <HomePage></HomePage>
      <AboutUs></AboutUs>
      <OurStory></OurStory>
      <Gallery></Gallery>
      <GuestBook></GuestBook>
      <ThankYou></ThankYou>
    </Fragment>
  );
}
