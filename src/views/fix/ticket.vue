<!-- 维修工单 -->

<script setup lang="ts">
import { ref, reactive, onMounted,unref } from "vue";
import { formUpload } from "@/api/mock";
import axios from '@/utils/axios'
import {useRouter } from "vue-router";
import UploadIcon from "@iconify-icons/ri/upload-2-line";
import { getAuthByToken, fetchAbnormalityForForm } from "@/api/back";
// 从cookie中来拿token要更安全
import Cookies from "js-cookie";
import { useFormStore } from "@/store/modules/form"
// const getRoute = useRoute();
const uploadRef = ref();
// const abnormalityId = getRoute.query.AbnormalityId;
const formStore = useFormStore()
const warehouse = ref(formStore.warehouseStore); // 初始化仓库对象的默认值
const router = useRouter()

const managers = { PrimaryManager: "仓库管理员", GeneManeger: "总负责人" };
const workers = { Practice: "实习检修工", PrimaryWorker: "初级检修工",SeniorWorker:"高级检修工" };
const genders = { male: "男", female: "女" };
const labelMapper = {
  0: "该处轨道正常",
  1: "该处单侧轨道异常。该数据已经自动上报至穿梭车调度系统。出现该异常的主要因素可能是穿梭车长时间搬运重量不均匀的货物造成轨道偏载而发生变形。",
  2: "该轨道接缝处出现较大落差，小车发生比较明显的颠簸。该数据已经自动上报至穿梭车调度系统。出现该异常的主要因素可能是轨道在安装初期矫正不够完全，或者长时间使用导致其中一段轨道受压较大而发生下沉。",
  3: "该处出现了轨道不平顺的现象,小车长时间发生颠簸。该数据已经自动上报至穿梭车调度系统。出现该异常的主要因素能是仓库在长时间使用中积累了大量灰尘、杂物，使得轨道表面不平顺。"
};

const user_name = ref<string>("lyt");
const user_id = ref<string>("2");
const user_gender = ref<string>("女");
const user_tel = ref<string>("98765432100");
const user_title = ref<string>("PrimaryWorker");

onMounted(()=>{
  fetchUserData()
})
//利用cookie获得用户信息
function fetchUserData() {
  const token = Cookies.get("authorized-token");
  if (token) {
    getAuthByToken(token)
      .then(response => {
        if (response.success) {
          console.log(response);
          user_name.value = response.user.username;
          user_id.value = "0100" + response.user.id;
          user_gender.value = response.user.avatar; // 假设直接将头像信息用作性别
          user_tel.value = response.user.phone;
          user_title.value = response.user.role;
        }
      })
      .catch(error => {
        console.log("error fetching user", error);
      });
  } else {
    console.log("token is not found");
  }
}
// fetchUserData();
// function fetchWareHouseAbnormalities(abnormalityId) {
//   return fetchAbnormalityForForm(abnormalityId);
// }
// if (abnormalityId) {
//   fetchWareHouseAbnormalities(abnormalityId).then(data => {
//     if (data) {
//       warehouse.value.regionName = data["Abnormality"].regionName;
//       warehouse.value.regionId = data["Abnormality"].id;
//       warehouse.value.layer = data["Abnormality"].layers;
//       warehouse.value.leaderName = data["Leader"].username;
//       warehouse.value.leaderPhone = data["Leader"].phone;
//       warehouse.value.PosX = data["Abnormality"].x;
//       warehouse.value.PosY = data["Abnormality"].y;
//       warehouse.value.label = data["Abnormality"].label;
//     }
//   });
// }

const formRef = ref(null);
const validateForm = reactive({
  fileList: [],
  date: "",
  checkResult: '1', 
  //这个应该是一个字符串，缺省值设置为选择‘是’（我们相信模型检测结果正确率很高）如果是数字，那么这个将无法被elementui进行检测，需要先点一下否再点是，直接点是 无法点选。
  actualResult: [],
  userid: user_id
});
const submitForm =()=>{
  let result="没成功"
  formRef.value.validate(async()=>{
    // alert("here")
    try{
      result=await axios.post("form/upload",validateForm)
    }
    catch(err){
      console.log("error:"+err)
      alert("运行出错，错误原因为："+err)
    }
  })
  //alert(result)
}
const routerBack=()=>{
    router.push({
        name:"Welcome",
    })
}


// const submitForm = () => {
//   if (!formRef.value) return;
//   const hhh = formRef.value.validate;
//   formRef.value.validate(valid => {
//     if (valid) {
//       const formData = new FormData();
//       validateForm.fileList.forEach(file => {
//         formData.append("files", file.raw);
//       });
//       formData.append("date", validateForm.date);
//       formData.append("AbnormalId", abnormalityId);
//       formData.append("userid", user_id.value);

//       在这里调用API
//       formUpload(formData)
//         .then(({ success }) => {
//           if (success) {
//             message("提交成功", { type: "success" });
//           } else {
//             message("提交失败");
//           }
//         })
//         .catch(error => {
//           message(`提交异常 ${error}`, { type: "error" });
//         });
//     }
//   });
// };

// const resetForm = () => {
//   if (!formRef.value) return;
//   formRef.value.resetFields();
// };
</script>

<template>
  <div>
    <!-- 维修人员信息 -->
    <el-card shadow="never" class="container">
      <h4>维修人员信息</h4>
      <hr />
      <hr />
      <div>姓名：{{ user_name }}</div>
      <div>工号：{{ user_id }}</div>
      <div>性别：{{ user_gender }}</div>
      <div>联系电话：{{ user_tel }}</div>
      <div>职称：{{ user_title }}</div>
    </el-card>
    <!-- 故障详情 -->
    <el-card shadow="never" class="container">
      <h4>故障详情</h4>
      <hr />
      <hr />
      <!-- 这个地方不需要填写，只要抓取数据放在这里就可以了 -->
      <div>仓库地址：{{ warehouse.regionName }}</div>
      <div>仓库编号：{{ warehouse.regionId }}</div>
      <div>仓库层数: {{ warehouse.layer }}</div>
      <div>仓库负责人：{{ warehouse.leaderName }}</div>
      <div>仓库负责人联系方式：{{ warehouse.leaderPhone }}</div>
      <div>故障坐标：{{ warehouse.position }}</div>
      <div>        
        <div class="div-item">故障原因：</div>
        <div class="div-item"> {{ labelMapper[warehouse.label] }}</div>
      </div>
    </el-card>
    <!-- 检修情况上传 -->
    <el-card>
    <el-form ref="formRef" :model="validateForm">
        <el-form-item
        prop="date"
        :rules="[{ required: false, message: '日期不能为空' }]"
        >
        <!-- 先改成false  -->
        检查时间：
        <el-date-picker
          v-model="validateForm.date"
          type="datetime"
          class="!w-[200px]"
          placeholder="请选择日期时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
        <el-button type="primary"  @click="routerBack()" class="mt-5">
          提交
        </el-button>

    </el-form>
    </el-card>
  </div>
</template>
<style>
.container {
  margin-top: 10px;
  margin-bottom: 10px;
}
.div-item{
  display:inline-block
}
</style>
./axios