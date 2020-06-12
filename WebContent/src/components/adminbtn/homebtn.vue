<template>
<div>
<el-button style="float: right; padding:20px 20px"  @click="gohome" circle><i class="el-icon-s-home el-icon--top"></i>Home</el-button>
</div>
</template>


<script>
import router from '@/router';
export default {
  name:'homebtn',
  
  methods: {
    gohome() {
      router.push('/admin')
    }
  }
};
</script>