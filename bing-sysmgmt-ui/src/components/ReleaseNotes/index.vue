<template>
    <el-collapse v-model="activeNames">
        <el-collapse-item title="🌟 New Features" name="1" :icon="CaretRight">
            <div>
                <ul class="custom-ul">
                    <li v-for="(item, index) of newFeatures">{{item.notes}}</li>
                </ul>
            </div>
        </el-collapse-item>
        <el-collapse-item title="⚡ Bug Fixes" name="2">
            <template #icon="{ isActive }">
                <span class="icon-ele">
                    {{ isActive ? 'Expanded' : 'Collapsed' }}
                </span>
            </template>
            <ul class="custom-ul">
                <li v-for="(item, index) of bugFixes">{{item.notes}}</li>
            </ul>
        </el-collapse-item>
        <el-collapse-item title="🚀 Improvements" name="3">
            <ul class="custom-ul">
                <li v-for="(item, index) of improvements">{{item.notes}}</li>
            </ul>
        </el-collapse-item>
        <el-collapse-item title="🛸 Additional Resources" name="4">
            <ul class="custom-ul">
                <li v-for="(item, index) of additionalResources">{{item.notes}}</li>
            </ul>
        </el-collapse-item>
    </el-collapse>
</template>
<script setup>
const props = defineProps({
    releaseDate: {
        type: String,
        default: '1970/01/01',
    },
    load: {
        type: Boolean,
        default: false,
    }
})
import { getReleaseNotesByDate } from "@/api/system/releasenotes";
import { CaretRight } from '@element-plus/icons-vue'
const activeNames = ref(['1', '2', '3', '4'])
const newFeatures = ref([]);
const bugFixes = ref([]);
const improvements = ref([]);
const additionalResources = ref([]);

// 取发布内容根据发布日期和系统名称
function findReleaseNotesByDate() {
    getReleaseNotesByDate({systemName: 'LegalDB', releaseDate: props.releaseDate}).then(response => {
        newFeatures.value = response['New Features'];
        bugFixes.value = response['Bug Fixes'];
        improvements.value = response['Improvements'];
        additionalResources.value = response['Additional Resources'];
        expandAll();
    });
}

function expandAll() {
    // 展开所有
    activeNames.value = ['1', '2', '3', '4'];
}

// 在组件挂载时调用异步方法
onMounted(() => {
    if(props.load) {
        findReleaseNotesByDate();
    }
});

// 使用 defineExpose 来暴露 show 方法
defineExpose({
    findReleaseNotesByDate,
});

</script>
<style scoped lang="scss">
:deep(.el-collapse-item__content) {
    color: grey;
    // text-align: center;
}

:deep(.el-collapse-item__header) {
    color: grey;
    font-weight: bold;
}

.custom-ul {
    margin: 0;
}

</style>