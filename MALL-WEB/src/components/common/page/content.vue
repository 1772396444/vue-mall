<template>
    <section class="commView">
        <!-- transition添加切换动画，内部加入路由视图 -->
        <transition class="innerView" name="slide-fade">
            <router-view />
        </transition>
    </section>
</template>

<script>
export default {

}
</script>

<style>
    .commView {
        margin: 10px;
        border-radius: 10px;
        background-color: white;
        height: calc(100vh - 85px);
    }
    .innerView {
        padding: 20px;
    }
    .slide-fade-enter-active {
        transition: all .3s ease;
    }
    .slide-fade-leave-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }
    .slide-fade-enter, .slide-fade-leave-to {
        transition: all 1s ease;
        transform: translateX(10px);
        opacity: 0;
    }
</style>
