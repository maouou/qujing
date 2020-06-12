<template>
  <!-- 将大容器设置nav类单独设置样式 -->
  <el-container class="nav">
    <el-header class="logo">
<el-image
      style="width: 55px; height: 55px;margin:5px;float:left"
      :src="url"
      :fit="contain"></el-image>
<div style="float:left">举报详细
</div>

<homebtn></homebtn>

    </el-header>

    <el-container >
    <el-aside width="200px"
              style="background-color: rgb(238, 241, 246)">
      <sidermenu></sidermenu>
    </el-aside>
    <el-main>
    <div style="height:380px; width:900px">
    <div style="height:380px; width:200px; float:left">
    	<div style="height:45px">
    	<h5>任务名：</h5>
        </div>
        <div style="height:100px">
    	<h5>任务描述：</h5>
        </div>
    	<div style="height:100px">
    	<h5>举报理由：</h5>
        </div>
        <div style="height:45px">
    	<h5>收货人：</h5>
        </div>
        <div style="height:45px">
    	<h5>送货人：</h5>
        </div>
        <div style="height:45px">
    	<h5>举报人：</h5>
        </div>
    </div>
    <div style="height:380px; width:450px;float:left">
    	<div style="height:45px">
    	<b><p>{{detail.taskName}}</p></b>
        </div>
        <div style="height:100px">
    	<b><p>{{detail.taskContent}}</p></b>
        </div>
    	<div style="height:100px">
    	<b><p>{{detail.reportContent}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.receiverName}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.senderName}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.reporterName}}</p></b>
        </div>
    </div>
    <div style="height:380px; width:200px;float:left">
    <el-col>
	<el-button type="primary" style="width:120px" @click="handleDelete()">删除任务</el-button>
	<br /><br />
	<el-button type="primary" style="width:120px" @click="handleUndelete()"> 未违规 </el-button>
	<br /><br />
	<el-button type="primary" style="width:120px" @click="handleTrans()">用户管理</el-button>
	<br /><br />
	<el-button style="width:120px" @click="handleBack()">返回</el-button>
	</el-col>
    
    </div>
    </div>
    </el-main>   
  

    </el-container>
  </el-container>
</template>
<style>
@import 'admin.css';

.div-inline{ 
	display:inline;
} 
p{ 
	font-family:"Hiragino Sans GB";
	font-size:17px;
	font-weight:600;
}
</style>

<script>
import axios from 'axios'  
export default {
  name: 'ReportDetail',
  data() {
      return {
      url:require('@/assets/logo.png'),
      detail:'',
      ID:''
    }
    
    },

    //请求举报的任务详情页
    created: function () {
    this.ID=this.$route.query.id;
    var self=this;
        axios.get('/admin/reportmanage/showreport.do',{params: {id:self.ID}})
           .then(response => {
              this.detail=response.data;
            })
        
    },
   methods:{
   //删除被举报的任务
      handleDelete() {
      var self=this;
      axios.get('/admin/reportmanage/deletetask.do',{params: {id:self.detail.suitID}})
           .then(response => {
              
            });
      this.$alert('任务已删除', '消息', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
        this.$router.push("/admin/report");
      },
      //该任务未违规
      handleUndelete() {
      var self=this;
      axios.get('/admin/reportmanage/legaltask.do',{params: {id:self.detail.suitID}})
           .then(response => {
              
            });
      	this.$alert('举报已移出', '消息', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
        this.$router.push("/admin/report");
      },
      handleTrans() {
      this.$router.push("/admin/user");
     
      },
      handleBack() {
      this.$router.push("/admin/report");
      
      }
   
   
    }
  
  
}
</script>
