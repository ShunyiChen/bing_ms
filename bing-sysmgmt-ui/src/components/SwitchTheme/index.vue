<template>
    <div>
      <el-switch class="switch-border" v-model="isDark" :active-action-icon="Moon" :inactive-action-icon="Sunny" @change="toggleTheme" />
    </div>
</template>
  
<script setup>
    import { ref } from 'vue';
    import { Sunny, Moon } from '@element-plus/icons-vue';
    import useAppStore from "@/store/modules/app";
    import useSettingsStore from '@/store/modules/settings'
    import { changeSidebarBgColor } from '@/utils/theme'
    const settingsStore = useSettingsStore();
    const sideTheme = computed(() => settingsStore.sideTheme);
    const appStore = useAppStore();
    const isDark = ref(appStore.dark == 'true')
    // 切换黑暗主题
    function toggleTheme() {
      document.documentElement.classList.toggle('dark')
      appStore.setDark(isDark.value);
 
      // 同步修改Sidebar背景颜色
      changeSidebarBgColor(isDark.value.toString(), sideTheme.value);
    }
</script>
  
<style lang='scss' scoped>
  .switch-border ::v-deep .el-switch__core {
    border: 1px solid var(--el-color-primary);
    border-radius: 20px;
    margin-bottom: 5px;
  }
 
</style>