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
            localStorage['token'] = token;
        },
        routes (state, routes) {
            state.routes = routes;
            localStorage['routes'] = JSON.stringify(routes);
        },
        buttons (state, buttons) {
            state.buttons = buttons;
            localStorage['buttons'] = JSON.stringify(buttons);
        }
    },
    getters: {
        token: state => {
            if (state.token === '') {
                state.token = localStorage.getItem('token');
            }
            return state.token;
        },
        routes: state => {
            if (state.routes.length === 0) {
                state.routes = JSON.parse(localStorage.getItem('routes'));
            }
            return state.routes;
        },
        buttons: state => {
            if (state.buttons.length === 0) {
                state.buttons = JSON.parse(localStorage.getItem('buttons'));
            }
            return state.buttons;
        }
    }
});
