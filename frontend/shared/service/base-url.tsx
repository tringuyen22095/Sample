import axios, { AxiosError } from 'axios';

interface ErrorResponse {
    error: string
    errorCode: string
    status: number
    [key: string]: any
}

export default function baseAxios() {
    const config = axios.create({
        baseURL: `http://nbs.int.crif.com:80/`
    });
    config.interceptors.request.use(function (config) {
        config.headers.Authorization = `Bearer ${''}`;
        return config;
    }, null, { synchronous: true });
    config.interceptors.response.use(function (config) {
        console.log("Called successful");
        return config;
    }, function (config: AxiosError<ErrorResponse, any>) {
        if (config.isAxiosError) {
            console.log("Called error");
        }
    }, { synchronous: true });
    return config;
}