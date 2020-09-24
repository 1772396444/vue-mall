<template>
    <a-layout-sider width="200" style="background: #fff">
        <a-menu
            theme="light"
            mode="inline"
            :default-selected-keys="selectedKeys"
            :default-open-keys="openKeys"
            :style="{ height: '100%', borderRight: 0 }"
        >
            <template v-for="menu in menuTree">
                <a-menu-item v-if="menu.child.length === 0" :key="menu.id" >
                    <Link :to="menu.url" :key="menu.name">
                        <span ><a-icon type="user" />{{menu.name}}</span>
                    </Link>
                </a-menu-item>
                <a-sub-menu v-else :key="menu.id">
                    <span slot="title"><a-icon type="user" />{{menu.name}}</span>
                    <a-menu-item v-for="child in menu.child" :key="child.id">
                        <Link :to="child.url" :key="child.name">
                            <span><a-icon type="menu-unfold" />{{child.name}}</span>
                        </Link>
                    </a-menu-item>
                </a-sub-menu>
            </template>
        </a-menu>
    </a-layout-sider>
</template>

<script>

import api from '@components/api/coreApi';

export default {
    data() {
        return {
            menuList: [],
            menuTree: [],
            openKeys: [],
            buttons: [],
            selectedKeys: [],
        }
    },
    async mounted() {
        this.loadKeys();
        await api.menuList().then(res => {
            this.menuList = [...res.data];
        });
        this.loadTreeMenu();
        this.saveMenu();
        this.saveBtn();
    },
    methods: {
        loadTreeMenu(parentId = '0') {
            this.menuList.filter((menu) => {
                return menu.parentId === parentId && menu.type === 1;
            }).forEach(({id , name , url , defaultIcon}) => {
                this.menuTree.push({
                    id , name , url , defaultIcon , child: []
                });
            });
            this.menuTree.forEach((menu) => {
                this.loadChildMenu(menu);
            });
        },
        loadChildMenu(parentMenu) {
            this.menuList.filter((menu) => {
                return menu.parentId === parentMenu.id && menu.type === 1;
            }).forEach(({id , name , url , defaultIcon}) => {
                parentMenu.child.push({
                    id , name , url , defaultIcon , child: []
                });
            });
        },
        saveMenu() {
            let routes = [];
            this.menuList.forEach(({  id , name , url , type }) => {
                if(type === 1){
                    routes.push({
                        id , path: url , name
                    });
                }
            });
            this.$store.commit('routes' , routes);
        },
        loadKeys() {
            this.$router.options.routes.forEach((route) => {
                if(route.name === 'index'){
                    let keyList = route.children && route.children.filter((child) => {
                        if(this.$route.path.indexOf(child.path) !== -1){
                            return child;
                        }
                    });
                    if(keyList.length === 0){
                        this.$router.push(route.children[0].path).catch(err => err);
                    }else{
                        keyList.forEach((child) => {
                            if(this.$route.path === child.path){
                                this.selectedKeys.push(child.id);
                            }else{
                                this.openKeys.push(child.id);
                            }
                        });
                    }
                }
            });
        },
        saveBtn() {
            this.menuList.filter(menu => {
                return menu.type === 0;
            }).forEach(menu => {
                this.buttons.push(menu.permission);
            });
            this.$store.commit('buttons' , this.buttons);
        },
    },
    components: {
        Link: () => import('./link')
    }
}

</script>
