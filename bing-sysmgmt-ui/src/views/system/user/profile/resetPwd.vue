<template>
   <el-form ref="pwdRef" :model="user" :rules="rules" label-width="180px">
      <el-form-item :label="$t('message.resetPwd.1')" prop="oldPassword">
         <el-input v-model="user.oldPassword" :placeholder="$t('message.resetPwd.4')" type="password" show-password />
      </el-form-item>
      <el-form-item :label="$t('message.resetPwd.2')" prop="newPassword">
         <el-input v-model="user.newPassword" :placeholder="$t('message.resetPwd.5')" type="password" show-password />
      </el-form-item>
      <el-form-item :label="$t('message.resetPwd.3')" prop="confirmPassword">
         <el-input v-model="user.confirmPassword" :placeholder="$t('message.resetPwd.6')" type="password" show-password/>
      </el-form-item>
      <el-form-item>
      <el-button type="primary" @click="submit">{{ $t('message.resetPwd.7') }}</el-button>
      <el-button type="danger" @click="close">{{ $t('message.resetPwd.8') }}</el-button>
      </el-form-item>
   </el-form>
</template>

<script setup>
import { updateUserPwd } from "@/api/system/user";

const { proxy } = getCurrentInstance();

const user = reactive({
  oldPassword: undefined,
  newPassword: undefined,
  confirmPassword: undefined
});

const equalToPassword = (rule, value, callback) => {
  if (user.newPassword !== value) {
    callback(new Error("两次输入的密码不一致"));
  } else {
    callback();
  }
};

const rules = ref({
  oldPassword: [{ required: true, message: "旧密码不能为空", trigger: "blur" }],
  newPassword: [{ required: true, message: "新密码不能为空", trigger: "blur" }, { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }, { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }],
  confirmPassword: [{ required: true, message: "确认密码不能为空", trigger: "blur" }, { required: true, validator: equalToPassword, trigger: "blur" }]
});

/** 提交按钮 */
function submit() {
  proxy.$refs.pwdRef.validate(valid => {
    if (valid) {
      updateUserPwd(user.oldPassword, user.newPassword).then(response => {
        proxy.$modal.msgSuccess("修改成功");
      });
    }
  });
};

/** 关闭按钮 */
function close() {
  proxy.$tab.closePage();
};
</script>
