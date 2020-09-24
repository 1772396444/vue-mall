<template>
    
    <a-layout id="components-layout-demo-top-side-2">
        <!-- 头部 -->
        <Header/>
        <a-layout class="content">
            <!-- Sider -->
            <Sider/>
            <!-- content -->
            <a-layout>
                <Content/>
            </a-layout>
        </a-layout>
    </a-layout>

</template>

<script>

export default {
    methods: {},
    components: {
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
