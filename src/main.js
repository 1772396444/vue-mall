import Vue from 'vue';
import App from './App';

import store from './store';
import router from './router';
import utils from './utils';

import { Modal } from 'ant-design-vue';

import less from 'less';

Vue.config.productionTip = false;

Vue.use(less);
Vue.use(Modal);

Vue.prototype.$utils = utils;

Vue.directive('permission' , {
    // 创建父节点时调用，由于bind发生在这之前 parentNode 为空
    // 需要验证按钮权限在按钮组加入 permission 属性，不传这里拿到 undefined
    inserted: (el , binding) => {
        if(binding.value !== '' && binding.value !== undefined){
            if(!store.getters.buttons.includes(binding.value)){
                el.parentNode && el.parentNode.removeChild(el);
            }
        }else if(binding.value !== undefined){
            console.warn('directive tag v-permission need a string but got null , ' +
                'you should write as v-permission="permission" , please check it ');
        }
    }

})

new Vue({
    store,
    router,
    components: {App},
    template: '<App/>'
}).$mount(document.getElementById('app'));
