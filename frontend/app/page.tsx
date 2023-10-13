import { MyPage } from '@/shared/layout/types'
import './page.scss'

const LoginPage: MyPage = () => {
  return (
    <>
      <a href='/login'>login</a>
    </>
  );
}

export default LoginPage;
LoginPage.LayoutKey = "Anonymous";
