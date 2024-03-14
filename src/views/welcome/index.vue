<script setup lang="ts">
    import { useRoute } from 'vue-router';
    import { ref }from 'vue';
 
    const row=ref('')
    const col=ref('') 
    const route=useRoute()
    if(JSON.stringify(route.query)!=''){
        row.value=JSON.stringify(route.query.Row)
        col.value=JSON.stringify(route.query.Col)
    }
</script>

<template>
<div>
    <router-view :key="$route.fullPath"></router-view>
    <h1>这是Row:{{ row }}、Col:{{ col }}的主页</h1>
</div>
</template>