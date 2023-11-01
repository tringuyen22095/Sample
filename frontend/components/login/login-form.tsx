import React, { Dispatch, SetStateAction } from 'react';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import './login-form.scss';
import { useForm } from 'react-hook-form';
import { Login } from "@mui/icons-material";
import { OutlinedInput, InputLabel, FormControl, Button, Checkbox, FormGroup, FormControlLabel } from '@mui/material';
import { show, hide } from "@/shared/common/loading/loading-slice";
import { useAppDispatch } from '@/shared/redux/store';
import { useRouter } from "next/navigation";
import * as authService from "@/shared/service/authentication.service";
import { Envs } from "@/shared/utils/utils";

const schema = Yup.object().shape({
  username: Yup.string().required('Username is required.'),
  password: Yup.string().required('Password is required.'),
  rememberMe: Yup.boolean().optional()
});

const initFormValues = {
  username: Envs.USERNAME || '',
  password: Envs.PASSWORD || '',
  rememberMe: false
};

interface Props {}

const LoginForm = (props: Props) => {
  const dispatch = useAppDispatch();
  const router = useRouter();

  const { register, handleSubmit, reset, setError, watch, setFocus, getValues, trigger, setValue, formState: { errors, isDirty, isSubmitted, isValid } } = useForm({
    mode: 'all',
    defaultValues: initFormValues,
    criteriaMode: 'all',
    resolver: yupResolver(schema)
  });

  const onSubmit = async (data) => {
    try {
      if (isValid) {
        dispatch(show());
        const response = await authService.signIn(data);
        if (response && response.data.authorization)
          router.replace('/dashboard');
      }
    } finally {
      dispatch(hide());
    }
  }

  return (
    <div className='login'>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className='row'>
          <div className='col-12 d-flex flex-column justify-content-center align-content-center'>
            <h2>Sign In</h2>
          </div>
        </div>
        <div className='row'>
          <div className='col-12'>
            <FormControl variant="outlined">
              <InputLabel htmlFor='username'>Username</InputLabel>
              <OutlinedInput type='text'
                label='Username'
                id='username'
                name='username'
                {...register('username')}
                error={errors.username?.message ? true : false} />
              <span className='error-msg'>{errors.username?.message}</span>
            </FormControl>
          </div>
        </div>
        <div className='row'>
          <div className='col-12'>
            <FormControl variant="outlined">
              <InputLabel htmlFor='password'>Password</InputLabel>
              <OutlinedInput type='password'
                label='Password'
                id='password'
                name='password'
                {...register('password')}
                error={errors.password?.message ? true : false} />
              <span className='error-msg'>{errors.password?.message}</span>
            </FormControl>
          </div>
        </div>
        <div className='row'>
          <div className='col-12 d-flex flex-column justify-content-center align-content-center'>
            <FormGroup>
              <FormControlLabel label="Remember me?"
                control={<Checkbox {...register('rememberMe')} />} />
            </FormGroup>
            <Button type='submit'
              variant='contained'
              size='large'
              disabled={!isValid}
              endIcon={<Login />}
              color='primary'>Sign In</Button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
