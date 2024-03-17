<!-- 主页，展示地图，最近工单等 -->
<script setup lang="ts">
import { RefSymbol } from "@vue/reactivity";
import { ref, reactive, computed, watch } from "vue";
import { useRouter } from "vue-router";
import { http } from "@/utils/http";
import { getRegions, getAbnormalities } from "@/api/back";

enum routerCate {
  detail,
  ticket
} //路由页面的id
const show = ref(true);
const activeRow = ref(-1);
const activeCol = ref(-1);
//下为扳手icon的图标
const whiteWrench =
  "m19.275 19.8l.7-.7L12 11.125q.475-.575.738-1.3T13 8.3q0-1.875-1.313-3.175T8.5 3.825h-.25L11.325 6.9l-4.25 4.225L4 8.05v.275Q4 10.2 5.312 11.5T8.5 12.8q.8 0 1.513-.263T11.3 11.8l7.975 8ZM5.375 3.25q0-.725.775-1.088T8.5 1.8q2.725 0 4.612 1.888T15 8.3q0 .65-.125 1.288T14.5 10.8l6.875 6.875q.575.575.588 1.413T21.4 20.5l-.675.7q-.575.6-1.413.613t-1.437-.588l-6.9-6.9q-.575.25-1.2.363t-1.25.112q-2.725 0-4.612-1.887T2.025 8.3q0-1.6.35-2.363t1.075-.762q.25 0 .463.088t.387.262L7.075 8.3L8.5 6.9L5.725 4.125q-.175-.175-.263-.4t-.087-.475ZM9.2 9Z";
const blackWrench =
  "M5.375 3.25q0-.65.875-1.05t2.25-.4q2.725 0 4.612 1.888T15 8.3q0 .65-.125 1.288T14.5 10.8l6.875 6.875q.575.575.588 1.413T21.4 20.5l-.675.7q-.575.6-1.413.613t-1.437-.588l-6.9-6.9q-.575.25-1.2.363t-1.25.112q-2.725 0-4.612-1.887T2.025 8.3q0-1.375.388-2.25t1.037-.875q.25 0 .463.088t.387.262L7.075 8.3L8.5 6.9L5.725 4.125q-.175-.175-.263-.4t-.087-.475Z";
const myList = ref([
  { message: "first" },
  { message: "second" },
  { message: "third" }
]);
let myTable = getAbnormalities["tableId"];
let tableValue: number[][] = getAbnormalities["tableStatus"];
const fruits = [
  "apple",
  "pear",
  "watermelon",
  "grape",
  "banana",
  "pineapple",
  "icon",
  "test",
  "my",
  "you",
  "fuck",
  "omg",
  "one",
  "two",
  "three",
  "four",
  "five",
  "six",
  "seven"
];
const regions = ref(); //这个需要响应式 因为请求是一个异步函数
const showTip = ref(true); //展示悬浮窗
const cursorX = ref(0); //监听鼠标坐标
const cursorY = ref(0);
const selected = ref(""); //绑定了选择框选择的结果
const thisWrench = ref(-1);
const router = useRouter(); //路由，跳转页面
const mobile = ref(screen.width <= 993);
const totalLayers = ref(1); //层数
const selectedLayer = ref(1); //选择的层数

const area = {
  region: "Shanghai",
  layer: 1
};

getRegions().then(data => {
  if (data) {
    regions.value = data["regions"]; //区域列表
  }
});
getAbnormalities(area.region, area.layer).then(data => {
  if (data) {
    myTable = data["tableId"];
    tableValue = data["tableStatus"];
    totalLayers.value = data["totalLayers"];
  }
});

// var buttonString:String='border-radius:0%,height:50px,width:50px,background-color:'+choosedColor.value+',border-width:1px'

const greyButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "border-width": "1px",
  "background-color": "grey"
});
const redButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "background-color": "red",
  "border-width": "1px"
});
const yellowButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "background-color": "yellow",
  "border-width": "1px"
});
const blueButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "background-color": "blue",
  "border-width": "1px"
});
const pinkButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "background-color": "pink",
  "border-width": "1px"
});
const greenButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "background-color": "green",
  "border-width": "1px"
});
const darkredButton = reactive({
  "border-radius": "0%",
  height: "50px",
  width: "50px",
  "background-color": "#8b0000",
  "border-width": "1px"
});
const mapCard = reactive({
  margin: "10px",
  "margin-top": "50px",
  "border-width": "1px",
  height: "350px",
  width: "70%"
});
const shadowItem = reactive({
  "box-shadow": "2px 2px 2px 3px grey",
  width: "97%"
});
document.addEventListener("mousemove", updateCursorPosition);
// destroyed() {
//     document.removeEventListener('mousemove', updateCursorPosition)
// }
function updateCursorPosition(event) {
  const rect = event.target.getBoundingClientRect();
  cursorX.value = event.clientX - 150;
  cursorY.value = event.clientY - 10;
  //可以加一个根据上下左右位置修改提示框方向
}
function overMouse(indexRow, indexCol) {
  //等于-1的时候执行前面那个，是通常情况
  if (tableValue[indexRow][indexCol] != -1) {
    activeRow.value = indexRow;
    activeCol.value = indexCol;
    chooseColor(indexRow, indexCol);
    //alert("0为"+ showTip[0]+' 1为'+showTip[1])
  }
  //alert("active="+active.value)
}
function leaveMouse() {
  activeRow.value = -1;
  activeCol.value = -1;
  showTip.value = false;
  //alert("active="+active.value)
}
function chooseColor(indexRow, indexCol) {
  if (tableValue[indexRow][indexCol] == -1) return greyButton;
  if (
    tableValue[indexRow][indexCol] == 0 &&
    activeCol.value == indexCol &&
    activeRow.value == indexRow
  )
    return greenButton;
  if (tableValue[indexRow][indexCol] == 0) return pinkButton;
  if (
    tableValue[indexRow][indexCol] == 1 &&
    activeCol.value == indexCol &&
    activeRow.value == indexRow
  ) {
    showTip.value = true;
    return darkredButton;
  }
  // 出现左右轨道高度不一
  if (tableValue[indexRow][indexCol] == 1) return yellowButton;
  // 出现轨道接缝落差
  if (tableValue[indexRow][indexCol] == 2) return redButton;
  // 轨道不平顺
  if (tableValue[indexRow][indexCol] == 3) return blueButton;
}
function routerToTable(category, ticketid) {
  //页面显示有bug，无法直接展示，故暂时采用query（get）模式传参
  if (category == "detail")
    router.push({
      name: "FaultDetail",
      query: {
        Row: activeRow.value,
        Col: activeCol.value,
        layer: selectedLayer.value,
        label: tableValue[activeRow.value][activeCol.value],
        region: area.region
      }
    });
  if (category == "ticket")
    router.push({ name: "404", query: { ticketId: ticketid } });
}

