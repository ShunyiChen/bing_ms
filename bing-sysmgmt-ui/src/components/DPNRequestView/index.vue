<template>
    <div>
        <!-- 添加或修改DPNRequest对话框 -->
        <el-dialog :modal="false" :title="title" v-model="open" width="52%" :close-on-click-modal=false 
            :close-on-press-escape=false draggable overflow style="border: 1px solid var(--el-color-primary);">
            <el-form ref="formRef" @submit.native.prevent v-loading="loading" 
             :rules="rules" label-width="auto"
              label-position="right">
                <div class="form-row">
                    <el-form-item label="LPN" prop="lpn" class="left-column" >
                        <el-input id="lpnField" v-model="form.lpn" :readonly=true placeholder=""/>
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Staff No. (GPN)" prop="staffNo" class="right-column" >
                        <el-input v-model="form.staffNo" :readonly=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Staff Name" prop="staffName" class="left-column">
                        <el-input v-model="form.staffName" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Email" prop="email" class="right-column">
                        <el-input v-model="form.email" :readonly=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Office" prop="office" class="left-column">
                        <el-input v-model="form.office" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Dept/Service Line" prop="serviceLine" class="right-column">
                        <el-input v-model="form.serviceLine" :readonly=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="EY Rank" prop="rankName" class="left-column">
                        <el-input v-model="form.rankName" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Status" prop="status" class="right-column">
                        <el-select v-model="form.status" placeholder="Select" class="readonly-select" @focus.prevent>
                            <el-option v-for="dict in legaldb_status" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div v-if="form.status == 'FIDS Received'" class="form-row" >
                    <el-form-item label="FIDS Received By" prop="fidsReceivedBy" class="left-column" >
                        <el-input v-model="form.fidsReceivedBy" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="FIDS Received Date" prop="fidsReceivedDate" class="right-column" >
                        <el-date-picker clearable v-model="form.fidsReceivedDate" type="datetime" :readonly=true
                            placeholder="" style="width: 100%;">
                        </el-date-picker>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Date Returned to IT" prop="returnedDate" class="left-column" >
                        <el-date-picker clearable v-model="form.returnedDate" type="datetime" :readonly=true
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
                        <el-select v-model="form.deviceType" placeholder="Select" class="readonly-select" @focus.prevent>
                            <el-option v-for="dict in legaldb_device_type" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Device Location" prop="deviceLocation" class="left-column">
                        <el-select v-model="form.deviceLocation" placeholder="Select" class="readonly-select" @focus.prevent>
                            <el-option v-for="dict in legaldb_office" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="EYAsset No" prop="eyAssetNo" class="right-column">
                        <el-input v-model="form.eyAssetNo" :readonly=true placeholder="" />
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Serial No. (For OSS)" prop="serialNo" class="left-column">
                        <el-input v-model="form.serialNo" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Remark" prop="remark" class="right-column">
                        <el-select v-model="form.remark" :readonly=true placeholder="Select" class="readonly-select" @focus.prevent>
                            <el-option v-for="dict in legaldb_remark" :key="dict.value" :label="dict.label"
                                :value="dict.value"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Engagement Code" prop="engagementCode" class="left-column">
                        <el-input v-model="form.engagementCode" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item label="Engagement Name" prop="engagementName" class="right-column">
                        <el-input v-model="form.engagementName" :readonly=true placeholder="" type="textarea" :rows="1"/>
                    </el-form-item>
                </div>
                <div class="form-row">
                    <el-form-item label="Received By" prop="receivedBy" class="left-column">
                        <el-input v-model.trim="form.receivedBy" :readonly=true placeholder="" />
                    </el-form-item>
                    <el-form-item class="center-column" >
                    </el-form-item>
                    <el-form-item v-if="form.status == 'Rejected'" label="Reject Reason" prop="rejectReason" class="right-column">
                        <el-input v-model="form.rejectReason" placeholder="" type="textarea" :rows="2" :readonly=true />
                    </el-form-item>
                    <el-form-item v-else-if="form.status == 'Cancelled by IT'" label="Cancel Reason" prop="cancelReason" class="right-column">
                        <el-input v-model="form.cancelReason" placeholder="" type="textarea" :rows="2" :readonly=true />
                    </el-form-item>
                    <el-form-item v-else class="right-column" >
                    </el-form-item>
                </div>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="enterEditMode" v-if="canEdit">{{ $t('message.DPNRequestView.2') }}</el-button>
                    <el-button @click="cancel">{{ $t('message.DPNRequestView.3') }}</el-button>
                </div>
            </template>
        </el-dialog>
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
import { getDPNRequest} from "@/api/legaldatabase/DPNRequest";
const { proxy } = getCurrentInstance();
// 使用字典数据
const { legaldb_device_type, legaldb_remark, legaldb_office, legaldb_status } = props.getDicts();
const formRef = ref(null);
const viewId = ref(0);
const open = ref(false);
const loading = ref(false);
const title = ref("");
const canEdit = ref(false);
import { ref, watch } from 'vue'
const emits = defineEmits(['update:loading', 'handleUpdate']);

const data = reactive({
    form: {},
    rules: {
    }
})
const { form, rules } = toRefs(data);

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
        rejecter: null
    };
    proxy.resetForm("formRef");
}

/** 新增按钮操作 */
function handleView(id, edit) {
    reset();
    viewId.value = id;
    canEdit.value = edit;
    open.value = true;
    title.value = i18n.global.t('message.DPNRequestView.1');
    loading.value = true;
    getDPNRequest(id).then(response => {
        form.value = response.data;
        loading.value = false;
    });
}

function enterEditMode() {
    cancel();
    setTimeout(() => {
        emits('handleUpdate', viewId.value);
    }, 200);
}

// 取消按钮
function cancel() {
    open.value = false;
    reset();
}

// 使用 defineExpose 来暴露 show 方法
defineExpose({
    handleView,
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

    .readonly-select {
        pointer-events: none; /* 禁止用户点击 */
    }
 
</style>
    