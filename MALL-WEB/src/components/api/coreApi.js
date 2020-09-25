import request from '@/utils/request';

export default {

	login(params) {
		return request.post('/basic/login', params);
	},

	logout(params){
		return request.get('/basic/logout' , params);
	},

	menuList(params){
		return request.post('/basic/resource/menuList' , params);
	}

}
