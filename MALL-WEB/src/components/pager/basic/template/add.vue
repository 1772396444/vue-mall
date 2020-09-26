<template>
    <div>
        <Select showSearch :style="{width: '100%'}" @onSearch="handleSerach"
                placeholder="请选择数据库" @change="handleSecChange" v-if="!isLoad">
            <Option v-for="(item,index) in database" :value="item.tableName" :key="index" >{{item.tableName}}</Option>
        </Select>
        <Collapse v-if="isLoad">
            <CollapsePanel key="1" header="搜索框设置">
                <Icon slot="extra" type="plus" />
                <div v-for="item in column">
                    {{item}}
                </div>
            </CollapsePanel>
            <CollapsePanel key="2" header="按钮设置">
                <p>按钮设置</p>
                <Icon slot="extra" type="plus" />
            </CollapsePanel>
            <CollapsePanel key="3" header="table设置">
                <p>table设置</p>
                <Icon slot="extra" type="plus" />
            </CollapsePanel>
        </Collapse>
    </div>
</template>

<script>
import api from '@components/api/basic/templateApi';
import { Collapse , Input , Select , Icon } from 'ant-design-vue';
export default {
    props: ['params'],
    data() {
        return {
            isLoad: false,
            database: [],
            column: [],
            search: {},
            buttons: {},
            table: {}
        }
    },
    created() {
        api.database({}).then(response => {
            this.database = [...response.data];
        });
    },
    methods: {
        handleSerach(e){
            let { clusterValue } = this.state;
            let that = this;
            // 节流
            setTimeout(function(){
                that.loadOption(clusterValue, e)
            },300)
        
        },
        handleSecChange(value) {
            api.table({tableId: value}).then(response => {
                this.column = [...response.data];
                this.isLoad = true;
            });
        }
    },
    components: {
        Icon,
        Input,
        Select,
        Option: Select.Option,
        Collapse,
        CollapsePanel: Collapse.Panel,
    }
}
</script>
