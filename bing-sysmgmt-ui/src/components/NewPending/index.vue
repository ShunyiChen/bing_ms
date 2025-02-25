<template>
    <div>
        <el-dialog :modal="false" :title="title" v-model="open" width="50%" :close-on-click-modal=false
            :close-on-press-escape=false draggable overflow style="border: 1px solid var(--el-color-primary);">
            <el-form ref="formRef" :model="form" :rules="rules" label-width="auto">
                <div class="form-row">
                    <el-form-item label="NameInBatch" prop="nameInBatchApprovalList" class="left-column">
                        <el-input id="firstField" v-model="form.nameInBatchApprovalList" placeholder="" />
                    </el-form-item>
                    <el-form-item label="GPN" prop="gpn" class="right-column">
                        <el-input v-model="form.gpn" placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Email" prop="emailAddress" class="left-column">
                        <el-input v-model="form.emailAddress" placeholder="" />
                    </el-form-item>
                    <el-form-item label="UserName" prop="userName" class="right-column">
                        <el-input v-model="form.userName" placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="LPN" prop="lpn" class="left-column">
                        <el-input v-model="form.lpn" placeholder="" />
                    </el-form-item>
                    <el-form-item  class="right-column">
                    </el-form-item>
                </div>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="submitForm">{{$t('message.NewPending.2')}}</el-button>
                    <el-button @click="cancel">{{$t('message.NewPending.3')}}</el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import i18n from '@/i18n'
import { addPending } from "@/api/legaldatabase/PendingList";
import { ref, watch } from 'vue'
const emits = defineEmits(['update:loading', 'refreshData' ]);
const { proxy } = getCurrentInstance();
const title = ref("");
const formRef = ref(null);
const open = ref(false);
const loading = ref(false);
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
        nameInBatchApprovalList: [
            { required: true, message: "NameInBatch cannot be empty.", trigger: ['change', 'blur'] }
        ],
        gpn: [
            { required: true, message: "GPN cannot be empty.", trigger: ['change', 'blur'] }
        ],
        emailAddress: [
            { required: true, message: "EmailAddress cannot be empty.", trigger: ['change', 'blur'] }
        ],
        userName: [
            { required: true, message: "UserName cannot be empty.", trigger: ['change', 'blur'] }
        ],
        lpn: [
            { required: true, message: "LPN cannot be empty.", trigger: ['change', 'blur'] }
        ],
    }
});

const { form, rules } = toRefs(data);

// 取消按钮
function cancel() {
    open.value = false;
    reset();
}

/** 提交按钮 */
function submitForm() {
    proxy.$refs["formRef"].validate(valid => {
        if (valid) {
            addPending(form.value).then(response => {
                proxy.$modal.msgSuccess("Added successfully");
                open.value = false;
                emits('refreshData')
            });
        }
    });
}

/** 新增按钮操作 */
function handleAdd() {
    reset();
    open.value = true;
    title.value = i18n.global.t('message.NewPending.1');
    focusInput();
}

// 表单重置
function reset() {
    form.value.nameInBatchApprovalList = null;
    form.value.gpn = null;
    form.value.emailAddress = null;
    form.value.userName = null;
    form.value.lpn = null;
}

const focusInput = () => {
    setTimeout(() => {
        document.getElementById("firstField")?.focus()
    }, 100);
}

// 使用 defineExpose 来暴露 show 方法
defineExpose({
    handleAdd,
});
</script>

<style lang='scss' scoped>
.form-row {
    display: flex;
    width: 100%;
    align-items: flex-start;
}

.left-column,
.right-column {
    flex: 1;
    /* 两侧列等宽 */
    // background-color: #f0f0f0; /* 背景颜色 */
    padding: 0px;
    /* 内边距 */
    box-sizing: border-box;
    /* 包含内边距和边框 */
}

.center-column {
    width: 40px;
    /* 可定制的中间列宽度 */
    // background-color: #d0e0f0; /* 背景颜色 */
    // padding: 0px; /* 内边距 */
    padding-left: 4px;
    box-sizing: border-box;
    /* 包含内边距和边框 */
}</style>
    