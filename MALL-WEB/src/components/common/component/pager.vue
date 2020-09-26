<template>
    <div>
        <!-- breadcrumb页面头面包屑 -->
        <div class="breadcrumb" v-if="showBar">
            <Breadcrumb>
                <BreadcrumbItem v-for="breadcrumb in breadcrumbList" :key="breadcrumb.name">
                    {{ breadcrumb.name }}
                </BreadcrumbItem>
            </Breadcrumb>
        </div>
        <div class="pagerView">
            <!-- vue slot插槽 -->
            <slot name="content"/>
            <!-- 模态框展示 -->
            <Modal
                width="80vw"
                v-if="!modal.type || modal.type === 'modal'"
                :maskClosable=false
                :title="modalOption.title"
                :visible="modalOption.show"
                :confirm-loading="confirmLoading"
                @ok="handleOk"
                @cancel="handleCancel"
            >
                <!-- component is 属性显示需要展示的页面，用于文件解耦 -->
                <component v-if="modalOption.component" :is="modalOption.component" :params="params" :submit="submit"
                           @onSubmit="onSubmit"></component>
            </Modal>
            <div>
                <Drawer
                    width="40vw"
                    v-if="modal.type && modal.type === 'drawer'"
                    :title="modalOption.title"
                    placement="right"
                    :closable="false"
                    :visible="modalOption.show"
                    @close="handleCancel"
                >
                    <component v-if="modalOption.component" :is="modalOption.component" :params="params"
                               :submit="submit"
                               @onSubmit="onSubmit"></component>
                    <div
                        :style="{
                          position: 'absolute',
                          right: 0,
                          bottom: 0,
                          width: '100%',
                          borderTop: '1px solid #e9e9e9',
                          padding: '10px 16px',
                          background: '#fff',
                          textAlign: 'right',
                          zIndex: 1,
                        }"
                    >
                        <Button :style="{ marginRight: '8px' }" @click="handleCancel">
                            Cancel
                        </Button>
                        <Button type="primary" @click="handleOk">
                            Submit
                        </Button>
                    </div>
                </Drawer>
            </div>
        </div>
    </div>
</template>

<script>
import {Modal, Drawer, Button, Breadcrumb} from 'ant-design-vue';

export default {
    props: ['showBar', 'modal', 'params'],
    data() {
        return {
            submit: false,
            modalOption: {},
            breadcrumbList: [
                {name: 'Home'}
            ],
            confirmLoading: false,
        }
    },
    created() {
        this.loadBreadcrumbList();
    },
    methods: {
        loadBreadcrumbList() {
            this.$router.options.routes.forEach((route) => {
                if (route.name === 'index') {
                    route.children.filter((child) => {
                        if (this.$route.path.indexOf(child.path) !== -1) {
                            return child;
                        }
                    }).forEach((child) => {
                        this.breadcrumbList.push({
                            name: child.name
                        });
                    });
                }
            });
        },
        handleCancel() {
            this.modalOption.show = false;
        },
        handleOk() {
            this.submit = true;
            this.confirmLoading = true;
        },
        onSubmit() {
            this.submit = false;
            this.confirmLoading = false;
            this.modalOption.show = false;
            this.modal.afterSubmit();
        },
    },
    watch: {
        modal: {
            handler(data) {
                this.modalOption = {...data};
            },
            deep: true
        }
    },
    components: {
        Modal,
        Drawer,
        Button,
        Breadcrumb,
        BreadcrumbItem: Breadcrumb.Item
    }
}
</script>

<style scoped lang="less">
.ant-modal-body {
    height: 66vh;
    overflow-y: scroll;
    scrollbar-width: none;
    -ms-overflow-style: none;
}

.ant-modal-body::-webkit-scrollbar {
    display: none;
}

.commView > div {
    height: 100%;
    padding: 0px 10px;
}

.breadcrumb {
    height: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.pagerView {
    overflow-x: hidden;
    overflow-y: scroll;
    scrollbar-width: none;
    -ms-overflow-style: none;
    height: calc(100% - 50px);
    
    &::-webkit-scrollbar {
        display: none;
    }
}
</style>
