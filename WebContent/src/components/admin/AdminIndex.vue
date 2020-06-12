<template>
  <!-- 将大容器设置nav类单独设置样式 -->
  <el-container class="nav">
    <!-- 页面顶部 -->
    <el-header class="logo">
<el-image
      style="width: 55px; height: 55px;margin:5px;float:left"
      :src="url"
      :fit="contain"></el-image>
<div style="float:left">管理员首页
</div>
    </el-header>
<el-container style="height: 500px; width: 800px; margin: auto">
      
    
      <el-main>
        <!-- 下面内容是中间空白区域的内容，你可以自定义 -->
        <el-menu router='true' >
        <!-- 开启菜单中的router=true之后，点击菜单会把index作为路由跳转路径 -->
        <el-menu-item  index="/admin/user">
          <i class="el-icon-setting"></i>用户管理
        </el-menu-item>
        <el-menu-item index="/admin/report">
          <i class="el-icon-setting"></i>举报管理
        </el-menu-item>
        <el-menu-item index="/admin/feedback">
          <i class="el-icon-setting"></i>反馈管理
        </el-menu-item>
        <el-menu-item index="/admin/require">
          <i class="el-icon-setting"></i>请求审核
        </el-menu-item>
      </el-menu>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
@import 'admin.css';

</style>

<script>
export default {
  name: 'AdminIndex',
  data() {
      return {
        url:require('@/assets/logo.png')
      }
    }
}
</script>