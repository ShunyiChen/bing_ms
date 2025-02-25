<template>
  <el-dialog v-model="dialogOverflowVisible" :modal="false" title="" width="600" draggable overflow
    :before-close="close" :show-close="false" style="border: 1px solid var(--el-color-primary);padding: 5px;">

    <template #header="{ close, titleId, titleClass }" >
      <div style="display: flex;justify-content: space-between;">
        <el-text style="white-space: nowrap;font-size: large;" type="primary">Filter</el-text>
        <div style="display: flex;justify-content: flex-end;">
          <el-checkbox label="Office Scope:" @change="setEnable" />
          <el-select v-model="scope" multiple clearable collapse-tags placeholder="Select" popper-class="custom-header"
            :max-collapse-tags="1" :disabled="enable" style="width: 210px">
            <template #header>
              <el-checkbox v-model="checkAll" :indeterminate="indeterminate" @change="handleCheckAll">
                All
              </el-checkbox>
            </template>
            <el-option v-for="item in legaldb_office" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </div>
      </div>
    </template>
    <div>
      <div style="display: flex;flex-direction: row;margin-top: -10px;gap: 4px;">
        <el-text style="white-space: nowrap;">Find what:</el-text>
        <el-input id="inputRef" v-model="queryParams.findWhat" placeholder="" clearable @keyup.enter="handleQuery"/>
        <el-button type="primary" :icon="Search" @click="handleQuery">Find</el-button>
      </div>
    </div>
  </el-dialog>
</template>
  
<script setup>
const props = defineProps({
  loading: {
      type: Boolean,
      default: false,
  }
})
import { listDPNRequest } from "@/api/legaldatabase/DPNRequest";
import { Search } from '@element-plus/icons-vue'
const { proxy } = getCurrentInstance();
const { legaldb_office } = proxy.useDict("legaldb_office");
import { ref, watch } from 'vue'
const emits = defineEmits(['update:loading', 'getFilterResult', 'getCount']);
const data = reactive({
  queryParams: {
    findWhat: null,
    scope: []
  }
})
const { queryParams } = toRefs(data);
const enable = ref(true)
const scope = ref([])
const checkAll = ref(false)
const indeterminate = ref(false)
const dialogOverflowVisible = ref(false);

watch(queryParams.value.scope, (val) => {
  if (val.length === 0) {
    checkAll.value = false
    indeterminate.value = false
  } else if (val.length === legaldb_office.value.length) {
    checkAll.value = true
    indeterminate.value = false
  } else {
    indeterminate.value = true
  }
})

const handleCheckAll = (val) => {
  indeterminate.value = false
  if (val) {
    scope.value = legaldb_office.value.map((_) => _.value)
  } else {
    scope.value = []
  }
}

function show(newQueryParams) {
  queryParams.value = newQueryParams;
  dialogOverflowVisible.value = true;
  focusInput()
}

const close = () => {
  dialogOverflowVisible.value = false;
}

function isShowing() {
  return dialogOverflowVisible.value
}

function setEnable() {
  enable.value = !enable.value;
}

function handleQuery() {
  emits("update:loading", true);
  queryParams.value.scope = enable.value ? [] : scope.value;
  listDPNRequest(queryParams.value).then(response => {
    emits('getFilterResult', response, queryParams.value.findWhat, queryParams.value.scope);
  });
  emits('getCount', queryParams.value)
  dialogOverflowVisible.value = false;
}

const focusInput = () => {
  setTimeout(() => {
    document.getElementById("inputRef")?.focus()
  },10);
}

// 使用 defineExpose 来暴露 show 方法
defineExpose({
  show,
  close,
  isShowing,
});
 
</script>
  
<style lang='scss' scoped>
.custom-header {
  .el-checkbox {
    display: flex;
    height: unset;
  }
}

.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 16px;
}
</style>
  