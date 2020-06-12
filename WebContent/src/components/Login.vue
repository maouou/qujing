<template>
  <div class="main" style="border:2px solid #C0C0C0;border-radius:10px;height: 600px; width: 1200px; margin: auto">
    <div style="width: 500px; margin: auto">
    <h3>取经系统管理员登录</h3>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
  <el-form-item label="用户名：" prop="username">
    <el-input  v-model="form.username"></el-input>
  </el-form-item>
  <el-form-item label="密码：" prop="password">
    <el-input v-model="form.password" show-password></el-input>
  </el-form-item>
  <el-form-item>

    <el-button type="primary" @click="onSubmit('form')">  登录  </el-button>

  </el-form-item>
</el-form>
    </div>
  </div>
</template>

<style>
.main{
	background:-webkit-linear-gradient(
	top,#D0D0D0,#E8E8E8,lightblue
	);
}
</style>

<script>
import axios from 'axios'
import QS from 'qs'
export default {
  name: 'Login',
  data() {
      return {
        form: {
          username: '',
          password: '',
          
        },
        session:'',
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            {  max: 30, message: '长度小于30个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { max: 30, message: '长度小于30个字符', trigger: 'blur' }
          ]
        }
        
        
      }
    },
    methods: {
    //处理登录
      onSubmit(form) {
      var sendJson =this.form;
      var self=this;
      this.$refs[form].validate((valid) => {
          if (valid) {
            axios.get('/adminlogin/login.do',{params: {username:sendJson.username,password:sendJson.password}})
      .then(response=>{
      console.log(response.status);
      if (response.status=='200') {
      localStorage.setItem('islogin',1);
       self.$router.push('/admin');
    	
		} else { 
    		self.$message({
          showClose: true,
          message: '用户名或密码错误'
        });
		}
      })
      .catch(error=>{
      
      });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      
		console.log('submit!');
      }
    }
}
</script>