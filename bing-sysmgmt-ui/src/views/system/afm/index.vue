<template>
    <div class="app-container">
        <el-upload
            ref="uploadRef"
            class="upload-demo"
            :before-upload="beforeUpload"
            :on-success="handleUploadSuccess"
            :action="uploadUrl"
            :auto-upload="false"
            :headers="headers"
            :data="uploadData"
            multiple
        >
            <template #trigger>
                <el-button type="primary">select file</el-button>
            </template>

            <el-button class="ml-3" type="primary" @click="submitUpload" style="margin-left: 10px;">
                upload to server
            </el-button>

            <template #tip>
            <div class="el-upload__tip">
                只能上传xlsx，docx，pdf，msg或图片文件，且不超过10MB
            </div>
            </template>
        </el-upload>
        <div v-if="showUrl">
            URL:
            <ul class="popup-result-scroll">
                <li v-for='item in generatedUrls' :key="item.url">
                    <div style="display: flex;flex-direction: column;">
                        <span>Name:{{item.name}}</span>
                        <span>URL:{{item.url}}</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>
<script lang="js" setup>
import { ref } from 'vue'
import { getToken } from "@/utils/auth";
const { proxy } = getCurrentInstance();
const uploadRef = ref();
const uploadUrl = ref(import.meta.env.VITE_APP_BASE_API + "/file/upload"); // 上传的图片服务器地址
const headers = ref({
  Authorization: "Bearer " + getToken()
});
// 定义要传递的数据对象
const uploadData = ref({
  directory: 'legaldatabase/templates/' // 替换为你需要的目录值
});
const generatedUrls = ref([]);
const showUrl = computed(() => {return generatedUrls.value.length > 0});

function beforeUpload(file) {
    //检查文件类型
    const fileName = file.name;
    const fileType = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
    const allowedFileTypes = ['.xlsx','.docx','.jpg', '.jpeg', '.png', '.pdf', '.msg'];
    //检查文件大小
    const isJpgPngPdfMsg = allowedFileTypes.includes(fileType);//file.type === 'image/jpeg';
    const isLt10M  = file.size / 1024 / 1024 < 10;
    if (!isJpgPngPdfMsg) {
        proxy.$modal.msgError(`'抱歉，您只能上传以下格式的文件：.xlsx, .docx, .pdf, .msg, .jpg, .jpeg, .png。请检查您的文件并重试。`);
    }
    else if (!isLt10M) {
        proxy.$modal.msgError('The uploaded file size cannot exceed 10MB!');
    }
    return isJpgPngPdfMsg && isLt10M;
}

function handleUploadSuccess(res, uploadFile, uploadFiles) {
    if(res.data) {
        generatedUrls.value.push(res.data);
    }
    else {
        proxy.$modal.msgError('上传失败!');
    }
}

function submitUpload() {
  uploadRef.value.submit();
}
</script>