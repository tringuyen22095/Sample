import React, { useState, useEffect } from 'react';
import * as Yup from "yup";
import "./login.scss";

const validationSchema = Yup.object({
  username: Yup.string().required("Username is required."),
  password: Yup.string().required("Password is required.")
});

const formGroupBuilder = {
  username: '',
  password: ''
}

const Login: React.FC = () => {
  const [formGroup, setFormGroup] = useState(formGroupBuilder);

  useEffect(() => {

  }, []);

  function onChange(event: React.BaseSyntheticEvent) {
    console.log(event)
  }

  function onSubmit() {
    console.log(formGroup);
  }

  return (
    <div className='login'>
      <div className='row'>
        <div className='col-12'>
          <label htmlFor='username'>Username</label>
          <br/>
          <input type='text' id='username' name='username' onChange={onChange}/>
        </div>
      </div>
      <div className='row'>
        <div className='col-12'>
          <label htmlFor='password'>Password</label>
          <br/>
          <input type='password' id='password' name='password' onChange={onChange}/>
        </div>
      </div>
      <div className='row'>
        <div className='col-12 d-flex justify-content-center align-content-center'>
          <button type='button' onClick={onSubmit}>Submit</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
