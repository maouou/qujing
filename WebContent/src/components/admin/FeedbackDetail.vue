<template>
  <!-- 将大容器设置nav类单独设置样式 -->
  <el-container class="nav">
    <el-header class="logo">
<el-image
      style="width: 55px; height: 55px;margin:5px;float:left"
      :src="url"
      :fit="contain"></el-image>
<div style="float:left">反馈详细
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
        <div style="height:45px">
    	<h5>任务积分：</h5>
        </div>
        <div style="height:100px">
    	<h5>任务描述：</h5>
        </div>
    	<div style="height:100px">
    	<h5>反馈详细：</h5>
        </div>
        <div style="height:45px">
    	<h5>收货人：</h5>
        </div>
        <div style="height:45px">
    	<h5>送货人：</h5>
        </div>
        <div style="height:45px">
    	<h5>反馈人：</h5>
        </div>
    </div>
    <div style="height:380px; width:450px;float:left">
    	<div style="height:45px">
    	<b><p>{{detail.taskName}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.points}}</p></b>
        </div>
        <div style="height:100px">
    	<b><p>{{detail.taskContent}}</p></b>
        </div>
    	<div style="height:100px">
    	<b><p>{{detail.backContent}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.receiverName}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.senderName}}</p></b>
        </div>
        <div style="height:45px">
    	<b><p>{{detail.backName}}</p></b>
        </div>
    </div>
    <div style="height:380px; width:200px;float:left">
    <el-col>
	<el-button type="primary" style="width:120px" @click="handleDelete()">未违规</el-button>
	<br /><br />
	<el-button type="primary" style="width:120px" @click="handleTrans()">用户管理</el-button>
	<br /><br />
  <el-button type="primary" style="width:120px" @click="handleChange">积分调整</el-button>
<el-dialog title="双方积分调整" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="收货方积分调整" :label-width="formLabelWidth">
      <el-input v-model.number="form.score1" type="number" autocomplete="off" placeholder="请输入调整后积分"></el-input>
    </el-form-item>
    <el-form-item label="送货方积分调整：" :label-width="formLabelWidth">
      <el-input v-model.number="form.score2" type="number" autocomplete="off" placeholder="请输入调整后积分"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="handleCancel">确 定</el-button>
  </div>
</el-dialog>
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
      ID:'',
      dialogFormVisible:false,
      form: {
          score1:'',
          score2:''
        },
    }
    
    },

    //请求反馈的任务详情页
    created: function () {
    this.ID=this.$route.query.id;
    var self=this;
        axios.get('url',{params: {id:self.ID}})
           .then(response => {
              this.detail=response.data;
            })
        
    },
   methods:{
   //处理反馈已读按钮
      handleDelete() {
      var self=this;
      axios.get('/admin/feedbackManage/legaltask.do',{params: {id:self.detail.backID}})
           .then(response => {
              
            });
      this.$alert('反馈已删除', '消息', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      },
      handleChange(){
        this.form.score1=this.detail.receiverPoints;
        this.form.score2=this.detail.senderPoints;
        this.dialogFormVisible = true;

      },
      //处理积分调整
      handleCancel() {
      	this.dialogFormVisible = false;
      	var self=this;
      axios.get('/admin/feedbackManage/openusermanage.do',{params: {receiverstudentId:self.detail.receiverstudentId,senderstudentId:self.detail.senderstudentId,receiverpoints:self.form.score1,senderpoints:self.form.score2}})
           .then(response => {
              this.$alert('任务已删除、积分已调整', '消息', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        }); 
            });
           
      },
      handleTrans() {
      this.$router.push("/admin/user");
     
      },
      handleBack() {
      this.$router.push("/admin/feedback");
      
      }
   
   
    }
  
  
}
</script>
