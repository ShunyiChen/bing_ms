<template>
  <div class="navbar">
    <hamburger id="hamburger-container" :is-active="appStore.sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />
    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" v-if="!settingsStore.topNav" />
    <top-nav id="topmenu-container" class="topmenu-container" v-if="settingsStore.topNav" />

    <div class="right-menu">
      <template v-if="appStore.device !== 'mobile'">
        <!-- <header-search id="header-search" class="right-menu-item" />

        <el-tooltip content="源码地址" effect="dark" placement="bottom">
          <ruo-yi-git id="ruoyi-git" class="right-menu-item hover-effect" />
        </el-tooltip> -->
        
        <!-- 切换主题 -->
        <el-tooltip :content="$t('message.Navbar.1')" effect="light" placement="bottom">
          <switch-theme id="switch-theme" />
        </el-tooltip>

        <div class="el-divider--vertical"></div>

        <!-- 文档地址 -->
        <el-tooltip :content="$t('message.Navbar.2')" effect="light" placement="bottom">
          <ruo-yi-doc id="ruoyi-doc" class="right-menu-item hover-effect" />
        </el-tooltip>

        <!-- 全屏显示 -->
        <screenfull id="screenfull" class="right-menu-item hover-effect" />

        <!-- 切换语言 -->
        <el-tooltip :content="$t('message.Navbar.3')" effect="light" placement="bottom">
          <change-language id="change-language" class="right-menu-item hover-effect" />
        </el-tooltip>

        <!-- 布局大小 -->
        <el-tooltip :content="$t('message.Navbar.4')" effect="light" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect" />
        </el-tooltip>
        
      </template>
      <div class="avatar-container">
        <el-dropdown @command="handleCommand" class="right-menu-item hover-effect" trigger="click">
          <div class="avatar-wrapper">
            <!-- <img :src="userStore.avatar" class="user-avatar" /> -->
            <el-avatar shape="circle" :style="`background-color:${getRGB(userStore.name)};font-size:13pt;font-weight:bold;`"> {{getAvatar(userStore.name)}} </el-avatar>
            <el-icon><caret-bottom /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <!-- 个人中心 -->
              <router-link to="/user/personalData">
                <el-dropdown-item>{{ $t('message.Navbar.5') }}</el-dropdown-item>
              </router-link>
              <!-- 布局设置 -->
              <el-dropdown-item command="setLayout" v-if="settingsStore.showSettings">
                <span>{{ $t('message.Navbar.6') }}</span>
              </el-dropdown-item>
              <!-- 退出登录 -->
              <el-dropdown-item divided command="logout">
                <span>{{ $t('message.Navbar.7') }}</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import i18n from '@/i18n'
import { ElMessageBox } from 'element-plus'
import Breadcrumb from '@/components/Breadcrumb'
import TopNav from '@/components/TopNav'
import Hamburger from '@/components/Hamburger'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import SwitchTheme from '@/components/SwitchTheme'
import ChangeLanguage from '@/components/ChangeLanguage'
// import HeaderSearch from '@/components/HeaderSearch'
// import RuoYiGit from '@/components/RuoYi/Git'
import RuoYiDoc from '@/components/RuoYi/Doc'
import useAppStore from '@/store/modules/app'
import useUserStore from '@/store/modules/user'
import useSettingsStore from '@/store/modules/settings'
import { getAvatarInitials, stringToRGB } from '@/utils/ruoyi'

const appStore = useAppStore()
const userStore = useUserStore()
const settingsStore = useSettingsStore()

function toggleSideBar() {
  appStore.toggleSideBar()
}

function handleCommand(command) {
  switch (command) {
    case "setLayout":
      setLayout();
      break;
    case "logout":
      logout();
      break;
    default:
      break;
  }
}
function getRGB(name) {
  return stringToRGB(name)
};
function getAvatar(email) {
  return getAvatarInitials(email)
};
function logout() {
  ElMessageBox.confirm(i18n.global.t('message.logout.1'), i18n.global.t('message.logout.4'), {
    confirmButtonText: i18n.global.t('message.logout.2'),
    cancelButtonText: i18n.global.t('message.logout.3'),
    type: 'warning'
  }).then(() => {
    userStore.logOut().then(() => {
      location.href = '/sysmgmt/index';
    })
  }).catch(() => { });
}

const emits = defineEmits(['setLayout'])
function setLayout() {
  emits('setLayout');
}
</script>

<style lang='scss' scoped>

.el-divider--vertical {
    display: inline-block;
    width: 1px;
    height: 1em;
    // margin: 0 8px;
    vertical-align: middle;
    position: relative;
    margin-left: 20px;
    margin-right: 10px;
    margin-top: 15px;
}

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  // background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .topmenu-container {
    position: absolute;
    left: 50px;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;
    display: flex;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 10px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 20px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        i {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
