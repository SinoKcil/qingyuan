<!-- 主页，展示地图，最近工单等 -->
<script setup lang="ts">
import { RefSymbol } from "@vue/reactivity";
import { ref, reactive, computed, watch,onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from '@/utils/axios'
import { ElMessage } from "element-plus"
import GroupLine from "@iconify-icons/ri/group-line";
import Question from "@iconify-icons/ri/question-answer-line";
import CheckLine from "@iconify-icons/ri/chat-check-line";
import Smile from "@iconify-icons/ri/star-smile-line";
import echarts from "@/plugins/echarts";
import {  getRegions ,getAbnormalities, getRecentNews } from "@/api/back";

enum routerCate {
  detail,
  ticket
} //路由页面的id


const show = ref(true);
const activeRow = ref(-1);
const activeCol = ref(-1);
// 嘻嘻 我是懒狗 连变量名都不想换（)
const fruits=ref([])
// const fruits = ref(
//   getRecentNews() || ["这里空空如也诶，快去看看有没有异常状态"]
// );
const abnormalities=["左右高度不一","轨道接缝落差","轨道不平顺"]
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
const myTable = ref()
const tableValue=ref()
const layers=ref([])

const regions = ref(); //这个需要响应式 因为请求是一个异步函数 更改接口之后 这是个由region 对象构成的list
const showTip = ref(false); //展示悬浮窗
const cursorX = ref(0); //监听鼠标坐标
const cursorY = ref(0);
const selected = ref(""); //绑定了选择框选择的结果
const thisWrench = ref(-1);
const router = useRouter(); //路由，跳转页面
const mobile = ref(screen.width <= 993);
const totalLayers = ref(1); //层数
const selectedLayer = ref(1); //选择的层数
const valueRef=ref([])
const chart = ref()

getRegions().then(data => {
  if (data) {
    regions.value = data["regions"]; //区域列表
  }
});

const area = reactive({
  region: "Shanghai",
  layer: 1
});
onMounted(() => {
  getAbnormality()
  axios.get(`recent`)
  .then((response)=>{
    fruits.value=response.data
    //console.log(fruits.value)
  })
  .catch((err)=>{
    console.log("message load error:"+err)
    ElMessage.error("最近动态加载失败！原因是："+err)
  })
  initEcharts()
})
// var buttonString:String='border-radius:0%,height:50px,width:50px,background-color:'+choosedColor.value+',border-width:1px'

const chartData = [
  {
    icon: GroupLine,
    bgColor: "#effaff",
    color: "#41b6ff",
    duration: 2200,
    name: "故障数量",
    value: 36000,
    percent: "+88%",
    data: [0,1,0,0,1,2,0]
  },
];

const greyButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "border-width": "1px",
  "background-color": "grey"
});
const redButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "background-color": "#e76e79",//#e76e79
  "border-width": "1px"
});
const yellowButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "background-color": "#eeeda0",
  "border-width": "1px"
});
const blueButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "background-color": "blue",
  "border-width": "1px"
});
const lightButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "background-color": "#b6d1ec",
  "border-width": "1px"
});
const greenButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "background-color": "#a0eeab",
  "border-width": "1px"
});
const darkBlueButton = reactive({
  "border-radius": "0%",
  height: "80px",
  width: "80px",
  "color":"white",
  "background-color": "#103693",
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
  "margin-left":"5px",
  "margin-top":"5px",
  width: "97%"
});
document.addEventListener("mousemove", updateCursorPosition);
// destroyed() {
//     document.removeEventListener('mousemove', updateCursorPosition)
// }
function getAbnormality(){
  axios.get(`region?region=${area.region}&layer=${area.layer}`)
  .then((response)=>{
      totalLayers.value=response.data.totalLayers
      //console.log("total layers="+totalLayers.value)
      for(let i=1;i<=totalLayers.value;i++){
        layers.value.push(i)
      }
      //console.log(layers.value)
      tableValue.value=response.data.tableStatus
      //console.log("first status="+tableValue.value[0])
      myTable.value=response.data.tableId
      //console.log("first line="+myTable.value[0])
    }).catch((err)=>{
      console.log("load error:"+err)
      ElMessage.error('地图加载失败！原因是:'+err)
  })
}
function updateCursorPosition(event) {
  const rect = event.target.getBoundingClientRect();
  cursorX.value = event.clientX ;
  cursorY.value = event.clientY - 10;
  //可以加一个根据上下左右位置修改提示框方向
}
function overMouse(indexRow, indexCol) {
  //等于-1的时候执行前面那个，是通常情况
  if (tableValue.value[indexRow][indexCol] != -1) {
    activeRow.value = indexRow;
    activeCol.value = indexCol;
    chooseColor(indexRow, indexCol);
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
  //console.log("indexarow="+indexRow+" indexcol="+indexCol)
  if (tableValue.value[indexRow][indexCol] === -1) return greyButton;
  if (
    tableValue.value[indexRow][indexCol] === 0 &&
    activeCol.value === indexCol &&
    activeRow.value === indexRow
  )//正常，鼠标触碰
    return greenButton;
  if (tableValue.value[indexRow][indexCol] === 0) return lightButton;//正常，鼠标触碰
  if (
    (tableValue.value[indexRow][indexCol] === 1
    ||tableValue.value[indexRow][indexCol] === 2
    ||tableValue.value[indexRow][indexCol] === 3
    ) &&
    activeCol.value === indexCol &&
    activeRow.value === indexRow
  ) {
    if(mobile.value===false) 
    {
      getValues()
      showTip.value = true;
    }
    return darkBlueButton;
  }
  // 出现左右轨道高度不一
  if (tableValue.value[indexRow][indexCol] === 1) return yellowButton;
  // 出现轨道接缝落差
  if (tableValue.value[indexRow][indexCol] === 2) return redButton;
  // 轨道不平顺
  if (tableValue.value[indexRow][indexCol] === 3) return blueButton;
}
function routerToTable(category, ticketid) {
  if (category == "detail")
    router.push({
      name: "SubmitTicket",
      params: {
        regionName:area.region,
        regionId:"test 1001",
        layer:area.layer,
        leaderName:"韩梅梅",
        leaderPhone:"10016",
        position:myTable.value[activeRow.value][activeCol.value],
        label: tableValue[activeRow.value][activeCol.value],
      }
    });
  if (category == "ticket")
    router.push({ name: "404", query: { ticketId: ticketid } });
}

//获取悬浮窗的value
function getValues(){
  if(activeRow.value===-1) valueRef.value = [0,0]
  else
  {
    let value = []
    let value0 = myTable.value[activeRow.value][activeCol.value]
    let temp = tableValue.value[activeRow.value][activeCol.value]
    let value1 = abnormalities[temp]
    value.push(value0)
    value.push(value1)
    valueRef.value = value
  }
}
function initEcharts(){

  var myChart = echarts.init(chart.value)
  const option={
    xAxis:{
      data:["周一","周二","周三","周四","周五","周六","周日"]
    },
    yAxis:{
      type:'value',
      interval:1,
      max:5,
      min:0
    },
    series:[
      {
        data:chartData[0].data,
        type:'bar',
      }
    ],
    grid:[
      {
        bottom:20,
        top:10
      }
    ]
  }
  myChart.setOption(option)
}

watch(selected, newValue => {
  area.region = newValue.region_name; // 更新区域
  console.log(area.region);
  // 根据新的区域重新请求异常数据
  getAbnormalities(area.region, area.layer).then(data => {
    if (data) {
      myTable.value = data["tableId"];
      tableValue.value = data["tableStatus"];
      totalLayers.value = data["totalLayers"];
    }
  });
});
watch(selectedLayer, (newValue, oldValue) => {
  // 发送请求
  getAbnormalities(area.region, newValue).then(data => {
    if (data) {
      myTable.value = data["tableId"];
      tableValue.value = data["tableStatus"];
    }
  });
});
</script>
<template>
  <div class="container">
    <!-- 地图卡片 -->
    <div style="flex-direction: column; grid-area: a">
      <el-card>
        <class class="card" shadow="never">
          <div style="display: flex; justify-content: space-between">
            <div style="flex-direction: column">
              <div class="text-md font-medium mb-2" >分析概览</div>
              <el-form action="" label-width="auto" inline>
                <el-form-item label="区域">
                  <el-select
                  name="weekchoose"
                  v-model="selected"
                  style="width:150px"
                >
                  <el-option
                    v-for="item in regions"
                    :label="item.region_name"
                    :value="item"
                  >
                  </el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="层数">
                  <el-select
                  name="layerchoose"
                  v-model="selectedLayer"
                  style="width:150px"
                >
                  <el-option
                    v-for="item in totalLayers"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>       
                </el-form-item>
              </el-form>
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
      <el-card shadow="never">
        <div class="flex justify-between">
          <span class="text-md font-medium">最新动态</span>
        </div>
        <el-scrollbar height="150px" class="mt-3">
          <div
            v-for="(item, index) in fruits"
            :key="index"
            center
            placement="top"
            class="mb-3"
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
              {{ ` ${item} ` }}
            </span>
          </div>
        </el-scrollbar>
      </el-card>
    </div>
    <!-- 格子信息概览 -->
    <div
      class="tooltip"
      :style="{ left: cursorX + 'px', top: cursorY + 'px' }"
      v-show="showTip && !mobile">
      轨道位置：{{ valueRef[0] }}
      <br/>
      故障原因：{{ valueRef[1] }}
    </div>
    <div style="grid-area:c" class="issues">
      <el-card class="line-card" shadow="never">
          <div class="flex justify-between">
            <span class="text-md font-medium">
              {{ chartData[0].name }}
            </span>
            <div
              class="w-8 h-8 flex justify-center items-center rounded-md"
              
            >
              <IconifyIconOffline
                :icon="chartData[0].icon"
                :color="chartData[0].color"
                width="18"
              />
            </div>
          </div>
          <div class="flex justify-between items-start mt-3">
            <div id="chart1" ref="chart" 
            style="width:100%;height:216px"></div>
          </div>
        </el-card>
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
    grid-template-areas: "a a b b" "a a c c" "a a c c";
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
  height: 80px;
  border: 2px solid #ccc;
  border-radius: 3px;
  background-color: white;
  text-align: left;
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
