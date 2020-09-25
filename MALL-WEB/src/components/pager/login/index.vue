<template>
    <div class="content">
        <div class="content-bar">
            <div class="content-bar-color"></div>
            <Form
                id="components-form-demo-normal-login"
                class="login-form"
                @submit="handleSubmit"
            >
                <Item>
                    <Input
                        v-model="name"
                        v-decorator="['name']"
                        placeholder="UserName"
                    />
                </Item>
                <Item>
                    <Input
                        v-model="password"
                        v-decorator="['password']"
                        type="password"
                        placeholder="PassWord"
                    />
                </Item>
                <Item>
                    <Button type="primary" html-type="submit" class="login-form-button">
                        Log in
                    </Button>
                </Item>
            </Form>
        </div>
    </div>
</template>

<script>
import api from '@components/api/coreApi';
import { Form , Input , Button } from 'ant-design-vue';
export default {
    data() {
        return {
            name: '',
            password: ''
        }
    },
    methods: {
        handleSubmit(e , value) {
            e.preventDefault();
            if (!this.name) {
                return this.$utils.Message.error(null, '用户名不能为空!');
            }
            if (!this.password) {
                return this.$utils.Message.error(null, '密码不能为空!');
            }
            api.login({name: this.name , password: this.password}).then(response => {
                if(response.data && response.data.code === 200){
                    this.$store.commit('token' , response.data.token);
                    this.$router.push('/index').catch(err => err);
                }
            })
        }
    },
    components: {
        Form,
        Item: Form.Item,
        Input,
        Button
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
    
    .content-bar-color {
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
            transition: width .5s, opacity 1s;
        }
    }
}

#components-form-demo-normal-login {
    width: 300px;
    position: absolute;
}

#components-form-demo-normal-login .ant-input {
    color: white;
    height: 40px;
    font-weight: 900;
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
