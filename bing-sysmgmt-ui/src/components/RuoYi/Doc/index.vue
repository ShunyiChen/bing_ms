<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script setup>
const url = ref('https://cbsapp-dev.ey.com.cn/sysmgmt/FilingSystem简易使用手册.pdf');

function goto() {
  window.open(url.value)
}
</script>