watch(selected, newValue => {
  area.region = newValue; // 更新区域
  console.log(area.region);
  // 根据新的区域重新请求异常数据
  getAbnormalities(area.region, area.layer).then(data => {
    if (data) {
      myTable = data["tableId"];
      tableValue = data["tableStatus"];
      totalLayers.value = data["totalLayers"];
    }
  });
});
watch(selectedLayer, (newValue, oldValue) => {
  // 发送请求
  getAbnormalities(area.region, newValue).then(data => {
    if (data) {
      myTable = data["tableId"];
      tableValue = data["tableStatus"];
    }
  });
});
</script>
<template>
  <div class="container">
    <!-- 触碰变色的按钮 -->
    <!-- <button v-for="(item,index) in myList" :key="index" :style="active==index?overButton:baseButton" @mouseover="overMouse(index)" @mouseleave="leaveMouse()">
        {{ item.message }}
    </button>
    <hr>
    <hr> -->

    <!-- 双重循环渲染按钮 -->
    <!-- <div v-for="(col,index1) in myTable" :key="index1">
        <button v-for="(item,index2) in myTable[index1]" :key="index2" :style="baseButton">
            {{ item }}
        </button>
    </div> -->
    <!-- 循环触碰按钮 -->
    <!-- <div  style="grid-area:d">
            <div v-for="(col,indexRow) in myTable" :key="indexRow">
            <button v-for="(item,indexCol) in myTable[indexRow]" :key="indexCol" :style="activeRow==indexRow&&activeCol==indexCol?overButton:baseButton" @mouseover="overMouse(indexRow,indexCol)" @mouseleave="leaveMouse()" >
                {{ item }}
            </button>
            </div>
        </div> -->
    <!-- 测试卡片 -->
    <!-- 地图卡片 -->
    <div style="flex-direction: column; grid-area: a">
      <el-card>
        <class class="card" shadow="never">
          <div style="display: flex; justify-content: space-between">
            <div style="flex-direction: column">
              <form action="">
                分析概览&nbsp;&nbsp;&nbsp;
                <select
                  name="weekchoose"
                  style="border-width: 1px; border-radius: 5%"
                  v-model="selected"
                >
                  <option
                    v-for="(item, index) in regions"
                    style="border-width: 1px"
                  >
                    {{ item }}
                  </option>
                </select>
              </form>
            </div>
            <div style="flex-direction: column">
              <form action="">
                层数&nbsp;&nbsp;&nbsp;
                <select
                  name="weekchoose"
                  style="border-width: 1px; border-radius: 5%"
                  v-model="selectedLayer"
                >
                  <option
                    v-for="index in totalLayers"
                    :key="index"
                    :value="index"
                  >
                    {{ index }}
                  </option>
                </select>
              </form>
            </div>
          </div>
          <br />
          <div v-for="(col, indexRow) in myTable" :key="indexRow">
            <button
              v-for="(item, indexCol) in myTable[indexRow]"
              :key="indexCol"
              :style="chooseColor(indexRow, indexCol)"
              @mouseover="overMouse(indexRow, indexCol)"
              @mouseleave="leaveMouse()"
              @click="routerToTable('detail', -1)"
            >
              {{ item }}
            </button>
          </div>
        </class>
      </el-card>
    </div>
    <!-- 右上待解决事件栏 -->
    <div style="grid-area: b" class="issues">
      <el-card>
        <div class="flex justify-between">
          <span class="text-md font-medium">最新动态</span>
        </div>
        <el-scrollbar max-height="200" class="mt-3">
          <div
            v-for="(item, index) in fruits"
            :key="index"
            center
            placement="top"
            :style="thisWrench == index ? shadowItem : ''"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="14"
              viewBox="0 0 24 24"
              class="inline-block"
            >
              <path
                fill="currentColor"
                :d="thisWrench == index ? blackWrench : whiteWrench"
              />
            </svg>
            <span
              class="text-text_color_regular text-sm inline-block"
              @mouseover="thisWrench = index"
              @mouseleave="thisWrench = -1"
              @click="routerToTable('ticket', index + item)"
            >
              {{ `新增 ${index} 条问题，${item + index} 条已解决` }}
            </span>
          </div>
        </el-scrollbar>
      </el-card>
    </div>
    <div
      class="tooltip"
      :style="{ left: cursorX + 'px', top: cursorY + 'px' }"
      v-show="showTip && mobile"
    >
      123
    </div>
  </div>
</template>

<style scoped>
@media screen and (min-width: 1024px) {
  .container {
    display: grid;
    row-gap: 20px;
    column-gap: 20px;
    margin-left: 20px;
    margin-right: 20px;
    grid-template-areas: "a a a b" "a a a c" "d d d c";
  }
  .issues {
    width: 90%;
  }
}
@media screen and (max-width: 1024px) {
  .container {
    display: grid;
    row-gap: 20px;
    width: 90%;
    column-gap: 20px;
    margin-left: 20px;
    margin-right: 20px;
    grid-template-areas: "a" "b" "c" "d";
  }
}
.card {
  margin-bottom: 10px;
}
.tooltip {
  width: 150px;
  height: 100px;
  border: 2px solid #ccc;
  border-radius: 3px;
  background-color: white;
  text-align: center;
  line-height: 98px;
  position: absolute;
  z-index: 99;
  transform: translateY(-50%);
  opacity: 0.95;
  box-shadow: 2px 2px 2px 3px grey;
}

.inline-block {
  display: inline-block;
}

.transition-box {
  margin-bottom: 10px;
  width: 200px;
  height: 100px;
  border-radius: 4px;
  background-color: #409eff;
  text-align: center;
  color: #fff;
  padding: 40px 20px;
  box-sizing: border-box;
  margin-right: 20px;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}
</style>
