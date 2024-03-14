<!-- 提交工单 -->

<script setup lang="ts">

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
    <el-card shadow="never" class="container">
        <h4>检查情况</h4><hr><hr>
        <div>
            故障是否属实：

        </div>
        
        <div>

        </div>
    </el-card>
</div>
</template>
<style>
.container{
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>