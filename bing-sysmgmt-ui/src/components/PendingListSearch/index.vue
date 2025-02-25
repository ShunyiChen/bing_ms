<template>
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="135" label-suffix=":"
        class="custom-form">
        <div class="form-row">
            <el-form-item label="NameInBatch" prop="nameInBatchApprovalList" class="custom-form-item">
                <el-input v-model="queryParams.nameInBatchApprovalList" placeholder="" clearable
                    @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="GPN" prop="gpn" class="custom-form-item">
                <el-input v-model="queryParams.gpn" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="Email" prop="emailAddress" class="custom-form-item">
                <el-input v-model="queryParams.emailAddress" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
        </div>
        <div class="form-row">
            <el-form-item label="UserName" prop="userName" class="custom-form-item">
                <el-input v-model="queryParams.userName" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="LPN" prop="lpn" class="custom-form-item">
                <el-input v-model="queryParams.lpn" placeholder="" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item class="custom-form-item">
                <el-button type="primary" icon="Search" @click="handleQuery">Search</el-button>
                <el-button icon="Refresh" @click="resetQuery">Reset</el-button>
            </el-form-item>
        </div>

    </el-form>
</template>

<script setup>
import { inject } from 'vue';
import { listPending } from "@/api/legaldatabase/PendingList";
const props = defineProps({
    /* 是否显示检索条件 */
    showSearch: {
        type: Boolean,
        default: true,
    },
    loading: {
        type: Boolean,
        default: false,
    }
})
const { proxy } = getCurrentInstance();
const data = reactive({
    form: {},
    queryParams: {
        pageNum: 1,
        pageSize: 10,
        nameInBatchApprovalList: null,
        gpn: null,
        emailAddress: null,
        userName: null,
        lpn: null,
    },
    rules: {
    }
});
const { queryParams } = toRefs(data);

const emits = defineEmits(['update:loading', 'getSearchResult']);

/** 初始化Request列表 */
function initGetList(status) {
    queryParams.value.status = status;
    getList()
}

/** 搜索按钮操作 */
function handleQuery() {
    getList();
}

/** 查询Pending列表 */
function getList() {
    emits("update:loading", true);
    listPending(queryParams.value).then(response => {
        emits('getSearchResult', response, queryParams.value)
    }).catch(err => {
        emits("update:loading", false);
    });
}

/** 重置按钮操作 */
function resetQuery() {
    proxy.resetForm("queryRef");
    handleQuery();
}

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