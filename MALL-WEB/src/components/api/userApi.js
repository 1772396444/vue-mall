import request from '@/utils/request';

export default {

	find(params) {
		return request.post('/user/find', params);
	}

}
