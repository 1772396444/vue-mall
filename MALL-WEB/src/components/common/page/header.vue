<template>
    <Header class="header">
        <Menu
            theme="dark"
            mode="horizontal"
            :default-selected-keys="['2']"
            :style="{ lineHeight: '64px' }"
        >
            <Item key="1" @click="logout" >
                退出 <Icon type="logout" />
            </Item>
        </Menu>
    </Header>
</template>

<script>
import api from '@components/api/coreApi';
import { Layout , Menu , Icon } from 'ant-design-vue';
export default {
    methods: {
        logout() {
            api.logout({}).then(response => {
                this.$store.dispatch('token' , '');
                this.$store.dispatch('routes' , []);
                this.$store.dispatch('buttons' , []);
                window.location.reload();
            });
        }
    },
    components: {
        Header: Layout.Header,
        Menu,
        Icon,
        Item: Menu.Item
    }
}
</script>
