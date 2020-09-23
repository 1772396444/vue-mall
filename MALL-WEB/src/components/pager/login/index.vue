<template>
    <div class="content">
        <div class="content-bar">
            <div class="content-bar-backage"></div>
            <a-form
                id="components-form-demo-normal-login"
                :form="form"
                class="login-form"
                @submit="handleSubmit"
            >
                <a-form-item>
                    <a-input
                        v-decorator="['name']"
                        placeholder="请输入用户名"
                    />
                </a-form-item>
                <a-form-item>
                    <a-input
                        v-decorator="['password']"
                        type="password"
                        placeholder="请输入密码"
                    />
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit" class="login-form-button">
                        Log in
                    </a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>
</template>

<script>
export default {
    beforeCreate () {
        this.form = this.$form.createForm(this, {name: 'normal_login'});
    },
    methods: {
        handleSubmit (e) {
            e.preventDefault();
            let data = this.form.getFieldsValue();
            if (!data.name) {
                return this.$utils.Message.error(null, '用户名不能为空!');
            }
            if (!data.password) {
                return this.$utils.Message.error(null, '密码不能为空!');
            }
            this.$utils.Message.success(null, `name=${data.name}\npassword=${data.password}`);
        }
    }
};
</script>

<style scoped lang="less">
@image: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600878739240&di=0f6dbca61264473a3e98f8ff087f2042&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201303%2F24%2F221533reghefhxww199oqk.jpg";

.content {
    width: 100vw;
    height: 100vh;
    display: flex;
    align-items: center;
    background-size: cover;
    background-image: url(@image);

    .content-bar {
        width: 100vw;
        height: 300px;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .content-bar-backage {
        opacity: .4;
        width: 100%;
        height: 100%;
        background: #78b3ae;
    }

    .ant-form-item {
        opacity: .4;
        width: 240px;
        margin: 0 auto 10px;

        &:hover {
            opacity: 1;
            width: 260px;
            transition: width .5s , opacity 1s;
        }
    }
}

input {
    &::-webkit-input-placeholder {
        color: white;
    }

    &::-ms-input-placeholder {
        color: white;
    }

    &:-moz-placeholder {
        color: white;
    }

    &::-moz-placeholder {
        color: white;
    }
}

#components-form-demo-normal-login {
    width: 300px;
    position: absolute;
}

#components-form-demo-normal-login .ant-input {
    color: white;
    height: 40px;
    text-align: center;
    border-radius: 20px;
    background: #4d575f;
}

#components-form-demo-normal-login .login-form {
    max-width: 300px;
}

#components-form-demo-normal-login .login-form-button {
    width: 100%;
}
</style>
