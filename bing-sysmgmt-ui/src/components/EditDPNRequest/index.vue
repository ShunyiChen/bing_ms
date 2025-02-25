<template>
    <div>
        <!-- 添加或修改DPNRequest对话框 -->
        <el-dialog :modal="false" :title="title" v-model="open" width="52%"
            :close-on-click-modal=false draggable overflow style="border: 1px solid var(--el-color-primary);">
            <el-form ref="formRef" @submit.native.prevent v-loading="loading" element-loading-text="Verifying..."
             :model="form" label-suffix=":"
             :rules="rules" label-width="auto"
              label-position="right">
                <div class="form-row">
                    <el-form-item label="LPN" prop="lpn" class="left-column" >
                        <el-input id="lpnField" v-model="form.lpn" :disabled=true placeholder=""/>
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Staff No. (GPN)" prop="staffNo" class="right-column" >
                        <el-input v-model="form.staffNo" :disabled=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Staff Name" prop="staffName" class="left-column">
                        <el-input v-model="form.staffName" :disabled=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Email" prop="email" class="right-column">
                        <el-input v-model="form.email" :disabled=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Office" prop="office" class="left-column">
                        <el-input v-model="form.office" :disabled=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Dept/Service Line" prop="serviceLine" class="right-column">
                        <el-input v-model="form.serviceLine" :disabled=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="EY Rank" prop="rankName" class="left-column">
                        <el-input v-model="form.rankName" :disabled=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Status" prop="status" class="right-column">
                        <el-select v-model="form.status" placeholder="Select" clearable>
                            <el-option v-for="dict in availableStatusOptions" :key="dict.value" :label="dict.value"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div v-if="form.status == 'FIDS Received'" class="form-row" >
                    <el-form-item label="FIDS Received By" prop="fidsReceivedBy" class="left-column" >
                        <el-input v-model="form.fidsReceivedBy" placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="FIDS Received Date" prop="fidsReceivedDate" class="right-column" >
                        <el-date-picker clearable v-model="form.fidsReceivedDate" type="datetime"
                            format="YYYY-MM-DD HH:mm:ss"
                            value-format="YYYY-MM-DD HH:mm:ss"
                            date-format="YYYY-MM-DD"
                            time-format="hh:mm:ss"
                            placeholder="" style="width: 100%;">
                        </el-date-picker>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Date Returned to IT" prop="returnedDate" class="left-column" >
                        <el-date-picker clearable v-model="form.returnedDate" type="datetime" :disabled="true"
                            format="YYYY-MM-DD HH:mm:ss"
                            value-format="YYYY-MM-DD HH:mm:ss"
                            date-format="YYYY-MM-DD"
                            time-format="hh:mm:ss"
                            placeholder="" style="width: 100%;">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Device Type" prop="deviceType" class="right-column">
                        <el-select v-model="form.deviceType" placeholder="Select" clearable :disabled="true">
                            <el-option v-for="dict in legaldb_device_type" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Device Location" prop="deviceLocation" class="left-column">
                        <el-select v-model="form.deviceLocation" placeholder="Select" clearable :disabled="true">
                            <el-option v-for="dict in legaldb_office" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="EYAsset No" prop="eyAssetNo" class="right-column">
                        <el-input v-model="form.eyAssetNo" :disabled="true" placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Serial No. (For OSS)" prop="serialNo" class="left-column">
                        <el-input v-model="form.serialNo" :disabled="true" placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Remark" prop="remark" class="right-column">
                        <el-select v-model="form.remark" :disabled="true" placeholder="Select" clearable >
                            <el-option v-for="dict in legaldb_remark" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Engagement Code" prop="engagementCode" class="left-column">
                        <el-input v-model="form.engagementCode" maxlength="8" :disabled="form.status != 'Rejected'" placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                        <el-button :icon="Search" circle @click="handleOpen(2)" :disabled="form.status != 'Rejected'"/>
                    </el-form-item>
                    <el-form-item label="Engagement Name" prop="engagementName" class="right-column">
                        <el-input v-model="form.engagementName" :disabled="form.status != 'Rejected'" placeholder="" type="textarea" :rows="1" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Received By" prop="receivedBy" class="left-column">
                        <el-input v-model.trim="form.receivedBy" :disabled=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item v-if="form.status == 'Rejected'" label="Reject Reason" prop="rejectReason" class="right-column">
                        <el-input v-model="form.rejectReason" placeholder="" 
                            type="textarea" :rows="2"/>
                    </el-form-item>
                    <el-form-item v-else-if="form.status == 'Cancelled by IT'" label="Cancel Reason" prop="cancelReason" class="right-column">
                        <el-input v-model="form.cancelReason" placeholder=""
                            type="textarea" :rows="2"/>
                    </el-form-item>
                    <el-form-item v-else class="right-column" >
                    </el-form-item>
                </div>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="submitForm">{{ $t('message.EditDPNRequest.2') }}</el-button>
                    <el-button @click="cancel">{{ $t('message.EditDPNRequest.3') }}</el-button>
                </div>
            </template>
        </el-dialog>
        <!-- 选择Engagement组件 -->
        <SelectEngagement ref="selectEngagementRef" @getSelectedEngagement="getSelectedEngagement" />
    </div>
</template>
    
