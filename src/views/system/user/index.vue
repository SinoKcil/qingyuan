<script setup lang="ts">
defineOptions({
    name:"SystemUser"
});

import { ref,reactive, onMounted } from 'vue'
import { 
  Delete,
  Edit,
  InfoFilled,
} from '@element-plus/icons-vue'
import { ElMessage,ElMessageBox, TableInstance }  from 'element-plus';
import { done } from 'nprogress';
import axios from '@/utils/axios'



const managers = { PrimaryManager: "仓库管理员", GeneManeger: "总负责人" };
const workers = { Practice: "实习检修工", PrimaryWorker: "初级检修工",SeniorWorker:"高级检修工" };
const genders = { male: "男", female: "女" };
const regions={ Shanghai:"上海",Suzhou:"苏州" };//此处理应从后台获取所有城市列表
const searchName=ref("")
const dialogVisible=ref(false)
const dialogTitle=ref("")
const tableRef=ref<TableInstance>()

interface User{
  user_id:string,
  user_name:string,
  user_gender:string,
  user_tel:string,
  user_title:string,
  user_region:string,
  user_warehouse:string,
}
var dataGot:User[]=[
  {
    user_id:"1",
    user_name:"张大强",
    user_gender:genders["male"],
    user_tel:"10086",
    user_title:workers["PrimaryWorker"],
    user_region:regions["Shanghai"],
    user_warehouse:"1001",
  },
  {
    user_id:"2",
    user_name:"李大壮",
    user_gender:genders["female"],
    user_tel:"10001",
    user_title:workers["Practice"],
    user_region:regions["Suzhou"],//由于prop无法接收数组元素，使用这种复杂的方式
    user_warehouse:"1002",
  },
]
const form=reactive({
  user_id:"",
  user_name:"",
  user_gender:"",
  user_tel:"",
  user_title:"",
  user_region:"",
  user_warehouse:"",
})
const dataList=ref(dataGot)
const filterRegion=(value:string,row:User) =>{
  return row.user_region===regions[value]
}
const filterTitle=(value:string,row:User) =>{
  return row.user_title===workers[value]
}
const clearFilter=()=>{
  tableRef.value!.clearFilter()
}

// onMounted(()=>{
//   let result="没成功"
//   axios.get(`admin/users?page=1&size=100`)
//   .then((response)=>{
//     console.log("success load")
//     dataGot=response.data.users
//     dataList.value=dataGot
//     for(let i=0;i<dataList.value.length;i++){
//       let user=dataList.value[i]
//       user.user_gender=genders[user.user_gender]
//       user.user_title=workers[user.user_title]
//       user.user_region=regions[user.user_region]
//     }
//   }).catch((err)=>{
//     console.log("load error:"+err)
//     ElMessage.error('数据加载失败！原因是:'+err)
//   })
//   //alert(result)
// })

function getKeyByValue(object: any, value: any) {  
    return Object.entries(object).find(([key, val]) => val === value)?.[0];  
} 
function operateUser(index,code){
  if(code==="modify"){
    dialogTitle.value="修改维修人员信息"
    form.user_id=dataList.value[index].user_id
    form.user_name=dataList.value[index].user_name
    form.user_gender=dataList.value[index].user_gender
    form.user_tel=dataList.value[index].user_tel
    form.user_title=dataList.value[index].user_title
    form.user_region=dataList.value[index].user_region
    form.user_warehouse=dataList.value[index].user_warehouse
    console.log(form.user_region)
  }
  else if(code==="add") {
    dialogTitle.value="添加维修人员"
  }
  dialogVisible.value=true
}
const formRef=ref(null)
function submitForm(code):any {
  let result="没成功"
  form.user_region=getKeyByValue(regions,form.user_region)//通过值找键值，返回数组的第零个，因为确定该值只可能有一个键
  form.user_gender=getKeyByValue(genders,form.user_gender)
  form.user_title=getKeyByValue(workers,form.user_title)
  formRef.value.validate(async()=>{
    //alert("here")
    try{
      if(code==='modify') result=await axios.get(`users/${form.user_id}`,{"region":form.user_region})
      console.log(result)
      ElMessage({
        message: '提交成功！',
        type: 'success',
      })
      return 1
    }
    catch(err){
      console.log("error:"+err)
      ElMessage.error('提交失败！原因是:'+err)
      return 0
    }
  })
  //alert(result)
}

const deleteFlag=ref(false)//为了解决popconfirm自运行的问题
function deleteUser(index){
  if(deleteFlag.value)
  {
    dataList.value.splice(index,1)
    deleteFlag.value=false
  }
}

