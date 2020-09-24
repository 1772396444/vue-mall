<template>
    <div class="button-box" :style="{height: hasBtn ? '60px' : '15px'}">
        <a-button v-for="(button , index) in buttons" :type="button.type" @click="click(button)"
                  :key="index" :icon="button.icon" v-permission="button.permission">{{ button.name }}
        </a-button>
    </div>
</template>

<script>
export default {
    props: ['buttons'],
    data() {
        return {
            hasBtn: false,
            btns: this.$router.app.$options.store.getters.buttons
        }
    },
    created() {
        this.hasBtn = this.buttons.filter(button => {
            if (button.permission === '' || button.permission === undefined) {
                return true
            } else {
                return this.btns.includes(button.permission)
            }
        }).length > 0;
    },
    methods: {
        click(button) {
            button.callback(button.modal);
        }
    }
}
</script>

<style>
.button-box {
    text-align: right;
    line-height: 60px;
}

.button-box > button {
    margin-left: 10px;
}
</style>
