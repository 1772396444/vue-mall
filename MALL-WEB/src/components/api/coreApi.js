import request from '@/utils/request';

export default {

	login(params) {
		return request.post('/login', params);
	},

	logout(params){
		return request.post('/logout' , params);
	},

	menuList(params){
		return request.post('/resource/menuList' , params);
	}

}
