故障总列表
<script setup lang="ts">
defineOptions({
    name:"SystemUser"
});

import { ref,reactive, onMounted,inject } from 'vue'
import { 
  Delete,
  Edit,
  InfoFilled,
} from '@element-plus/icons-vue'
import { ElMessage,ElMessageBox, TableInstance }  from 'element-plus';
import { done } from 'nprogress';
import axios from '@/utils/axios'
import { getOffsetTop } from 'element-plus/es/utils';



const managers = { PrimaryManager: "仓库管理员", GeneManeger: "总负责人" };
const workers = { Practice: "实习检修工", PrimaryWorker: "初级检修工",SeniorWorker:"高级检修工" };
const genders = { male:"男", female:"女" };
const regions={ Shanghai:"上海",Suzhou:"苏州",Default:"哈尔滨",Zhangjiahe:"张家口" };//此处理应从后台获取所有城市列表
const abnormalities=["左右高度不一","轨道接缝落差","轨道不平顺"]
const status=[ "故障未上报","故障已上报","故障已维修", "误故障报"]

const searchName=ref("")
const dialogVisible=ref(false)
const dialogTitle=ref("")
const tableRef=ref<TableInstance>()


interface abnormality{
    time:string,
    id:number,
    region:string,
    layer:number,
    location:string,
    label:number,
    status:number,
    worker:string,
    phone:string
}

interface GetUAbnormality{
  Leader:{
    id: number,
    username: string,
    phone:number,
    password: string,
    role: string,
    avatar:string,
    region: string
  },
  Abnormality: {
    id: number,
    x: number,
    y: number,
    label: number,
    time: string,
    status: number,
    layers: number,
    regionName: string
  }
}
var dataGot:GetAbnormality[]

const dataList=ref([])
const filterTime=(value:string,row:abnormality) =>{
  return row.user_region===regions[value]
}
const filterLabel=(value:string,row:abnormality) =>{
  console.log(abnormalities[value])
  return row.label===abnormalities[value]
}
const filterRegion=(value:string,row:abnormality) =>{
  return row.region===regions[value]
}
const filterLayer=(value:string,row:abnormality) =>{
  return row.layer===value
}
const filterStatus=(value:string,row:abnormality) =>{
  return row.status===status[value]
}
const clearFilter=()=>{
  tableRef.value!.clearFilter()
}

onMounted(()=>{
  let result="没成功"
  dataList.value=[]
  for(let id=1;id<450;id++)
  {
    if(id%50===0) id=id+50
    axios.get(`id?id=${id}`)
  .then((response)=>{
    //console.log("success load")
    dataGot=response.data
    pushAbnormality()
  }).catch((err)=>{
    console.log("load error:"+err)
    ElMessage.error('数据加载失败！原因是:'+err)
  })
  }
  dataGot=dataList.value
  //alert(result)
})

function getKeyByValue(object: any, value: any) {  
    return Object.entries(object).find(([key, val]) => val === value)?.[0];  
} 
function operateUser(index,code){
  if(code==="modify"){
    dialogTitle.value="故障列表"
    form.id=dataList.value[index].user_id
    form.username=dataList.value[index].user_name
    form.avatar=dataList.value[index].user_gender
    form.phone=dataList.value[index].user_tel
    form.user_title=dataList.value[index].user_title
    form.region=dataList.value[index].user_region
    form.user_warehouse=dataList.value[index].user_warehouse
    console.log(form.region)
  }
  else if(code==="add") {
    dialogTitle.value="添加维修人员"
  }
  dialogVisible.value=true
}

function pushAbnormality(){
    let gotLeader=dataGot.Leader
    let gotAB=dataGot.Abnormality
    if(gotAB.status===0) return
    let ab:abnormality={
        time:gotAB.time.slice(0,10),
        id:gotAB.id,
        region:regions[gotAB.regionName],
        layer:gotAB.layers,
        location:`(${gotAB.x},${gotAB.y})`,
        label:abnormalities[gotAB.label],
        status:status[gotAB.status],
        worker:gotLeader.username,
        phone:gotLeader.phone
    }
    dataList.value.push(ab)
}


function accurateSearch(){
  let name=searchName.value
  if(name==="") return
  console.log(dataGot)
  dataGot=dataList.value
  dataList.value=[]
  for(let i=0;i<dataGot.length;i++){
    let username=dataGot[i].worker
    if(username===name) dataList.value.push(dataGot[i])
  }
}
function vagueSearch(){
  let name=searchName.value
  if(name==="") return
  let reg=new RegExp(name)//正则表达式查询

  dataGot=dataList.value
  dataList.value=[]

  for(let i=0;i<dataGot.length;i++){
    let username=dataGot[i].worker
    if(reg.test(username)){
      dataList.value.push(dataGot[i])
    }
  }
}
function resetSearch(){
  searchName.value=""//通过绑定v-model重置输入框
  dataList.value=dataGot
}

