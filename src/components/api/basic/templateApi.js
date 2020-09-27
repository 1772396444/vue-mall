import request from '@/utils/request';

export default {

	find(params) {
		return request.post('/basic/template/list' , params);
	},

	database(params) {
		return request.post('/basic/template/database' , params);
	},

	table(params) {
		return request.post('/basic/template/table' , params);
	}

}
