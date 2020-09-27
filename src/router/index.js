import Vue from 'vue';
import Router from 'vue-router';

import Login from '@components/pager/login';
import NotFound from '@components/pager/exception/404';

Vue.use(Router);

const ROUTE_MODE = 'history';
const ROUTE_BASE = '/';

// 默认路由键
const defaultRoutes = [
    {
        path: '/',
        redirect: 'index'
    }, {
        path: '/index',
        name: 'index',
        component: () => import('@components')
    }, {
        path: '*',
        name: '404',
        component: NotFound
    }
];

// 新建默认路由
const router = new Router({
    mode: ROUTE_MODE,
    base: ROUTE_BASE,
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login
        }
    ],
    scrollBehavior: () => ({x: 0, y: 0})
});

// 标记刷新，避免刷新导致页面路由死循环
let refresh = true;

router.beforeEach((to, from, next) => {
    if(!router.app.$options.store.getters.token){
        if(to.path !== '/login'){
            return next('/login');
        }
    } else if(refresh){
        // 动态注册路由
        defaultRoutes.forEach((menu) => {
            if(menu.path === '/index' && router.app.$options.store.getters.routes){
                if(!menu.children){
                    menu.children = [];
                }
                router.app.$options.store.getters.routes.forEach(({ id , path , name }) => {
                    let page = NotFound;
                    try{
                        require(process.env.PAGE_URL + path + '/index.vue').default;
                        page = () => import(process.env.PAGE_URL + path + '/index.vue');
                    }catch(err) {
                        // console.warn(path + '/index.vue 不存在!' + err);
                    }
                    menu.children.push({
                        id,
                        path,
                        name: name,
                        component: page
                    });
                });
            }
        })
        refresh = false;
        router.addRoutes(defaultRoutes);
        router.options.routes = [...defaultRoutes];
        return next({ ...to , replace: true });
    }
    next();
});

export default router;
