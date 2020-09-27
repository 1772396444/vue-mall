import axios from 'axios';
import router from '@/router';
import store from '@/store';

import * as antd from 'ant-design-vue';

const server = axios.create({
    baseURL: process.env.BASE_API,
    timeout: 5 * 1000
});

server.defaults.headers['authorization'] = '';

server.interceptors.request.use(
    config => {
        config.data = JSON.stringify(config.data);
        config.headers = {
            'Content-Type': 'application/json'
        };
        if (store.getters.token) {
            config.headers.authorization = store.getters.token;
        }
        return config;
    },
    error => {
        console.error(error);
        return Promise.reject(error.response);
    }
);

server.interceptors.response.use(
    response => {
        console.log(response);
        if (response.data.code !== 200) {
            if(Object.keys(responseCode).includes(JSON.stringify(response.data.code))){
                antd.message.error(responseCode[response.data.code]);
            }else if (Object.keys(tokenCode).includes(JSON.stringify(response.data.code))) {
                router.replace('/login');
                store.commit('token', '');
                localStorage.removeItem('MALL_TOKEN');
                antd.message.error(responseCode[tokenCode.data.code]);
            }else{
                antd.message.error(response.data.message);
            }
        }
        return response.data;
    },
    error => {
        console.error(error);
        return Promise.reject(error.response);
    }
);

const tokenCode = {
    401: '未授权，请重新登录'
}

const responseCode = {
    400: '错误请求',
    403: '拒绝访问',
    404: '请求错误,未找到该资源',
    405: '请求方法未允许',
    408: '请求超时'
};

const request = {

    get (url, params) {
        const config = {
            method: 'get',
            url
        };
        if (params) config.data = params;
        return server(config);
    },

    post (url, params) {
        const config = {
            method: 'post',
            url
        };
        if (params) config.data = params;
        return server(config);
    }

};

export default request;
