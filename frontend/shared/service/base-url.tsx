import axios, { AxiosError } from 'axios';
import { Envs } from '../utils/utils';
import { toast } from 'react-toastify';

interface ErrorResponse {
    error: string
    errorCode: string
    status: number
    [key: string]: any
}

export default function baseAxios() {
    const config = axios.create({
        baseURL: Envs.BASE_URL
    });
    config.interceptors.request.use(function (config) {
        config.headers.Authorization = `Bearer ${''}`;
        return config;
    }, null, { synchronous: true });
    config.interceptors.response.use(function (config) {
        toast.success('Action executed successful!', {});
        return config;
    }, function (config: AxiosError<ErrorResponse, any>) {
        if (config.isAxiosError) {
            toast.error('Action executed failure!', {});
        }
    }, { synchronous: true });
    return config;
}