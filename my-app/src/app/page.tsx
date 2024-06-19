import PaegOne from "components/page-one";
import PaegTwo from "components/page-two";
import {
  SnowFall
} from "widgets";
import styles from './styles.module.css';

export default function Home() {
  return (
    <>
      <PaegOne></PaegOne>
      <PaegTwo></PaegTwo>
      {/* back drop */}
      {/* <SnowFall snowElementCount={20}/> */}
    </>
  );
}
