<template>
    <Pager :showBar="true" :modal="modal" :params="params" >
        <template #content>
            <Search :data="searchData" :onSearch="search" />
            <Button :buttons="buttons" />
            <TableWarpper :columns="tableHeader" :refreshTable="refreshTable" ref="table" />
        </template>
    </Pager>
</template>

<script>
import api from '@components/api/basic/userApi';
export default {
    data() {
        return {
            searchData: [
                { label: '日期', type: 'date', name: 'date', placeholder: '日期选择器' },
                { label: '月份', type: 'month', name: 'month', placeholder: '月份选择器' },
                { label: '输入', type: 'input', name: 'input', placeholder: '输入框' },
                { label: '下拉', type: 'select', data: [{id:1,name:'2'},{id:2,name:'3'}], name: 'select', placeholder: '下拉选择器' },
                { label: '树形', type: 'tree', data: [{
                        key: 1,value: '树1',child: [{key:2,value:'树2',child:[{key:3,value:'树3'}]},{key:4,value:'树4'}]
                    }], name: 'tree', placeholder: '树形下拉框' },
            ],
            modal: {},
            buttons: [
                {
                    name: '添加',
                    type: 'primary',
                    icon: 'search',
                    permission: 'account:test7',
                    callback: (modal) => this.showModal(modal),
                    modal: {
                        type: 'modal',
                        title: '资源新增',
                        afterSubmit: () => this.$refs.table.refresh(),
                        component: resolve => require(['./add'] , resolve),
                    },
                }
            ],
            tableHeader: [
                { title: '资源编码', dataIndex: 'id', align: 'center'  },
                { title: '资源名称', dataIndex: 'name', align: 'center'  },
                { title: '权限', dataIndex: 'permission', align: 'center'  },
                { title: 'url地址', dataIndex: 'url', align: 'center'  },
                { title: '类型', dataIndex: 'type', align: 'center'  },
                { title: '排序', dataIndex: 'sorts', align: 'center'  },
                { title: '状态', dataIndex: 'status', align: 'center'  },
                {
                    title: '操作',
                    width: 100,
                    align: 'center',
                    scopedSlots:  { customRender: 'action' },
                    buttons: [
                        {
                            name: '修改',
                            type: 'primary',
                            callback: (modal , params) => this.showModal(modal , params),
                            modal: {
                                type: 'drawer',
                                title: '资源修改',
                                afterSubmit: () => this.$refs.table.refresh(),
                                component: resolve => require(['./edit'] , resolve),
                            },
                        }
                    ],
                },
            ],
            params: {},
            searchParams: {},
        }
    },
    methods: {
        search(value) {
            console.log(value)
            this.searchParams = value;
            Object.keys(value).forEach(key => {
                this.$refs.table.pagination[key] = value[key];
            });
            this.$refs.table.pagination.current = 1;
            this.$refs.table.refresh();
        },
        showModal(modal , params) {
            this.params = params;
            this.modal = {...modal};
            this.modal.show = true;
        },
        refreshTable(params) {
            return api.find(params)
        },
    },
    components: {
        Pager: () => import('@/components/common/component/pager'),
        Search: () => import('@/components/common/component/search'),
        Button: () => import('@/components/common/component/button'),
        TableWarpper: () => import('@/components/common/component/table'),
    }
}
</script>
