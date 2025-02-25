<template>
    <div class="sidebar-logo-container" :class="{ 'collapse': collapse }"
        :style="{ backgroundColor: sideTheme === 'theme-dark' ? variables.menuBackground : variables.menuLightBackground }">
        <div v-if="!collapse" class="sidebar-logo-link">
            <div class="sidebar-title">
                <el-icon style="margin-right: 5px;">
                    <LocationInformation />
                </el-icon>
                <span class="text-border">{{ title }}</span>
            </div>
        </div>
    </div>
</template>

<script setup>
import variables from '@/assets/styles/variables.module.scss'
import logoImg from '@/assets/logo/EYLogo.png'
import useSettingsStore from '@/store/modules/settings'
const settingsStore = useSettingsStore()
const props = defineProps({
    collapse: {
        type: Boolean,
        required: true
    }
})
const sideTheme = computed(() => settingsStore.sideTheme);
const title = ref('系统管理端');
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
    height: 25px;
    // line-height: 60px;
    // background: red;
    text-align: center;
    overflow: hidden;
    padding-left: 10px;
    // margin-bottom: 15px;

    & .sidebar-logo-link {
        height: 100%;
        width: 100%;

        & .sidebar-logo {
            width: 32px;
            height: 16px;
            vertical-align: middle;
            margin-right: 12px;
        }

        & .sidebar-title {
            // display: inline-block;
            margin: 5px 0px;
            color: #747480;
            // font-weight: 600;
            // line-height: 50px;
            font-size: small;
            font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
            // vertical-align: middle;
            // margin-top: 21px;

            & .text-border {
                border: 1px solid #747480;
                /* 边框颜色和宽度 */
                border-radius: 5px;
                /* 圆角半径 */
                padding: 3px;
                /* 内边距，增加文字和边框之间的空间 */
            }
        }
    }

    &.collapse {
        .sidebar-logo {
            margin-right: 0px;
        }
    }
}
</style>