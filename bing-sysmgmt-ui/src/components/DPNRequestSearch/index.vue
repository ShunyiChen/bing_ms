<template>
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="155" label-suffix=":"
        class="custom-form">
        <div class="form-row">
            <el-form-item label="GPN" prop="staffNo" class="custom-form-item">
                <el-input v-model.trim="queryParams.staffNo" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="LPN" prop="lpn" class="custom-form-item">
                <el-input v-model.trim="queryParams.lpn" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Staff Name" prop="staffName" class="custom-form-item">
                <el-input v-model.trim="queryParams.staffName" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="Email" prop="email" class="custom-form-item">
                <el-input v-model.trim="queryParams.email" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Office" prop="offices" class="custom-form-item">
                <!-- <el-input v-model="queryParams.office" placeholder="" clearable @keyup.enter="handleQuery" /> -->
                <el-select v-model="queryParams.offices" multiple clearable collapse-tags placeholder="Select"
                    popper-class="custom-header" :max-collapse-tags="1" style="width: 100%;">
                    <template #header>
                        <el-checkbox v-model="checkAll" :indeterminate="indeterminate" @change="handleCheckAll">
                            All
                        </el-checkbox>
                    </template>
                    <el-option v-for="item in legaldb_office" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>

            </el-form-item>
            <el-form-item label="Dept/Service Line" prop="serviceLine" class="custom-form-item">
                <el-input v-model.trim="queryParams.serviceLine" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="EY Rank" prop="rankName" class="custom-form-item">
                <el-input v-model.trim="queryParams.rankName" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Creator" prop="createdBy" class="custom-form-item">
                <el-input v-model.trim="queryParams.createdBy" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Create Date" prop="createdDateRange" class="custom-form-item">
                <el-date-picker v-model="queryParams.createdDateRange" type="datetimerange"
                    start-placeholder="Start date"
                    end-placeholder="End date"
                    format="YYYY-MM-DD HH:mm:ss"
                    date-format="YYYY-MM-DD"
                    time-format="hh:mm:ss"
                    style="width: 100%;" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="Reject Reason" prop="rejectReason" class="custom-form-item">
                <el-input v-model.trim="queryParams.rejectReason" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Return Date" prop="returnedDateRange" class="custom-form-item">
                <el-date-picker v-model="queryParams.returnedDateRange" type="datetimerange" start-placeholder="Start date"
                    end-placeholder="End date"
                    format="YYYY-MM-DD HH:mm:ss"
                    date-format="YYYY-MM-DD"
                    time-format="hh:mm:ss"
                    style="width: 100%;" />
            </el-form-item>
            <el-form-item label="Device Type" prop="deviceType" class="custom-form-item">
                <el-select v-model="queryParams.deviceType" placeholder="Select" clearable>
                    <el-option v-for="dict in legaldb_device_type" :key="dict.value" :label="dict.label"
                        :value="dict.value"></el-option>
                </el-select>
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="EY Asset No" prop="eyAssetNo" class="custom-form-item">
                <el-input v-model.trim="queryParams.eyAssetNo" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Serial No" prop="serialNo" class="custom-form-item">
                <el-input v-model.trim="queryParams.serialNo" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Received By" prop="receivedBy" class="custom-form-item">
                <el-input v-model.trim="queryParams.receivedBy" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="Engagement Code" prop="engagementCode" class="custom-form-item">
                <el-input v-model.trim="queryParams.engagementCode" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Engagement Name" prop="engagementName" class="custom-form-item">
                <el-input v-model.trim="queryParams.engagementName" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Cancel Reason" prop="cancelReason" class="custom-form-item">
                <el-input v-model.trim="queryParams.cancelReason" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="FIDS Receiver" prop="fidsReceivedBy" class="custom-form-item">
                <el-input v-model.trim="queryParams.fidsReceivedBy" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="FIDS Received Date" prop="fidsReceivedDateRange" class="custom-form-item">
                <el-date-picker v-model="queryParams.fidsReceivedDateRange" type="datetimerange"
                    start-placeholder="Start date" end-placeholder="End date"
                    format="YYYY-MM-DD HH:mm:ss"
                    date-format="YYYY-MM-DD"
                    time-format="hh:mm:ss"
                    style="width: 100%;" />
            </el-form-item>
            <el-form-item label="Device Location" prop="deviceLocation" class="custom-form-item">
                <el-input v-model="queryParams.deviceLocation" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="Approval Date" prop="approvedDateRange" class="custom-form-item">
                <el-date-picker v-model="queryParams.approvedDateRange" type="datetimerange" start-placeholder="Start date"
                    end-placeholder="End date"
                    format="YYYY-MM-DD HH:mm:ss"
                    date-format="YYYY-MM-DD"
                    time-format="hh:mm:ss"
                    style="width: 100%;" />
            </el-form-item>
            <el-form-item label="Reject Date" prop="rejectedDateRange" class="custom-form-item">
                <el-date-picker v-model="queryParams.rejectedDateRange" type="datetimerange" start-placeholder="Start date"
                    end-placeholder="End date"
                    format="YYYY-MM-DD HH:mm:ss"
                    date-format="YYYY-MM-DD"
                    time-format="hh:mm:ss"
                    style="width: 100%;" />
            </el-form-item>
            <el-form-item label="Approver" prop="approver" class="custom-form-item">
                <el-input v-model.trim="queryParams.approver" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="Rejecter" prop="rejecter" class="custom-form-item">
                <el-input v-model.trim="queryParams.rejecter" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Remark" prop="remark" class="custom-form-item">
                <el-select v-model="queryParams.remark" placeholder="Select" clearable>
                    <el-option v-for="dict in legaldb_remark" :key="dict.value" :label="dict.label"
                        :value="dict.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item class="custom-form-item">
                <el-button type="primary" icon="Search" @click="handleQuery">Search</el-button>
                <el-button icon="Refresh" @click="resetQuery">Reset</el-button>
            </el-form-item>
        </div>

    </el-form>
