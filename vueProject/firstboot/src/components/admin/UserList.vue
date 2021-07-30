<template>
  <div>
      <!-- 用户列表 -->
      <el-breadcrumb separator-class="el-icon-arrow-right" class="el-breadcrumb">
        <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
        <el-breadcrumb-item >权限管理</el-breadcrumb-item>
        <el-breadcrumb-item >用户列表</el-breadcrumb-item>
      </el-breadcrumb>
      <!-- 用户列表主体部分 -->
      <el-card>
        <!-- 搜索区域 -->

        <!-- 间隙 -->
        <el-row :gutter="25">

          <!-- 列宽 -->
          <el-col :span="6">
            <!-- 搜索添加 -->
            <el-input placeholder="请输入搜索内容..." >
              <el-button slot="append" icon="el-icon-search" ></el-button>
            </el-input>
          </el-col>

          <el-col :span="4"@click="addDialogVisiable = true"> 
            <!-- 搜索按钮 -->
              <el-button type="primary"  >添加用户</el-button>
          </el-col>

        </el-row>

        <!-- <span>{{userList}}</span> -->
        <!-- 用户列表显示区域 -->
        <!-- border:边框 stripe：隔行变色 -->
        <el-table :data="userList" border stripe class="el-table">
          <!-- 索引列 -->
          <el-table-column label="#." type="index" ></el-table-column>
            <!-- 用户名 -->
          <el-table-column label="用户名" prop="username" ></el-table-column>
            <!-- 邮  箱 -->
          <el-table-column label="邮  箱"  prop="email" ></el-table-column>
            <!-- 密  码 -->
          <el-table-column label="密  码"  prop="password" ></el-table-column>
            <!-- 角  色 -->
          <el-table-column label="角  色"  prop="role" ></el-table-column>
            <!-- 状  态 -->
          <el-table-column label="状  态"  prop="state" >
            <!-- 添加作用域插槽 -->
            <template slot-scope="scope">
              <!-- {{scope.row}}每一行封存的数据 -->
              <el-switch v-model = 'scope.row.state'></el-switch>
            </template>
          </el-table-column>

          <el-table-column label="操  作" >
            <!-- 自定义插槽 -->
            <template slot-scope="scope">
              <!-- 修改 -->
              <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
              <!-- 删除 -->
              <el-button type="danger" icon="el-icon-delete" size="mini" ></el-button>
              <!-- 权限 -->
              <el-tooltip effect="dark" content="分配权限" placement="top-start" :enterable="false" >
                <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
  </div>
</template>

<script>
export default {
  created(){
    this.getUserList();
  },
  data(){
    return{
      //查询信息封装的实体
      queryInfo:{
        query:"",//查询信息
        pageNum:1,//查询的当前页
        pageSize:5,//每页最大数
      },
      // 用户列表
      userList:[

      ],
      total:0,//总记录数
    }
  },
  methods:{
    // 获取所有用户列表
    async getUserList(){
      console.log('获取所有用户列表...');
      const {data:res} = await this.$http.get("allUser",{params:this.queryInfo});
      // console.log(res.data);
      this.userList = res.data;
      this.total = res.unumner;
    },
  },
}
</script>

<style>
  .el-breadcrumb{
    margin-bottom: 15px;
    font-size: 12px;
  }
  .el-table *{
    text-align: center;
  }
  .el-table el-table-column{
    font-weight: bold;
    
  }
</style>