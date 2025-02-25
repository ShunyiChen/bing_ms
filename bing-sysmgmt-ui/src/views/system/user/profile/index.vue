<template>
   <div class="app-container">
      <el-row :gutter="20">
         <el-col :span="7" :xs="24">
            <el-card class="box-card" style="height: 422px;">
               <template v-slot:header>
                 <div class="clearfix">
                   <span>{{ $t('message.personalData.1') }}</span>
                 </div>
               </template>
               <div>
                  <div class="text-center">
                     <!-- <userAvatar /> -->
                     <el-avatar shape="circle" :size=100 :style="`background-color:${getRGB(state.user.userName)};font-size:40px`"> {{getAvatar(state.user.userName)}} </el-avatar>
                  </div>
                  <ul class="list-group list-group-striped">
                     <li class="list-group-item">
                        <svg-icon icon-class="user" />{{ $t('message.personalData.2') }}
                        <div class="pull-right">{{ state.user.userName }}</div>
                     </li>
                     <!-- <li class="list-group-item">
                        <svg-icon icon-class="phone" />手机号码
                        <div class="pull-right">{{ state.user.phonenumber }}</div>
                     </li> -->
                     <li class="list-group-item">
                        <svg-icon icon-class="email" />{{ $t('message.personalData.3') }}
                        <div class="pull-right">{{ state.user.email }}</div>
                     </li>
                     <li class="list-group-item">
                        <svg-icon icon-class="tree" />{{ $t('message.personalData.4') }}
                        <div class="pull-right" v-if="state.user.dept">{{ state.user.dept.deptName }} / {{ state.postGroup }}</div>
                     </li>
                     <li class="list-group-item">
                        <svg-icon icon-class="peoples" />{{ $t('message.personalData.5') }}
                        <div class="pull-right">{{ state.roleGroup }}</div>
                     </li>
                     <li class="list-group-item">
                        <svg-icon icon-class="date" />{{$t('message.personalData.6')}}
                        <div class="pull-right">{{ state.user.createTime }}</div>
                     </li>
                  </ul>
               </div>
            </el-card>
         </el-col>
         <el-col :span="17" :xs="24">
            <el-card style="height: 422px;">
               <template v-slot:header>
                 <div class="clearfix">
                   <span>{{ $t('message.personalData.7') }}</span>
                 </div>
               </template>
               <el-tabs v-model="activeTab">
                  <!-- <el-tab-pane label="基本资料" name="userinfo">
                     <userInfo :user="state.user" />
                  </el-tab-pane> -->
                  <el-tab-pane :label="$t('message.personalData.8')" name="resetPwd">
                     <resetPwd />
                  </el-tab-pane>
               </el-tabs>
            </el-card>
         </el-col>
      </el-row>
   </div>
</template>

<script setup name="Profile">
import userAvatar from "./userAvatar";
import userInfo from "./userInfo";
import resetPwd from "./resetPwd";
import { getUserProfile } from "@/api/system/user";
import { getAvatarInitials, stringToRGB } from '@/utils/ruoyi'

const activeTab = ref("resetPwd");
const state = reactive({
  user: {},
  roleGroup: {},
  postGroup: {}
});

function getUser() {
  getUserProfile().then(response => {
    state.user = response.data;
    state.roleGroup = response.roleGroup;
    state.postGroup = response.postGroup;
  });
};

function getRGB(name) {
   if(name) {
      return stringToRGB(name)
   } else {
      return 'none'
   }
};

function getAvatar(email) {
   if(email) {
      return getAvatarInitials(email)
   }
   else {
      return ''
   }
};

getUser();
</script>