</template>
  
<script setup>
import { listDPNRequest } from "@/api/legaldatabase/DPNRequest";
const props = defineProps({
    /* 是否显示检索条件 */
    showSearch: {
        type: Boolean,
        default: true,
    },
    loading: {
        type: Boolean,
        default: false,
    },
    getDicts: {
        type: Function,
        required: true
    },
})

const { proxy } = getCurrentInstance();
// 使用字典数据
const { legaldb_device_type, legaldb_remark, legaldb_office } = props.getDicts();
// const { legaldb_device_type, legaldb_remark, legaldb_office } = proxy.useDict("legaldb_device_type", "legaldb_remark", "legaldb_office");
const checkAll = ref(false)
const indeterminate = ref(false)
const value = ref([])

const data = reactive({
    queryParams: {
        staffNo: null,
        lpn: null,
        staffName: null,
        email: null,
        offices: null,
        serviceLine: null,
        rankName: null,
        createdBy: null,
        created: null,
        status: null,
        rejectReason: null,
        deviceType: null,
        eyAssetNo: null,
        serialNo: null,
        receivedBy: null,
        engagementCode: null,
        engagementName: null,
        cancelReason: null,
        fidsReceivedBy: null,
        fidsReceivedDate: null,
        deviceLocation: null,
        approved: null,
        rejected: null,
        approver: null,
        rejecter: null,
        createdDateRange: null,
        returnedDateRange: null,
        fidsReceivedDateRange: null,
        approvedDateRange: null,
        rejectedDateRange: null,
        pageNum: 1,
        pageSize: 10,
        findWhat: null,
        scope: []
    },
    rules: {
        staffNo: [
            { required: true, message: "gpn员工编号不能为空", trigger: "blur" }
        ],
        lpn: [
            { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
        createdBy: [
            { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        created: [
            { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        status: [
            { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
    }
});

const { queryParams } = toRefs(data);
const emits = defineEmits(['update:loading','getSearchResult', 'getCount']);

/** 搜索按钮操作 */
function handleQuery() {
    getList();
}

/** 初始化Request列表 */
function initGetList(status) {
    queryParams.value.status = status;
    getList();
}
 
/** 查询DPNRequest列表 */
function getList() {
    emits("update:loading", true);
    listDPNRequest(queryParams.value).then(response => {
        emits('getSearchResult', response, queryParams.value);
    });
    emits('getCount', queryParams.value);
}

/** 重置按钮操作 */
function resetQuery() {
    queryParams.value.findWhat = null;
    queryParams.value.scope = [];
    proxy.resetForm("queryRef");
    handleQuery();
}

const handleCheckAll = (val) => {
    indeterminate.value = false
    if (val) {
        queryParams.value.offices = legaldb_office.value.map((_) => _.value);
    } else {
        queryParams.value.offices = [];
    }
}

watch(value, (val) => {
    if (val.length === 0) {
        checkAll.value = false;
        indeterminate.value = false;
    } else if (val.length === legaldb_office.value.length) {
        checkAll.value = true;
        indeterminate.value = false;
    } else {
        indeterminate.value = true;
    }
})
// 使用 defineExpose 来暴露 show 方法
defineExpose({
    initGetList,
    resetQuery
});
</script>
  
<style lang='scss' scoped>
.custom-form {
    margin-bottom: 10px;
    /* 调整表单的底部间距 */
}

.form-row {
    display: flex;
    flex-wrap: wrap;
    /* 允许换行 */
}

.custom-form-item {
    flex: 1 1 30%;
    /* 每个表单项占据大约三分之一的宽度 */
    margin-bottom: 5px;
    /* 调整每个表单项的底部间距 */
}

.custom-header {
    .el-checkbox {
        display: flex;
        height: unset;
    }
}
</style>