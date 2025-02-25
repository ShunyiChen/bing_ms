<template>
    <div>
        <!-- 添加或修改DPNRequest对话框 -->
        <el-dialog :modal="false" :title="title" v-model="open" width="55%" :close-on-click-modal=false
         :close-on-press-escape=false draggable overflow style="border: 1px solid var(--el-color-primary);">
            <el-form ref="formRef" @submit.native.prevent v-loading="loading" element-loading-text="Verifying..."
             :model="form" label-suffix=":"
             :rules="rules" label-width="auto"
              label-position="right">
                <div class="form-row">
                    <el-form-item label="LPN" prop="lpn" class="left-column" >
                        <el-input id="lpnField" v-model="form.lpn" :disabled=true placeholder=""/>
                    </el-form-item>
                    <el-form-item class="center-column" >
                        <el-button  :icon="UserFilled" circle @click="handleOpen(1)"/>
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
                        <el-select v-model="form.status" placeholder="Select" clearable :disabled=true>
                            <el-option key="New Record" label="New Record" value="New Record" ></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <!-- <el-row >
                    <el-col :span="12">
                        <el-form-item label="Reject Reason" prop="rejectReason" class="custom-form-item">
                            <el-input v-model="form.rejectReason" placeholder=""  type="textarea" :rows="2"/>
                        </el-form-item>
                    </el-col>   
                    <el-col :span="12">
                        <el-form-item label="Cancel Reason" prop="cancelReason" class="custom-form-item">
                            <el-input v-model="form.cancelReason" placeholder=""  type="textarea" :rows="2"/>
                        </el-form-item>
                    </el-col>
                </el-row> -->
                <!-- <div class="form-row">
                    <el-form-item label="FIDS Received By" prop="fidsReceivedBy" class="custom-form-item">
                        <el-input v-model="form.fidsReceivedBy" placeholder="" />
                    </el-form-item>
                    <el-form-item label="FIDS Received Date" prop="fidsReceivedDate" class="custom-form-item">
                        <el-date-picker clearable v-model="form.fidsReceivedDate" type="datetime" 
                            placeholder="" style="width: 100%;">
                        </el-date-picker>
                    </el-form-item>
                </div> -->
                <div class="form-row">
                    <el-form-item label="Date Returned to IT" prop="returnedDate" class="left-column" >
                        <el-date-picker clearable v-model="form.returnedDate" type="datetime" 
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
                        <el-select v-model="form.deviceType" placeholder="Select" clearable>
                            <el-option v-for="dict in legaldb_device_type" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Device Location" prop="deviceLocation" class="left-column">
                        <el-select v-model="form.deviceLocation" placeholder="Select" clearable>
                            <el-option v-for="dict in legaldb_office" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="EYAsset No" prop="eyAssetNo" class="right-column">
                        <el-input v-model="form.eyAssetNo" maxLength="100" placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Serial No. (For OSS)" prop="serialNo" class="left-column">
                        <el-input v-model="form.serialNo" maxLength="100" placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Remark" prop="remark" class="right-column">
                        <el-select v-model="form.remark" placeholder="Select" clearable >
                            <el-option v-for="dict in legaldb_remark" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Received By" prop="receivedBy" class="left-column">
                        <el-input v-model.trim="form.receivedBy" placeholder="" :disabled=true />
                    </el-form-item>
                    <el-form-item class="center-column" >
                        <el-button  :icon="UserFilled" circle @click="handleOpen(2)" />
                    </el-form-item>
                    <el-form-item class="right-column" >
                    </el-form-item>
                </div>
                <!-- <el-row >
                    <el-col :span="12">
                        <el-form-item label="Engagement Code" prop="engagementCode" class="custom-form-item">
                            <el-input v-model="form.engagementCode" placeholder="" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="Engagement Name" prop="engagementName" class="custom-form-item">
                            <el-input v-model="form.engagementName" placeholder=""  type="textarea" :rows="2"/>
                        </el-form-item>
                    </el-col>
                </el-row> -->
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="submitForm">{{ $t('message.NewDPNRequest.1') }}</el-button>
                    <el-button @click="cancel">{{ $t('message.NewDPNRequest.2') }}</el-button>
                    <el-button @click="reset">{{ $t('message.NewDPNRequest.3') }}</el-button>
                </div>
            </template>
            
        </el-dialog>
        <!-- 选择EY用户组件 -->
        <SelectUser ref="selectUserRef" v-model:source="source"
            @getSelectedUser="getSelectedUser"
            @getSelectedReceiveBy="getSelectedReceiveBy"/>
    </div>
</template>
    
<script setup>
const props = defineProps({
    id: {
        type: Number,
        default: 0,
    },
    getDicts: {
        type: Function,
        required: true
    },
})
import i18n from '@/i18n'
import SelectUser from '@/components/SelectUser'
import { UserFilled } from '@element-plus/icons-vue'
import { addDPNRequest } from "@/api/legaldatabase/DPNRequest";
const { proxy } = getCurrentInstance();
// 使用字典数据
const { legaldb_device_type, legaldb_remark, legaldb_office, legaldb_email_suffixes } = props.getDicts();
const selectUserRef = ref(null);
const formRef = ref(null);
const open = ref(false);
const loading = ref(false);
const source = ref(1);
const title = ref("");
import { ref, watch } from 'vue'
import { get } from '@vueuse/core';
const emits = defineEmits(['update:loading', 'refreshData', 'updateUnreadCount']);

