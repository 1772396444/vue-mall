import * as antd from 'ant-design-vue';

const Message = {

    success (message = '系统提示', description = '操作成功!') {
        if (!message) message = '系统提示';
        this.notification('success', message, description);
    },

    info (message, description) {
        this.notification('info', message, description);
    },

    warning (message, description) {
        this.notification('warning', message, description);
    },

    error (message = '系统提示', description = '操作失败!') {
        if (!message) message = '系统提示';
        this.notification('error', message, description);
    },

    notification (type, message, description) {
        antd.notification[type]({
            message,
            description
        });
    }

};

export default Message;
