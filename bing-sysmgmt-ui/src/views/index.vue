<template>
  <div class="app-container">
    <div>
      <el-row type="flex" :gutter="40" style="align-items: center;justify-content: center;margin: 40px 0px;">
        <el-col :span="4">
          <span style="font-size: x-large;font-weight: bold;">Release Notes</span>
        </el-col>
      </el-row>
      <el-row type="flex" :gutter="40" style="align-items: center;justify-content: center;">
        <el-col :span="8">
          <div style="display: flex;flex-direction: row;align-items: center;justify-content: center;">
            <el-icon color="var(--el-color-primary)" :size="25">
              <SuccessFilled />
            </el-icon>
            <div style="display: flex;flex-direction: column;margin-left: 5px;">
              <span style="font-size: smaller;">Legal Database is up to date</span>
              <span style="font-size: medium;">Last modified date {{ latestVersion }} (CBS Official Build)</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-tabs :tab-position="tabPosition" style="height: 500px;margin-top: 30px;overflow-y: auto;" @tab-change="handleTabChanged">
      <el-tab-pane v-for="(item, index) of releaseDateList" :key="item" :label="item">
        <ReleaseNotes :releaseDate="item" :load="index == 0" :ref="(el) => (itemsRef[index] = el)"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup name="Index">
import ReleaseNotes from '@/components/ReleaseNotes'
import { getReleaseDateList } from "@/api/system/releasenotes";
const { proxy } = getCurrentInstance();
const latestVersion = ref('1.2.0');
const tabPosition = ref('left');
const releaseDateList = ref([]);
const itemsRef = ref([]);

// 取所有发布日期列表根据系统名称
function findReleaseDateList() {
  getReleaseDateList({systemName: 'LegalDB'}).then(response => {
    releaseDateList.value = response;
    latestVersion.value = releaseDateList.value[0];
  });
}

const handleTabChanged = (idx) => {
  itemsRef.value[idx].findReleaseNotesByDate();
}

// 在组件挂载时调用异步方法
onMounted(() => {
  findReleaseDateList();
});

</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}</style>

