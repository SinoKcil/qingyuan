<!-- 故障详情页面 -->
<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { ArrowLeft, ArrowRight, Delete, Edit, Share } from "@element-plus/icons-vue";
import { getAbnormalDetails } from "@/api/back";
import { ref, reactive, computed, watch } from "vue";
import form from "../src/fix/form.vue";

const getRoute = useRoute();
const router = useRouter();
const handleBack = () => {
  router.go(-1);
};
// 详细信息
const regions={ Shanghai:"上海",Suzhou:"苏州",Default:"哈尔滨",Zhangjiahe:"张家口" };

const regionName = ref<string>("");
const x = ref<number>(0);
const y = ref<number>(0);
const label = ref<string>("");
const status = ref<string>("");
const time = ref<Date>(new Date());
const layer = ref<number>(0);
const suggestion = ref<string>("");
const labelDesc = ref<string>("");
let id: number; // 在数据库中这个故障的id 这是主键 可以减少参数的传递
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
    id = data.id;
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
function reportFault(id: number) {
  if (id >= 0) {
    router.push({
      name: "SubmitTicket",
      query: { AbnormalityId: id } //  故障的id（注意不是region的id）
    });
  } else {
    router.push({
      name: "404",
      query: {}
    });
  }
}
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
            :icon="ArrowLeft"
            @click="handleBack"
            >返回</el-button
          >
          <div>
            <!-- 检查是否有查询参数 -->
            <div v-if="Object.keys(getRoute.query).length > 0">
              <!-- 如果有查询参数，显示Label信息 -->
              <el-text
                class="mx-3"
                type="warning"
                style="font-family: Consolas; font-size: 20px"
              >
                <b>故障Label: {{ getRoute.query.label }}</b>
              </el-text>
            </div>

            <!-- 如果没有查询参数，显示提示信息 -->
            <el-text
              v-else
              class="mx-3"
              type="error"
              style="font-family: Consolas; font-size: 20px"
            >
              <b>请返回至工厂地图，然后点击对应区域来查看故障详情</b>
            </el-text>
          </div>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 20px"
          >
            <b>时间: {{ time }}</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 20px"
          >
            <b>位置: {{ regions[getRoute.query.region] }}仓库</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 20px"
          >
            <b>坐标:({{ x }},{{ y }})</b>
          </el-text>
          <div>
            <el-text
              class="mx-3"
              type="error"
              style="font-family: Consolas; font-size: 20px"
            >
              <b>{{ status }}</b>
            </el-text>

            <!-- 当 status 为 1 时，显示上报故障按钮 -->
            <!-- v-if="status === '轨道出现异常，请及时上报维护申请。'" -->
            <el-button
             
              type="warning"
              style="margin-left: 58px; margin-top: -10px"
              @click="reportFault(id)"
              size="large"
            >
              上报故障
            </el-button>
          </div>
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
            style="font-family: Consolas; font-size: 20px"
          >
            <b>故障原因</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 20px"
          >
            {{ labelDesc }}
          </el-text>
          <el-text
            class="mx-3"
            type="primary"
            style="font-family: Consolas; font-size: 20px"
          >
            <b>解决建议</b>
          </el-text>
          <el-text
            class="mx-3"
            type="default"
            style="font-family: Consolas; font-size: 20px"
          >
            {{ suggestion }}
          </el-text>
        </el-space>
      </el-card>
    </div>
  </div>
</template>