const currentPage = ref(1)
const pageSize = 10
const handelCurrentChange = page => {
    currentPage.value = page
    // currentPage.value = 1
    const index = pageSize * (page - 1)
    const nums = pageSize * page
}

</script>
<template>
<div>
   <el-card >
    <div style="margin-bottom: 5px">
      <span><h2>故障工单管理</h2></span>
      <!-- <span>
        查询检修员：
        <el-input
        style="width:50%"
        placeholder="请输入待查询检修员名字"
        v-model="searchName"
        @change="accurateSearch()"
        clearable
        ></el-input>
      </span>
      <br>
    <el-button type="warning" style="margin-top:5px;margin-left:101px" @click="accurateSearch()">精确查询</el-button>
    <el-button type="success" style="margin-top:5px" @click="vagueSearch()">模糊查询</el-button>
    <el-button type="info" style="margin-top:5px" @click="resetSearch()">重置查询</el-button> -->
    </div>
      <el-button style="margin-top:5px;margin-bottom: 5px" @click="clearFilter"> 重置过滤器</el-button>
      <el-table 
      ref="tableRef"
      :data="dataList" 
      row-key="user_id"
      max-height="600px"
      table-layout="auto"
      stripe
      border>
      <el-table-column
        label="时间" prop="time" width="150"
      ></el-table-column>
      <el-table-column
        label="编号" prop="id" minWidth="80"
      ></el-table-column>
      <el-table-column
        label="区域" prop="region" minWidth="80"
        :filters="[
          {text:'上海',value:'Shanghai'},
          {text:'苏州',value:'Suzhou'},
          {text:'哈尔滨',value:'Default'},
          {text:'张家口',value:'Zhangjiahe'},
        ]"
        :filter-method="filterRegion"
      ></el-table-column>
      <el-table-column
        label="层数" prop="layer" minWidth="90"
        :filters="[
          {text:'1',value:1},
          {text:'2',value:2},
          {text:'3',value:3},
        ]"
        :filter-method="filterLayer"
      ></el-table-column>
      <el-table-column
        label="坐标" prop="location" minWidth="90"
      ></el-table-column>
      <el-table-column
        label="故障原因" prop="label" minWidth="100"
        :filters="[
          {text:'左右高度不一',value:0},
          {text:'轨道接缝落差',value:1},
          {text:'轨道不平顺',value:2}
        ]"
        :filter-method="filterLabel"
      ></el-table-column>
      <el-table-column
        label="检修状态" prop="status" minWidth="100"
        :filters="[
          {text:'故障已上报',value:1},
          {text:'故障已维修',value:2},
          {text:'误报故障',value:3},
        ]"
        :filter-method="filterStatus"
      ></el-table-column>
      <el-table-column
        label="检修员" prop="worker" minWidth="90"
      ></el-table-column>
      <el-table-column
        label="联系方式" prop="phone" minWidth="90"
      ></el-table-column>
    </el-table>
    <!-- <el-pagination 
      layout="prev, pager, next" :total="50"
      :hide-on-single-page="value"
      v-model:current-page="currentPage"
      @current-change="handelCurrentChange"
       /> -->
   </el-card>

   <el-dialog
   v-model="dialogVisible"
   :title="dialogTitle"
   width="50%"
   :before-close="handleCloseDialog"
   >
    <el-form :model="form" ref="formRef" label-width="auto">
      <el-form-item label="编号:">
        <el-input v-model="form.id" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="姓名:">
        <el-input v-model="form.username" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="性别:">
        <el-input v-model="form.avatar" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="联系电话:">
        <el-input v-model="form.phone" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="职称:">
        <el-input v-model="form.user_title" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="负责区域:">
        <el-select
          v-model="form.region" 
          style="width:80%"
          >
          <!-- value属性是传给v-model的，一定要有 -->
          <el-option 
            v-for="item in Object.keys(regions)"           
            :label="regions[item]"
            :value="item" 
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="负责区域:">
        <el-input v-model="form.user_warehouse" style="width:80%" disabled/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="">
        <el-button @click="handleSubmit('modify')">提交</el-button>
        <el-button @click="handleCloseDialog">返回</el-button>
      </div>
    </template>
  </el-dialog>
</div>
</template>
<style scoped>

</style>
