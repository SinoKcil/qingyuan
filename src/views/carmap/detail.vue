<!-- 故障详情页面 -->
<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { ArrowLeft, ArrowRight, Delete, Edit, Share } from "element-plus";
import { getAbnormalDetails } from "@/api/back";
import { ref, reactive, computed, watch } from "vue";

const getRoute = useRoute();
const router = useRouter();
const handleBack = () => {
  router.go(-1);
};
// 详细信息
const regionName = ref<string>("");
const x = ref<number>(0);
const y = ref<number>(0);
const label = ref<string>("");
const status = ref<string>("");
const time = ref<Date>(new Date());
const layer = ref<number>(0);
const suggestion = ref<string>("");
const labelDesc = ref<string>("");
getAbnormalDetails(
  getRoute.query.region,
  getRoute.query.layer,
  getRoute.query.Row,
  getRoute.query.Col,
  getRoute.query.label
).then(data => {
  if (data) {
    regionName.value = data.regionName;
    x.value = data.x;
    y.value = data.y;
    labelDesc.value = data.labelDescription;
    status.value = data.fixStatus;
    time.value = data.time;
    layer.value = data.layers;
    suggestion.value = data.fixSuggestion;
  }
});

const getTextClass = (label: string) => {
  switch (label) {
    case "0":
      return "success";
    case "1":
      return "warning";
    case "2":
      return "danger";
    case "3":
      return "primary";
    default:
      return "";
  }
};
// function formatTimeString(timeString) {
//   const regex = /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})/;
//   const match = timeString.match(regex);

//   if (match) {
//     const year = match[1];
//     const month = match[2];
//     const day = match[3];
//     const hour = match[4];
//     const minute = match[5];
//     const formattedDateTime = `${year}年${month}月${day}日${hour}时${minute}分`;
//     return formattedDateTime;
//   } else {
//     return "Invalid time format";
//   }
// }
// const formattedDateTime = formatTimeString(time.value);
</script>

<template>
  <div style="display: flex; flex-direction: column">
    <div style="margin-bottom: 18px">
      <el-card
        style="display: flex; flex-direction: column; align-items: flex-start"
      >
        <!-- <h1>这是一个参数测试</h1>
      <h1>接收到了参数Row:{{ getRoute.query.Row }}</h1>
      <h1>Col:{{ getRoute.query.Col }}</h1>
      <h1>Layer:{{ getRoute.query.layer }}</h1>
      <h1>Label:{{ getRoute.query.label }}</h1>
      <h1>Region:{{ getRoute.query.region }}</h1>
      <h1>Region Name: {{ regionName }}</h1>
      <h1>X: {{ x }}</h1>
      <h1>Y: {{ y }}</h1>
      <h1>Label: {{ label }}</h1>
      <h1>Status: {{ status }}</h1>
      <h1>Time: {{ time }}</h1>
      <h1>Layer: {{ layer }}</h1>
      <h1>Suggestion: {{ suggestion }}</h1> -->
        <el-space
          direction="vertical"
          :size="18"
          style="display: flex; flex-direction: column; align-items: flex-start"
        >
          <!-- 怎么在最左侧对齐呢 -->
          <el-button
            type="primary"
            icon="el-icon-arrow-left"
            @click="handleBack"
            >返回</el-button
          >
          <el-text
            class="mx-3"
            type="warning"
            style="font-family: Consolas; font-size: 32px"
          >
            <b>Label: {{ getRoute.query.label }}</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 32px"
          >
            <b>时间: {{ time }}</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 32px"
          >
            <b>位置: {{ getRoute.query.region }}仓库({{ x }},{{ y }})</b>
          </el-text>
          <el-text
            class="mx-3"
            type="error"
            style="font-family: Consolas; font-size: 32px"
          >
            <b>{{ status }}</b>
          </el-text>
        </el-space>
      </el-card>
    </div>
    <div style="margin-bottom: 18px">
      <el-card
        style="
          display: flex;
          flex-direction: column;
          align-items: flex-start;
          margin-bottom: 18px;
        "
      >
        <el-space
          direction="vertical"
          :size="18"
          style="display: flex; flex-direction: column; align-items: flex-start"
        >
          <el-text
            class="mx-3"
            type="primary"
            style="font-family: Consolas; font-size: 32px"
          >
            <b>故障原因</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 26px"
          >
            {{ labelDesc }}
          </el-text>
          <el-text
            class="mx-3"
            type="primary"
            style="font-family: Consolas; font-size: 32px"
          >
            <b>解决建议</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 26px"
          >
            {{ suggestion }}
          </el-text>
        </el-space>
      </el-card>
    </div>
  </div>
</template>
