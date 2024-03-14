<!-- 故障详情页面 -->
<script setup lang="ts">
    import { useRoute,useRouter } from 'vue-router'

    const getRoute=useRoute()
</script>

<template>
<div>
    <h1>这是一个参数测试</h1>
    <h1>接收到了参数Row:{{ getRoute.query.Row }},Col:{{ getRoute.query.Col }}</h1>
</div>
</template>