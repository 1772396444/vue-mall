<template>
    
    <Layout id="components-layout-demo-top-side-2">
        <!-- 头部 -->
        <Header/>
        <Layout class="content">
            <!-- Sider -->
            <Sider/>
            <!-- content -->
            <Layout>
                <Content/>
            </Layout>
        </Layout>
    </Layout>

</template>

<script>
import { Layout } from 'ant-design-vue'
export default {
    methods: {},
    components: {
        Layout,
        Header: () => import('@components/common/page/header'),
        Sider: () => import('@components/common/page/sider'),
        Content: () => import('@components/common/page/content')
    }
}

</script>

<style scoped lang="less">
#components-layout-demo-top-side-2 .logo {
    width: 120px;
    height: 31px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px 28px 16px 0;
    float: left;
    
    .content {
        height: calc(100vh - 64px);
    }
}
</style>
