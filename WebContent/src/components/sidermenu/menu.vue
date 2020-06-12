<template>
    <el-menu router='true'>
        <!-- 开启菜单中的router=true之后，点击菜单会把index作为路由跳转路径 -->
        <el-menu-item index="/admin/user">
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
</template>

<style>
.el-menu-item {
    font-size: 24px;
    line-height: 70px;
    height: 70px;
    color: #303133;
    padding: 0 20px;
    cursor: pointer;
    -webkit-transition: border-color .3s, background-color .3s, color .3s;
    transition: border-color .3s, background-color .3s, color .3s;
    box-sizing: border-box
  }

  .el-menu-item [class^=el-icon-] {
    margin: 0 12px;
    padding-bottom: 4px;
    width: 24px;
    text-align: center;
    font-size: 22px;
    vertical-align: middle
  }

.el-menu-item:focus,
.el-menu-item:hover {
  outline: 0;
  background-color: #cee3fc;
  color: #fff;
}




</style>

<script>
import router from '@/router';
export default {
  name:'sidermenu'

};
</script>