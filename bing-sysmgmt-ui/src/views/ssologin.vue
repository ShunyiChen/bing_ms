<template>
    <div class="login">
        <div style="display: flex;flex-direction: column;justify-content: center;align-items: center;">
            <el-row style="color: white;font-size: large;">
                {{ msg }}
            </el-row>
            <el-icon v-if="success" class="is-loading" style="color: white;font-size: x-large;margin-top:20px;">
                <Loading />
            </el-icon>
            <el-link v-else type="primary" style="font-size: large;padding-top: 20px;"
                href="https://localhost:82/sysmgmt">{{ $t('message.ssologin.3') }}</el-link>
        </div>
    </div>
</template>

<script setup>
import i18n from '@/i18n'
import { extractUrlParams } from "@/utils/url";
import { ref } from 'vue'
import Cookies from "js-cookie";
import useUserStore from '@/store/modules/user'
const msg = ref(i18n.global.t('message.ssologin.1'));
const success = ref(true);
const userStore = useUserStore();
const router = useRouter();
const redirect = ref(undefined);
const route = useRoute();

function handleSSOLogin() {
    const d = Cookies.get('d')
    if (d) {
      // 调用action的aad登录方法
      userStore.ssologin(d).then(() => {
        const customQuery = extractUrlParams(redirect.value);
        router.push({ path: redirect.value || "/", query: customQuery });
      }).catch(err => {
        msg.value = i18n.global.t('message.ssologin.2')
        success.value = false;
      });
    //  setTimeout(() => {
    //     msg.value = 'Failed to retrieve AAD token due to timeout.'
    //     success.value = false;
    //  }, 3000);
    } else {
        msg.value = i18n.global.t('message.ssologin.2')
        success.value = false;
    }
}

watch(route, (newRoute) => {
    redirect.value = newRoute.query && newRoute.query.redirect;
}, { immediate: true });

// 在组件挂载时调用异步方法
onMounted(() => {
    handleSSOLogin();
});
</script>

<style lang='scss' scoped>
.login {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/images/ey_bg.jpg");
    background-position: 50% 100%;
    background-size: cover;
}

</style>
  