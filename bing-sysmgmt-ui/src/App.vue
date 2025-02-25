<template>
  <div style="display: flex;flex-direction: column;height: 100%;">
    <SecurityBanner @close="handleBannerClose" v-if="securityBannerEnabled"/>
    <div id="appDiv" :class="securityBannerEnabled ? 'securityBannerEnabledClass' : 'securityBannerDisabledClass'">
      <router-view />
    </div>
  </div>
</template>

<script setup>
import SecurityBanner from '@/components/SecurityBanner'
import useSettingsStore from '@/store/modules/settings'
import useAppStore from "@/store/modules/app";
import { handleThemeStyle, changeSidebarBgColor } from '@/utils/theme'
import { ref } from 'vue';
const appStore = useAppStore()
const settingsStore = useSettingsStore()
const sideTheme = computed(() => settingsStore.sideTheme);
const dark = computed(() => appStore.dark);
const securityBannerEnabled = ref(false);

onMounted(() => {
  nextTick(() => {
    // 初始化主题样式
    handleThemeStyle(useSettingsStore().theme)
    // 初始化Dark主题
    if(appStore.dark == 'true') {
      document.documentElement.classList.toggle('dark')
    }
    // 初始化Sidebar背景颜色
    changeSidebarBgColor(dark.value.toString(), sideTheme.value);
  })
})

const handleBannerClose = () => {
  const appDiv = document.getElementById("appDiv");
  setTimeout(() => {
    appDiv.style.transform = 'none';
    appDiv.style.overflowY = 'auto';
  }, 10);

}

</script>

<style lang='scss' scoped>

    .securityBannerEnabledClass {
      height: 100%;
      transition: transform 0.2s ease, opacity 0.2s ease;
      transform: translateY(50.8px);
    }
    .securityBannerDisabledClass {
      height: 100%;
    }
</style>

