import Vue from 'vue';
import App from './App';

import store from './store';
import router from './router';
import utils from './utils';

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

import less from 'less';
import './system.less';

Vue.config.productionTip = false;

// 注册antd
Vue.use(Antd);
Vue.use(less);

Vue.prototype.$utils = utils;

new Vue({
    store,
    router,
    components: {App},
    template: '<App/>'
}).$mount(document.getElementById('app'));