function accurateSearch(){
  let name=searchName.value
  if(name==="") return
  dataList.value=[]
  for(let i=0;i<dataGot.length;i++){
    let username=dataGot[i].user_name
    if(username===name) dataList.value.push(dataGot[i])
  }
}
function vagueSearch(){
  let name=searchName.value
  if(name==="") return
  let reg=new RegExp(name)//正则表达式查询
  dataList.value=[]
  for(let i=0;i<dataGot.length;i++){
    let username=dataGot[i].user_name
    if(reg.test(username)){
      dataList.value.push(dataGot[i])
    }
  }
}
function resetSearch(){
  searchName.value=""//通过绑定v-model重置输入框
  dataList.value=dataGot
}
function handleCloseDialog(){
  ElMessageBox.confirm(
    "你确定要退出吗？这将不会留下任何修改。",
    "提示",{
      confirmButtonText:"确定，我要退出",
      cancelButtonText:"不，我还要修改",
    })
  .then(()=>{
    dialogVisible.value=false
    done()
  })
  .catch((e)=>{
  })

}
function handleSubmit(code){
  ElMessageBox.confirm(
    "你确定要提交吗？",
    "提交",{
      confirmButtonText:"是的，我确认无误",
      cancelButtonText:"不，我还要修改"
    }
  )
  .then(()=>{
    let result:number=submitForm(code)
    if(result==1) dialogVisible.value=false
  })
  .catch(()=>{})
}
</script>
<template>
<div>
   <el-card >
    <div style="margin-bottom: 5px">
      <span><h2>维修人员管理</h2></span>
      <span>
        查询：
        <el-input
        style="width:50%"
        placeholder="请输入待查询人员名字"
        v-model="searchName"
        @change="accurateSearch()"
        clearable
        ></el-input>
      </span>
    <br>
    <el-button type="warning" style="margin-top:5px;margin-left:53px" @click="accurateSearch()">精确查询</el-button>
    <el-button type="success" style="margin-top:5px" @click="vagueSearch()">模糊查询</el-button>
    <el-button type="info" style="margin-top:5px" @click="resetSearch()">重置查询</el-button>
    
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
        label="编号" prop="user_id" width="70"
      ></el-table-column>
      <el-table-column
        label="姓名" prop="user_name" minWidth="80"
      ></el-table-column>
      <el-table-column
        label="性别" prop="user_gender" minWidth="60"
      ></el-table-column>
      <el-table-column
        label="联系电话" prop="user_tel" minWidth="90"
      ></el-table-column>
      <el-table-column
        label="职称" prop="user_title" minWidth="100"
        :filters="[
          {text:'实习检修工',value:'Practice'},
          {text:'初级检修工',value:'PrimaryWorker'},
          {text:'高级检修工',value:'SeniorWorker'}
        ]"
        :filter-method="filterTitle"
      ></el-table-column>
      <el-table-column
        label="负责区域" prop="user_region" minWidth="100"
        :filters="[
          {text:'上海',value:'Shanghai'},
          {text:'苏州',value:'Suzhou'}
        ]"
        :filter-method="filterRegion"
      ></el-table-column>
      <el-table-column
        label="负责仓库" prop="user_warehouse" minWidth="90"
      ></el-table-column>
      <el-table-column label="操作" width="90" fixed="right">
        <template #default="scope">
          <el-button @click="operateUser(scope.$index,'modify')" type="primary" size="small" :icon="Edit" circle></el-button>
          <el-popconfirm 
            title="您确定要删除这个人吗？"
            width="220"
            confirm-button-text="是"
            cancel-button-text="否"
            :icon="InfoFilled"
            icon-color="#c83f0e"
            @confirm="deleteUser(scope.$index)"
          >
            <template #reference>
              <el-button type="danger" size="small" @click="deleteFlag=true" :icon="Delete" circle></el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
   </el-card>

   <el-dialog
   v-model="dialogVisible"
   :title="dialogTitle"
   width="50%"
   :before-close="handleCloseDialog"
   >
    <el-form :model="form" ref="formRef" label-width="auto">
      <el-form-item label="编号:">
        <el-input v-model="form.user_id" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="姓名:">
        <el-input v-model="form.user_name" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="性别:">
        <el-input v-model="form.user_gender" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="联系电话:">
        <el-input v-model="form.user_tel" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="职称:">
        <el-input v-model="form.user_title" style="width:80%" disabled/>
      </el-form-item>
      <el-form-item label="负责区域:">
        <el-select
          v-model="form.user_region" 
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
