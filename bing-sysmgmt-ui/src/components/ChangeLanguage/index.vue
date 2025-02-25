<template>
    <div>
      <el-dropdown trigger="click" @command="handleSetLanguage">
        <div class="size-icon--style">
          <svg-icon class-name="size-icon" icon-class="language" />
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item v-for="item of sizeOptions" :key="item.value" :disabled="language === item.value" :command="item.value">
              {{ item.label }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </template>
  
  <script setup>
  import i18n from '@/i18n'
  import useAppStore from "@/store/modules/app";
  
  const appStore = useAppStore();
  const language = computed(() => appStore.language);
  const route = useRoute();
  const router = useRouter();
  const { proxy } = getCurrentInstance();
  const sizeOptions = ref([
    { label: "简体中文", value: "zhcn" },
    { label: "繁體中文", value: "zhtw" },
    { label: "English", value: "en" },
  ]);
  
  function handleSetLanguage(language) {
    proxy.$modal.loading(i18n.global.t('message.ChangeLanguage.1'));
    appStore.setLanguage(language);
    setTimeout("window.location.reload()", 1000);
  }
  </script>
  
  <style lang='scss' scoped>
  .size-icon--style {
    font-size: 18px;
    line-height: 50px;
    // padding-right: 7px;
  }
  </style>