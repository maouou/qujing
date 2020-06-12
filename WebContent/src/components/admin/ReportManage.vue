<template>
  <!-- 将大容器设置nav类单独设置样式 -->
  <el-container class="nav">
    <el-header class="logo">
<el-image
      style="width: 55px; height: 55px;margin:5px;float:left"
      :src="url"
      :fit="contain"></el-image>
<div style="float:left">举报管理
</div>

<homebtn></homebtn>

    </el-header>

<el-container>
    <el-aside width="200px"
              style="background-color: rgb(238, 241, 246)">
      <sidermenu></sidermenu>
    </el-aside>
    
      <el-main style="position:relative">
    	<el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="任务名称"
      width="180">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.taskName }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="送货方"
      width="120">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.sender }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="收货方"
      width="120">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
      </template>
    </el-table-column>
    
    <el-table-column
      label="违规项"
      width="100">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.type }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="描述"
      width="250">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.content }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleDetail(scope.$index, scope.row)">详细</el-button>
     
          
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
  name: 'ReportManage',
  data() {
      return {
      currentPage: 1,
      total:10,
      alldata:'',
      url:require('@/assets/logo.png'),
        tableData:''
      }
    },
    //初始化表格
    created: function () {
        axios.get('/admin/reportmanage/list.do')
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
      handleDetail(index, row) {
      this.$router.push({  
            path: '/admin/reportdetail', 
            query: {   
                id:row.id
            } 
            
        });

      },
      
      handleCurrentChange(val) {
        this.pagedivde(val);
      }
    }
}
</script>