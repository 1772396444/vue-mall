import Vue from 'vue';
import Router from 'vue-router';

import Login from '@/components/pager/login';
import NotFound from '@/components/common/error/404';

Vue.use(Router);

const ROUTE_MODE = 'history';
const ROUTE_BASE = '/';

// 默认路由键
// const defaultRoutes = [
//     {
//         path: '/',
//         redirect: 'index'
//     }, {
//         path: '/index',
//         name: 'index',
//         component: Login
//     }, {
//         path: '*',
//         name: '404',
//         component: NotFound
//     }
// ];

// 新建默认路由
const router = new Router({
    mode: ROUTE_MODE,
    base: ROUTE_BASE,
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login
        }
    ],
    scrollBehavior: () => ({x: 0, y: 0})
});

// 标记刷新，避免刷新导致页面路由死循环
let refresh = true;

router.beforeEach((to, from, next) => {
    if (refresh) {
        next();
    }
});

export default router;
