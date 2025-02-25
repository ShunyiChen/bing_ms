<template>
    <div>
        <!-- 添加或修改DPNRequest对话框 -->
        <el-dialog :modal="false" :title="title" v-model="open" width="45%" :close-on-click-modal="false"
            :close-on-press-escape=false draggable style="border: 1px solid var(--el-color-primary);">
            <div style="display: flex;flex-direction: column;">
                <div>
                    <el-input id="focusField" v-model="form.keyword" maxlength="30" style="width: 260px;" :placeholder="$t('message.SelectEngagement.1')"
                        :prefix-icon="Search" clearable @keyup.enter="handleFind"/>
                    <el-button type="primary" @click="handleFind">{{ $t('message.SelectEngagement.2') }}</el-button>
                </div>
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="Engagements" name="first">
                        <el-table v-loading="loading" :data="tableData" @row-dblclick="handleDoubleClick" style="width: 100%;height: 400px;">
                            <el-table-column prop="eid" label="EID" width="200" />
                            <el-table-column prop="engagementName" label="Engagement Name" width="400" />
                            <el-table-column fixed="right" :label="$t('message.SelectEngagement.3')" min-width="100">
                                <template #default="scope">
                                    <el-button link type="primary" @click="handleSelect(scope.row)">
                                        {{ $t('message.SelectEngagement.4') }}
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>

                    </el-tab-pane>
                </el-tabs>
            </div>
            <template #footer>
                <div class="dialog-footer">
                    <div style="display: flex;justify-content: space-between;;">
                        <el-text>{{ $t('message.SelectEngagement.6', { num: tableData.length }) }}</el-text>
                        <el-button @click="cancel">{{ $t('message.SelectEngagement.5') }}</el-button>
                    </div>
                </div>
            </template>
        </el-dialog>
    </div>
</template>
    
<script setup>
// const props = defineProps({
//     source: {
//         type: Number,
//         default: 1,
//     }
// })
import { Search, SuccessFilled, InfoFilled, UserFilled } from '@element-plus/icons-vue'
import { getEngagementByEID } from "@/api/legaldatabase/DPNRequest";
const activeName = ref('first')
const { proxy } = getCurrentInstance();
const open = ref(false);
const loading = ref(false);
const title = ref("");
const tableData = ref([]);
import { ref } from 'vue'
const emits = defineEmits(['getSelectedUser']);
const data = reactive({
    form: {},
    rules: {
    }
})
const { form, rules } = toRefs(data);

const handleFind = () => {
    if(!form.value.keyword) {
        return;
    }
    loading.value = true;
    tableData.value = [];
    getEngagementByEID({ EID: form.value.keyword }).then((res) => {
        if (res.data.response.length > 0) {
            res.data.response.map(e => {
                const { eid, c_LNM } = e;
                const data = {
                    eid: eid,
                    engagementName: c_LNM
                }
                tableData.value.push(data);
            });
        }
        loading.value = false;
    }).catch(err => {
        loading.value = false;
        proxy.$modal.msgError('Failed to retrieve engagement information, please try again.');
    });
}

function handleDoubleClick(row, column, event) {
    handleSelect(row);
}

const handleSelect = (row) => {
    emits('getSelectedEngagement', row);
    open.value = false;
}

// 取消按钮
function cancel() {
    open.value = false;
    reset();
}

// 表单重置
function reset() {
    form.value = {
        keyword: null
    };
    proxy.resetForm("formRef");
}

/** 新增按钮操作 */
function handleOpen() {
    reset();
    open.value = true;
    title.value = "Select Engagement";
    focusInput();
}

const handleClick = (tab, event) => {
    console.log(tab, event)
}

const focusInput = () => {
    setTimeout(() => {
        document.getElementById("focusField")?.focus()
    }, 100);
}

// 使用 defineExpose 来暴露 show 方法
defineExpose({
    handleOpen,
});

</script>
    
<style lang='scss' scoped>
.form-row {
    display: flex;
    flex-wrap: wrap;
    // justify-content: flex-start;
}

.custom-form-item {
    flex: 1 1 50%;
    /* 每个表单项占据大约三分之一的宽度 */
    margin-bottom: 15px;
    /* 调整每个表单项的底部间距 */
    width: 100%;
    padding: 0px 2px;
}
</style>
    