<script setup>
const props = defineProps({
    getDicts: {
        type: Function,
        required: true
    }
})
import i18n from '@/i18n'
import { Search } from '@element-plus/icons-vue'
import SelectEngagement from '@/components/SelectEngagement'
import useUserStore from '@/store/modules/user'
import { getDPNRequest, updateDPNRequest } from "@/api/legaldatabase/DPNRequest";
const { proxy } = getCurrentInstance();
// 使用字典数据
const { legaldb_device_type, legaldb_remark, legaldb_office } = props.getDicts();
// const { legaldb_device_type, legaldb_remark, legaldb_office } = proxy.useDict("legaldb_device_type", "legaldb_remark", "legaldb_office");
const formRef = ref(null);
const open = ref(false);
const loading = ref(false);
const availableStatusOptions = ref(new Array());
const selectEngagementRef = ref(null);
const userStore = useUserStore()
const title = ref("");
const oldStatus = ref(null);
import { ref, watch } from 'vue'
const emits = defineEmits(['update:loading', 'refreshData', 'updateUnreadCount']);

const data = reactive({
    form: {},
    rules: {
        status: [
            { required: true, message: "Status cannot be empty.", trigger: ['change', 'blur'] }
        ],
        rejectReason: [
            { required: true, message: "RejectReason cannot be empty.", trigger: ['change', 'blur'] }
        ],
        cancelReason: [
            { required: true, message: "CancelReason cannot be empty.", trigger: ['change', 'blur'] }
        ],
        fidsReceivedBy: [
            { required: true, message: "FidsReceivedBy cannot be empty.", trigger: ['change', 'blur'] },
            { type: 'email', message: 'Please input correct email address', trigger: ['blur', 'change'] },
        ],
        fidsReceivedDate: [
            { required: true, message: "FidsReceivedDate cannot be empty.", trigger: ['change', 'blur'] }
        ],
    }
})
const { form, rules } = toRefs(data);

// 返回选中的Engagement
function getSelectedEngagement(row) {
    form.value.engagementName = row.engagementName;
    form.value.engagementCode = row.eid;
}

// 取消按钮
function cancel() {
    open.value = false;
    reset();
}

// 表单重置
function reset() {
    form.value = {
        id: null,
        staffNo: null,
        lpn: null,
        staffName: null,
        email: null,
        office: null,
        serviceLine: null,
        rankName: null,
        createdBy: null,
        created: null,
        status: null,
        rejectReason: null,
        returnedDate: null,
        deviceType: null,
        eyAssetNo: null,
        serialNo: null,
        remark: null,
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
    };
    proxy.resetForm("formRef");
}

// 打开Engagement选择器
function handleOpen() {
    selectEngagementRef.value.handleOpen();
}

/** 编辑按钮操作 */
function handleEdit(id) {
    reset();
    open.value = true;
    title.value = i18n.global.t('message.EditDPNRequest.1');
    loading.value = true;
    getDPNRequest(id).then(response => {
        form.value = response.data;
        oldStatus.value = response.data.status;
        loading.value = false;
        initStatusSelectOptions();
    });
}

function isRole(role) {
    return userStore.roles[0] === role;
}

function initStatusSelectOptions() {
    availableStatusOptions.value = [];
    const status = form.value.status;

    // 定义可用状态选项
    const roleActions = {
        'OSTS': {
            'Approved': ["Formatted", "Cancelled by IT"],
            'Rejected': ["Cancelled by IT"],
            'Waiting for Confirmation': ["Cancelled by IT"],
            'Pending Legal Review': ["Cancelled by IT"]
        },
        'Legal': {
            'Approved': ["Rejected"],
            'Rejected': ["Approved"],
            'Waiting for Confirmation': ["Approved", "Rejected"],
            'Pending Legal Review': ["Approved", "Rejected"]
        },
        'FIDS': {
            'Rejected': ["FIDS Received"]
        }
    };

    // 根据当前状态和角色获取可用选项
    for (const [role, statuses] of Object.entries(roleActions)) {
        if (isRole(role) && statuses[status]) {
            availableStatusOptions.value.push(...statuses[status].map(value => ({ value })));
            form.value.status = null; // 清空状态
            return; // 找到匹配的角色后退出
        }
    }

    // 如果没有匹配的角色和状态，保留当前状态
    availableStatusOptions.value.push({ value: status });
}

/** 提交按钮 */
function submitForm() {
    const fields = formRef.value.fields;
    let validationPromises = [];
    // 遍历字段并进行操作
    fields.forEach(field => {
        // 将 validateField 的调用封装成一个 Promise
        const promise = new Promise((resolve) => {
            formRef.value.validateField(field.prop, (valid) => {
                resolve(valid);
            });
        });
        validationPromises.push(promise);
    });
    
    // 等待所有字段的验证完成
    Promise.all(validationPromises).then((results) => {
        const allValid = results.every(valid => valid);
        if (allValid) {

            form.value.newStatus = form.value.status;
            form.value.status = oldStatus.value;
            // console.log('所有字段验证通过，提交表单:', form.value);
            updateDPNRequest(form.value).then(response => {
                proxy.$modal.msgSuccess("Updated successfully");
                open.value = false;
                emits("updateUnreadCount", form.value.newStatus);
                emits('refreshData')
            });
        } else {
            console.log('表单验证失败');
        }
    });
}

// 使用 defineExpose 来暴露 show 方法
defineExpose({
    handleEdit,
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
        flex: 1; /* 两侧列等宽 */
        // background-color: #f0f0f0; /* 背景颜色 */
        padding: 0px; /* 内边距 */
        box-sizing: border-box; /* 包含内边距和边框 */
    }

    .center-column {
        width: 40px; /* 可定制的中间列宽度 */
        // background-color: #d0e0f0; /* 背景颜色 */
        // padding: 0px; /* 内边距 */
        padding-left: 4px;
        box-sizing: border-box; /* 包含内边距和边框 */
    }
 
</style>
    