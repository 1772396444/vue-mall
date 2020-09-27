<template>
    <div>
        
        <Input
            v-if="!data.type || data.type.toUpperCase() === 'INPUT'"
            v-decorator="[data.name]"
            :placeholder="data.placeholder"
            v-model="inputParams"
        />
    
        <Select v-else-if="data.type && data.type.toUpperCase() === 'SELECT'" v-decorator="['select']"
            showSearch @onSearch="handleSerach" :placeholder="data.placeholder" v-model="inputParams" >
            <Option v-for="(item,index) in data.data" :value="item.id" :key="index" >{{item.name}}</Option>
        </Select>
    
        <DatePicker v-else-if="data.type && data.type.toUpperCase() === 'DATE'" :format="dateFormat"
            :default-value="moment(new Date(), dateFormat)" @change="change" />
    
        <MonthPicker v-else-if="data.type && data.type.toUpperCase() === 'MONTH'" :format="monthFormat"
            :default-value="moment(new Date(), monthFormat)" @change="change" />
    
        <TreeSelect
            v-model="inputParams"
            show-search
            tree-data-simple-mode
            :tree-data="treeDate"
            :placeholder="data.placeholder"
            allow-clear
            tree-default-expand-all
            v-else-if="data.type && data.type.toUpperCase() === 'TREE'"
        >
        </TreeSelect>
        
    </div>
</template>

<script>
import moment from 'moment';
import {Input, Select, TreeSelect, DatePicker} from 'ant-design-vue';
export default {
    props: ['data'],
    data() {
        return {
            inputParams: '',
            treeDate: [],
            dateFormat: 'YYYY/MM/DD',
            monthFormat: 'YYYY/MM',
        }
    },
    created() {
        if(this.data.type){
            if(this.data.type.toUpperCase() === 'DATE') {
                this.inputParams = moment(new Date(), this.dateFormat).format(this.dateFormat);
            }else if(this.data.type.toUpperCase() === 'MONTH') {
                this.inputParams = moment(new Date(), this.monthFormat).format(this.monthFormat);
            }else if(this.data.type.toUpperCase() === 'TREE') {
                this.caseTreeData(this.data.data , 0 , false);
            }
        }
    },
    methods: {
        moment,
        handleSerach(e){
            let { clusterValue } = this.state;
            let that = this;
            // 节流
            setTimeout(function(){
                that.loadOption(clusterValue, e)
            },300)
        },
        change(date , value) {
            this.inputParams = value;
        },
        caseTreeData(data , pId , isLeaf) {
            data.forEach(cData => {
                this.treeDate.push({
                    pId,
                    isLeaf,
                    id: cData.key,
                    value: cData.key,
                    title: cData.value,
                });
                if(cData.child){
                    this.caseTreeData(cData.child , cData.key , cData.child[0].child ? false : true);
                }
            });
        },
    },
    components: {
        Input,
        Select,
        Option: Select.Option,
        TreeSelect,
        TreeSelectNode: TreeSelect.TreeNode,
        DatePicker,
        MonthPicker: DatePicker.MonthPicker,
    }
}
</script>

<style lang="less">
.ant-calendar-picker {
    width: 100%;
}
</style>
