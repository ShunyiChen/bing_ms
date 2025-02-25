<template>
    <div id="securityBanner" class="security-banner" :class="{ hide: isHidden }">
      <div class="banner-content">
        <span class="english-text">
          To ensure the security and compliance of EY information, please adhere to our
          <a href="https://discovercontent.ey.net/pop23013a6l9nnl4#detailPath=ZYFCSFS674FF-1228837741-5112" target="_blank">Global Data Protection and Confidentiality Policy</a>
           and <a href="https://sites.ey.com/sites/Be_Aware/SitePages/Information-Security-Policies.aspx" target="_blank">information security policies</a>.
            <a href="https://eyt.service-now.com/ey?id=kb_article_view&sys_id=6e813a438702b1d0e14aed7e8bbb3587" target="_blank">Report</a> 
            any suspicious activity immediately. Click <a href="https://sites.ey.com/sites/Be_Aware" target="_blank">here</a> to learn more about information security.
        </span>
        <span class="chinese-text">
          为确保安永信息的安全与合规，请每位员工遵守安永<a href="https://discovercontent.ey.net/pop23013a6l9nnl4#detailPath=ZYFCSFS674FF-1228837741-5112" target="_blank">全球数据保护政策</a>
           及<a href="https://eychinamanaged.sharepoint.cn/sites/PSG" target="_blank">信息安全制度</a>，
           并立即<a href="https://eyt.service-now.com/ey?id=kb_article_view&sys_id=6e813a438702b1d0e14aed7e8bbb3587" target="_blank">上报</a>任何可疑活动。
            <a href="https://eychinamanaged.sharepoint.cn/sites/PRC_InfoSec" target="_blank">点此</a>了解更多信息安全知识。
        </span>
      </div>
      <button class="close-btn" @click="closeBanner"></button>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const isHidden = ref(false)
  const emit = defineEmits(['close'])
  
  const closeBanner = () => {
    isHidden.value = true
    emit('close')
  }


  </script>
  
  <style lang="scss" scoped>
  .security-banner {
    height: 50.8px;
    width: 100%;
    background-color: rgb(156, 33, 33);
    float: left;
    position: fixed;
    // z-index: 3001;
    top: 0px;
    font-size: small;
    color: #FFF;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 10px;
    transition: transform 0.2s ease, opacity 0.2s ease;
  }
  
  .banner-content {
    display: flex;
    flex-direction: column;
    gap: 4px;
  }
  
  .banner-content a {
    color: #FFF;
    text-decoration: underline;
  }

  .security-banner.hide {
    transform: translateY(-101%);
  }
  
  .close-btn {
    background-color: transparent;
    border: none;
    color: white;
    font-size: 20px;
    cursor: pointer;
    margin-left: 10px;
    line-height: 1;
    
    &:before {
      content: '✖';
      display: inline-block;
      font-size: 20px;
      color: white;
    }
    
    &:hover {
      opacity: 0.7;
    }
  }
  </style>