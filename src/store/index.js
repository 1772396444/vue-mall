import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        token: '',
        routes: [],
        buttons: []
    },
    mutations: {
        token (state, token) {
            state.token = token;
            localStorage['MALL_TOKEN'] = token;
        },
        routes (state, routes) {
            state.routes = routes;
            localStorage['MALL_ROUTES'] = JSON.stringify(routes);
        },
        buttons (state, buttons) {
            state.buttons = buttons;
            localStorage['MALL_BUTTONS'] = JSON.stringify(buttons);
        }
    },
    getters: {
        token: state => {
            if (state.token === '') {
                state.token = localStorage.getItem('MALL_TOKEN');
            }
            return state.token;
        },
        routes: state => {
            if (state.routes.length === 0) {
                state.routes = JSON.parse(localStorage.getItem('MALL_ROUTES'));
            }
            return state.routes;
        },
        buttons: state => {
            if (state.buttons.length === 0) {
                state.buttons = JSON.parse(localStorage.getItem('MALL_BUTTONS'));
            }
            return state.buttons;
        }
    },
    actions: {
        token (context, token) {
            context.commit('token' , token);
        },
        routes (context, routes) {
            context.commit('routes' , routes);
        },
        buttons (context, buttons) {
            context.commit('buttons' , buttons);
        }
    }
});
