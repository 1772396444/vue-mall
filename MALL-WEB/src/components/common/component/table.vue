<template>
    <a-table bordered :columns="columns" :data-source="tableData" :loading="loading"
             @change="handleTableChange" :pagination="pagination" size="middle">
        <!-- table按钮组 -->
        <span slot="action" slot-scope="text, record">
            <a-button v-for="(button , index) in buttons" :key="index" :type="button.type"
                      size="small" @click="buttonClick(record , button)">{{ button.name }}</a-button>
        </span>
    </a-table>
</template>

<script>
export default {
    props: ['columns', 'refreshTable'],
    data() {
        return {
            tableData: [],
            buttons: [],
            loading: true,
            pagination: {
                total: 0,
                current: 1,
                pageSize: 10,
                // showSizeChanger: true,
                // pageSizeOptions: ["10", "20", "50", "100"],
                showTotal: total => `共有 ${total} 条数据`,
            },
        }
    },
    created() {
        this.refresh();
    },
    methods: {
        refresh() {
            this.loading = true;
            this.pagination.pageNo = this.pagination.current
            this.refreshTable(this.pagination).then(response => {
                if (response.status) {
                    response.data.records.forEach((item, index) => {
                        item.key = index;
                    });
                    this.tableData = response.data.records;
                    this.pagination.total = response.data.total;
                }
                this.loading = false;
                this.loadScope();
            });
        },
        loadScope() {
            this.columns.forEach(column => {
                if (column.buttons) {
                    this.buttons = column.buttons;
                }
            });
        },
        handleTableChange(pagination) {
            this.pagination.current = pagination.current;
            this.pagination.pageSize = pagination.pageSize;
            this.refresh();
        },
        buttonClick(record, button) {
            button.callback(button.modal, record);
        },
    }
}
</script>

<style scoped lang="less">
.ant-btn {
    margin-left: 10px;
}

.ant-table-thead > tr > th {
    text-align: center;
}

.ant-table-body {
    overflow-x: hidden;
    text-align: center;
    scrollbar-width: none;
    -ms-overflow-style: none;
}

.ant-table-body::-webkit-scrollbar {
    display: none;
}
</style>
