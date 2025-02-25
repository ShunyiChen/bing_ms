<template>
  <div class="sidebar-logo-container" :class="{ 'collapse': collapse }" :style="{ backgroundColor: sideTheme === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground }">
    <transition name="sidebarLogoFade">
      <router-link v-if="collapse" key="collapse" class="sidebar-logo-link" to="/">
        <img v-if="logo" :src="eyLogo" class="sidebar-logo" />
        <h1 v-else class="sidebar-title" :style="{ color: (sideTheme === 'theme-dark' || dark.toString() == 'true') ? variables.logoTitleColor : variables.logoLightTitleColor }">{{ title }}</h1>
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <img v-if="logo" :src="eyLogo" class="sidebar-logo" />
        <h1 class="sidebar-title" :style="{ color: (sideTheme === 'theme-dark' || dark.toString() == 'true') ? variables.logoTitleColor : variables.logoLightTitleColor }">{{ title }}</h1>
      </router-link>
    </transition>
  </div>
</template>

<script setup>
import variables from '@/assets/styles/variables.module.scss'
// import logo from '@/assets/logo/logo.png'
import logo from '@/assets/logo/EYLogo.png'
import logoBlack from '@/assets/logo/EYLogoBlack.png'
import useSettingsStore from '@/store/modules/settings'
import useAppStore from '@/store/modules/app'
const appStore = useAppStore();
const dark = computed(() => appStore.dark);
const title = import.meta.env.VITE_APP_TITLE;
const settingsStore = useSettingsStore();
const sideTheme = computed(() => settingsStore.sideTheme);
const eyLogo = computed(() => 
  {
    if(sideTheme.value === 'theme-dark' || dark.value.toString() == 'true') {
      return logo;
    } else {
      return logoBlack;
    }
  }
);
 

defineProps({
  collapse: {
    type: Boolean,
    required: true
  }
})

</script>

<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 60px;
  background: none !important;
  text-align: left;
  overflow: hidden;
  padding-left: 15px;
  margin-bottom: 15px;

  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 32px;
      height: 32px;
      vertical-align: middle;
      margin-right: 12px;
    }

    & .sidebar-title {
      display: inline-block;
      margin: 0;
      color: #fff;
      font-weight: 600;
      // line-height: 50px;
      font-size: 11pt;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
      margin-top: 24px;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
  }
}
</style>