import PaegOne from "components/home";
import PaegTwo from "components/page-two";
import Header from "components/header";
import {
  SnowFall
} from "widgets";
import styles from './styles.module.css';

export default function Home() {
  return (
    <>
      <Header></Header>
      <PaegOne></PaegOne>
      <PaegTwo></PaegTwo>
      {/* back drop */}
      {/* <SnowFall snowElementCount={20}/> */}
    </>
  );
}