// 打开EY用户选择器
function handleOpen(val) {
    source.value = val;
    selectUserRef.value.handleOpen();
}

// 返回选中的EY用户
function getSelectedUser(row) {
    form.value.lpn = row.lpn;
    form.value.staffNo = row.staffNo;
    form.value.rankName = row.rankName;
    form.value.staffName = row.staffName;
    form.value.serviceLine = row.serviceLine;
    form.value.office = row.office;
    form.value.email = row.email;
}

// 返回选中的EY用户
function getSelectedReceiveBy(row) {
    form.value.receivedBy = row.email;
}

const validateLpnFormat = (rule, value, callback) =>  {
    if(!value) {
        callback(new Error('LPN cannot be empty.'));
    } else if(isValidSevenDigitNumber(value)) {
        callback();
    } else {
        callback(new Error('Please enter a 6-digit or 7-digit LPN.'));
    }
};

function isValidSevenDigitNumber(lpn) {
    // 正则表达式来匹配6位数字字母或7位数字
    const regex = /^(?:[A-Za-z0-9]{6}|[0-9]{7})$/;
    return regex.test(lpn);
}

const validateEmailFormat = (rule, value, callback) =>  {
    if(!value) {
        callback(new Error('Email cannot be empty.'));
    } else if(isValidEmail(value)) {
        callback();
    } else {
        callback(new Error('Please enter a valid EY email address.'));
    }
};

function isValidEmail(email) {
    // 定义允许的邮箱后缀
    const validSuffixes = [];
    Object.keys(legaldb_email_suffixes.value).some((key) => {
        validSuffixes.push(legaldb_email_suffixes.value[key].value);
    });
    if(!email) {
        return false;
    }
    // 将邮箱转换为小写
    const lowerCaseEmail = email.toLowerCase();
    // 使用正则表达式检查邮箱格式
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(lowerCaseEmail)) {
        return false; // 邮箱格式不正确
    }
    // 提取邮箱后缀
    const emailSuffix = lowerCaseEmail.split('@')[1];
    // 检查后缀是否在允许的后缀列表中
    return validSuffixes.includes(emailSuffix);
}

const data = reactive({
    form: {},
    rules: {
        staffNo: [
            { required: true, message: "StaffNo(GPN) cannot be empty.", trigger: ['change', 'blur'] },
        ],
        lpn: [
            { required: true, validator: validateLpnFormat, trigger: ['change', 'blur'] },
        ],
        staffName: [
            { required: true, message: "Staff name cannot be empty.", trigger: ['change', 'blur'] }
        ],
        office: [
            { required: true, message: "Office cannot be empty.", trigger: ['change', 'blur'] }
        ],
        email: [
            { required: true, message: 'Please input email address', trigger: ['change', 'blur']},
            { type: 'email', message: 'Please input correct email address', trigger: ['blur', 'change']},
        ],
        serviceLine: [
            { required: true, message: "ServiceLine cannot be empty.", trigger: ['change', 'blur'] }
        ],
        rankName: [
            { required: true, message: "EYRank cannot be empty.", trigger: ['change', 'blur'] }
        ],
        status: [
            { required: true, message: "Status cannot be empty.", trigger: ['change', 'blur'] }
        ],
        deviceType: [
            { required: true, message: "DeviceType cannot be empty.", trigger: ['change', 'blur'] }
        ],
        deviceLocation: [
            { required: true, message: "DeviceLocation cannot be empty.", trigger: ['change', 'blur'] }
        ],
        eyAssetNo: [
            { required: true, message: "EYAssetNo cannot be empty.", trigger: ['change', 'blur'] },
            { max: 100, message: 'EYAssetNo length must not exceed 100 characters.', trigger: ['change', 'blur'] }
        ],
        serialNo: [
            { max: 100, message: 'SerialNo length must not exceed 100 characters.', trigger: ['change', 'blur'] }
        ],
        remark: [
            { required: true, message: "Remark cannot be empty.", trigger: ['change', 'blur'] }
        ],
        receivedBy: [
            { required: true, validator: validateEmailFormat, trigger: ['change']},
        ]
    }
})
const { form, rules } = toRefs(data);

// 取消按钮
function cancel() {
    open.value = false;
    reset();
}

// 表单重置
function reset() {
    form.value.lpn = null;
    form.value.staffNo = null;
    form.value.staffName = null;
    form.value.serviceLine = null;
    form.value.email = null;
    form.value.office = null;
    form.value.rankName = null;
    form.value.receivedBy = null;
}

const focusInput = () => {
    setTimeout(() => {
        document.getElementById("lpnField")?.focus()
    }, 100);
}

/** 新增按钮操作 */
function handleAdd() {
    reset();
    open.value = true;
    title.value = i18n.global.t('message.NewDPNRequest.4');
    form.value.status = "New Record";
    focusInput();
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
            // console.log('所有字段验证通过，提交表单:', form.value);
            addDPNRequest(form.value).then(response => {
                proxy.$modal.msgSuccess("Added successfully");
                open.value = false;
                emits("updateUnreadCount", 'Waiting for Confirmation');
                emits('refreshData')
            });
        } else {
            console.log('表单验证失败');
        }
    });

}

// 在组件挂载时调用异步方法
onMounted(() => {
});

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
    