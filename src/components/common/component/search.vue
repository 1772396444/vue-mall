<template>
    <Form class="ant-advanced-search-form" :form="form" @submit="handleSearch">
        <Row :gutter="24">
            <Col
                v-for="(filed , index) in data"
                :key="index"
                :span="6"
                :style="{ display: index < count ? 'block' : 'none' }"
            >
                <FormItem :label="filed.label">
                    <InputType ref="input" :data="filed"></InputType>
                </FormItem>
            </Col>
        </Row>
        <Row>
            <Col :span="24" :style="{ textAlign: 'right' }">
                <Button type="primary" html-type="submit">
                    Search
                </Button>
                <Button :style="{ marginLeft: '8px' }" @click="handleReset">
                    Clear
                </Button>
                <a :style="{ marginLeft: '8px', fontSize: '12px' }" @click="toggle">
                    Collapse
                    <Icon :type="expand ? 'up' : 'down'"/>
                </a>
            </Col>
        </Row>
    </Form>
</template>

<script>
import { Form , Row , Col , Input , Button , Icon } from 'ant-design-vue';
export default {
    props: ['data'],
    data() {
        return {
            paramsData: [],
            expand: false,
            form: this.$form.createForm(this, {name: 'advanced_search'})
        };
    },
    computed: {
        count() {
            return this.expand ? 99 : 4;
        },
    },
    methods: {
        handleSearch(e) {
            e.preventDefault();
            let values = {};
            this.$refs.input.forEach(child => {
                values[child.data.name] = child.inputParams;
            });
            this.$attrs.onSearch(values);
        },
        handleReset() {
            this.form.resetFields();
            this.handleSearch();
        },
        toggle() {
            this.expand = !this.expand;
        },
    },
    components: {
        Form,
        Row,
        Col,
        Input,
        Button,
        Icon,
        FormItem: Form.Item,
        InputType: () => import('./inputType')
    }
}
</script>

<style scoped lang="less">
.ant-advanced-search-form {
    padding: 15px;
    background: #fbfbfb;
    border: 1px solid #d9d9d9;
    border-radius: 6px;
}

.ant-advanced-search-form .ant-form-item {
    display: flex;
}

.ant-advanced-search-form .ant-form-item-control-wrapper {
    flex: 1;
}

#components-form-demo-advanced-search .ant-form {
    max-width: none;
}

#components-form-demo-advanced-search .search-result-list {
    margin-top: 16px;
    border: 1px dashed #e9e9e9;
    border-radius: 6px;
    background-color: #fafafa;
    min-height: 200px;
    text-align: center;
    padding-top: 80px;
}

.ant-form-horizontal {
    background-color: white;
    border-top: 3px solid #1d8fe0;
}

.ant-form-item {
    margin-bottom: 10px !important;
}
</style>

