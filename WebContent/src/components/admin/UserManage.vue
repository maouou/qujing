<template>
  <!-- 将大容器设置nav类单独设置样式 -->
  <el-container class="nav">
    <el-header class="logo">
<el-image
      style="width: 55px; height: 55px;margin:5px;float:left"
      :src="url"
      :fit="contain"></el-image>
<div style="float:left">用户管理
</div>

<homebtn></homebtn>

    </el-header>

    <el-container >
    <el-aside width="200px"
              style="background-color: rgb(238, 241, 246)">
      <sidermenu></sidermenu>
    </el-aside>
    <el-main style="position:relative">
    	<el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="UID"
      width="140">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.userIDNumber }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="姓名"
      width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.userName }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="积分"
      width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.points }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="状态"
      width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.state }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleChange(scope.row)">积分调整</el-button>
        <el-dialog title="积分调整" :visible.sync="dialogFormVisible">
  <el-form :model="upform"  ref="form">
    <el-form-item label="用户积分调整" :label-width="formLabelWidth">
      <el-input v-model.number="upform.score" autocomplete="off" type="number" placeholder="输入调整后积分"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="handleEdit()">确 定</el-button>
  </div>
</el-dialog>

  <el-button
	  slot="reference"
          size="mini"
          @click="handleReset(scope.$index, scope.row)">重置密码</el-button>

  <el-button
	  slot="reference"
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">注销账号</el-button>

          
  <el-button
	  slot="reference"
          size="mini"
          type="danger"
          @click="handleForbide(scope.$index, scope.row)">封禁账号</el-button>

  <el-button
	  slot="reference"
          size="mini"
          type="danger"
          @click="handleActive(scope.$index, scope.row)">解封账号</el-button>

      </template>
    </el-table-column>
    
  </el-table>

  <div class="block" style="position: absolute;bottom:10px">
    <span class="demonstration"></span>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage1"
      :page-size="7"
      layout="total, prev, pager, next"
      :total="total">
    </el-pagination>
  </div>
    </el-main>   
  

    </el-container>
  </el-container>
</template>

<style>
@import 'admin.css';

</style>

<script>
import axios from 'axios'  
  
export default {
    name: 'UserManage',
    data() {
      return {
      visible1: false,
      visible2: false,
      visible3: false,
      visible4: false,
      visible5: false,
      total:10,
      currentPage: 1,
      alldata:'',
      url:require('@/assets/logo.png'),
        tableData:'',
        dialogFormVisible: false,
        upform: {
          score:'',
          id:''
        },
        formLabelWidth: '120px'
      }
    },
    //初始化表格
    created: function () {
       	axios.get('/admin/usermanage/list.do')
           .then(response => {
              this.alldata = response.data;
              this.pagedivde(1);
            })
    },
    methods: {
      //将数据分页
      pagedivde(cpage){
        this.total=parseInt(this.alldata.length);
        var jr=[];
        var start=(cpage-1)*7;
        var j=0;
        for(var i=start;(i<this.total&&j<7);i++){
          jr.push(this.alldata[i]);
          j++;
        }
        this.tableData=jr;
      },
    //修改用户积分
    handleChange(row)
    {
    	this.upform.id=row.userIDNumber;
      this.upform.score=row.points;
    	this.dialogFormVisible = true;
    },
      handleEdit(index, row) {
      var self=this;
 
      	axios.get('/admin/usermanage/changepoints.do',{params: {IDNumber:self.upform.id,points:self.upform.score}})
           .then(response => {
   
            });
        this.$message({
          showClose: true,
          message: '修改成功'
        });
 		this.dialogFormVisible = false;
      },
      //重置用户密码
      handleReset(index, row) {

      	axios.get('/admin/usermanage/resetpassword.do',{params: {id:row.userIDNumber}})
           .then(response => {
 
            });
        this.$message({
          showClose: true,
          message: '重置成功'
        });

      },
      //注销用户
      handleDelete(index, row) {

      axios.get('/admin/usermanage/deleteaccount.do',{params: {id:row.userIDNumber}})
           .then(response => {
  
            });
        this.$message({
          showClose: true,
          message: '注销成功'
        });
 
      },
      //激活用户
      handleActive(index, row) {

      axios.get('/admin/usermanage/wakeaccount.do',{params: {id:row.userIDNumber}})
           .then(response => {
 
            });
        this.$message({
          showClose: true,
          message: '激活成功'
        });


      },
      //封禁用户
      handleForbide(index, row) {

      axios.get('/admin/usermanage/stopaccount.do',{params: {id:row.userIDNumber}})
           .then(response => {
       
            });
        this.$message({
          showClose: true,
          message: '封禁成功'
        });


      },
      
      handleCurrentChange(val) {
        this.pagedivde(val);
      }
    }
  }
</script>