<!-- 提交工单 -->

<script setup lang="ts">
import { ref,reactive } from "vue";
import { formUpload } from "@/api/mock";
import { message } from "@/utils/message";
import { formDataHander } from "@pureadmin/utils";
import UploadIcon from "@iconify-icons/ri/upload-2-line";

const formRef = ref();
const uploadRef = ref();
const validateForm = reactive({
  fileList: [],
  date: "",
  checkResult:1,
  actualResult:[]
});

const submitForm = formEl => {
  if (!formEl) return;
  formEl.validate(valid => {
    if (valid) {
      // 多个 file 在一个接口同时上传
      const formData = formDataHander({
        files: validateForm.fileList.map(file => ({ raw: file.raw })), // file 文件
        date: validateForm.date // 别的字段
      });
      formUpload(formData)
        .then(({ success }) => {
          if (success) {
            message("提交成功", { type: "success" });
          } else {
            message("提交失败");
          }
        })
        .catch(error => {
          message(`提交异常 ${error}`, { type: "error" });
        });
    } else {
      return false;
    }
  });
};

const resetForm = formEl => {
  if (!formEl) return;
  formEl.resetFields();
};

const managers={PrimaryManager:'仓库管理员',GeneManeger:'总负责人'}
    const workers={Practice:'实习检修工',PrimaryWorker:'初级检修工'}
    const genders={male:'男',female:'女'}

    const user_name="张大强"
    const user_id="010001"
    const user_gender='male'
    const user_tel="13012345678"
    const user_title="PrimaryWorker"
</script>


<template>
<div>
    <!-- 维修人员信息 -->
    <el-card shadow="never" class="container">
        <h4>维修人员信息</h4><hr><hr>
        <div>姓名：{{ user_name }}</div>
        <div>工号：{{ user_id }}</div>
        <div>性别：{{ genders[user_gender] }}</div>
        <div>联系电话：{{ user_tel }}</div>
        <div>职称：{{ workers[user_title] }}</div>
    </el-card>
    <!-- 故障详情 -->
    <el-card shadow="never" class="container">
        <h4>故障详情</h4><hr><hr>
        <div>仓库编号：</div>
        <div>仓库地址：</div>
        <div>仓库负责人：</div>
        <div>仓库负责人联系方式：</div>
        <div>故障坐标：</div>
        <div>故障评估：</div>
    </el-card>
    <!-- 检修情况上传 -->
    <el-form ref="formRef" v-model="validateForm">
        <h4>检查情况</h4><hr><hr>
        <el-form-item
        :rules="[{ required: true, message: '故障情况不能为空' }]"
        >
            故障是否属实：
            <el-radio v-model="validateForm.checkResult" label="1" name="checkResult">是</el-radio>
            <el-radio v-model="validateForm.checkResult" label="0" name="checkResult">否</el-radio>
        </el-form-item>
        <el-form-item v-if="validateForm.checkResult==0">
            故障原因：
            <el-checkbox-group v-model="validateForm.actualResult">
              <el-checkbox value="1">原因1</el-checkbox>
              <el-checkbox value="2">原因2</el-checkbox>
              <el-checkbox value="3">原因3</el-checkbox>
              <el-checkbox value="0">其他</el-checkbox>
            </el-checkbox-group>
            <el-input 
            v-if="validateForm.actualResult.includes(0)" 
            placeholder="请输入原因"
            :rules="[{ required: true, message: '必须输入其他原因！' }]"
            ></el-input>
        </el-form-item>
        <el-form-item
      prop="fileList"
      :rules="[{ required: true, message: '附件不能为空' }]"
    >
    检查情况：
      <el-upload
        ref="uploadRef"
        v-model:file-list="validateForm.fileList"
        drag
        multiple
        action="#"
        class="!w-[200px]"
        :auto-upload="false"
      >
        <div class="el-upload__text">
          <IconifyIconOffline
            :icon="UploadIcon"
            width="26"
            class="m-auto mb-2"
          />
          可点击或拖拽上传
        </div>
      </el-upload>
    </el-form-item>
    <el-form-item
      prop="date"
      :rules="[{ required: true, message: '日期不能为空' }]"
    >
    检查时间：
      <el-date-picker
        v-model="validateForm.date"
        type="datetime"
        class="!w-[200px]"
        placeholder="请选择日期时间"
        value-format="YYYY-MM-DD HH:mm:ss"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" text bg @click="submitForm(formRef)">
        提交
      </el-button>
      <el-button text bg @click="resetForm(formRef)">重置</el-button>
    </el-form-item>
    </el-form>
</div>
</template>
<style>
.container{
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>