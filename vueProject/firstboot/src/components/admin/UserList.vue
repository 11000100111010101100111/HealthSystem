<template>
  <div>
    <!-- 用户列表 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="el-breadcrumb">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表主体部分 -->
    <el-card>
      <!-- 搜索区域 -->

      <!-- 间隙 -->
      <el-row :gutter="25">
        <!-- 列宽 -->
        <el-col :span="6">
          <!-- 搜索添加 -->
          <el-input
            placeholder="请输入搜索内容..."
            v-model="queryInfo.query"
            clearable
            @clear="getUserList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>

        <el-col :span="4" @click="addDialogVisiable = true">
          <!-- 搜索按钮 -->
          <el-button type="primary" @click="addDialogVisible = true"
            >添加用户</el-button
          >
        </el-col>
      </el-row>

      <!-- <span>{{userList}}</span> -->
      <!-- 用户列表显示区域 -->
      <!-- border:边框 stripe：隔行变色 -->
      <el-table :data="userList" border stripe class="el-table">
        <!-- 索引列 -->
        <el-table-column label="#." type="index"></el-table-column>
        <!-- 用户名 -->
        <el-table-column label="用户名" prop="username"></el-table-column>
        <!-- 邮  箱 -->
        <el-table-column label="邮  箱" prop="email"></el-table-column>
        <!-- 密  码 -->
        <el-table-column label="密  码" prop="password"></el-table-column>
        <!-- 角  色 -->
        <el-table-column label="角  色" prop="role"></el-table-column>
        <!-- 状  态 -->
        <el-table-column label="状  态" prop="state">
          <!-- 添加作用域插槽 -->
          <template slot-scope="scope">
            <!-- {{scope.row}}每一行封存的数据 -->
            <el-switch
              v-model="scope.row.state"
              @change="changeState(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>

        <el-table-column label="操  作">
          <!-- 自定义插槽 -->
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="selectUser(scope.row.id)"
            ></el-button>
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeUser(scope.row.id)"
            ></el-button>
            <!-- 权限 -->
            <el-tooltip
              effect="dark"
              content="分配权限"
              placement="top-start"
              :enterable="false"
            >
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!-- 使用element-ui提供的分页组件 -->
      <!-- @size-change:每页最大变化数
          @current-change=当前最大变化
          :current-page="currentPage4"
          :page-sizes：分页大小选项"
          :page-size:分页大小
          layout：功能组件 -->
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pageNum"
          :page-sizes="[1, 5, 10, 50, 100]"
          :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>

    <!-- 添加用户对话框 -->
    <el-dialog
      title="添加用户"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClose"
    >
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="70px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>

        <el-form-item label="密  码" prop="password">
          <el-input v-model="addForm.password" type="password"></el-input>
        </el-form-item>

        <el-form-item label="邮  箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addUser">确定</el-button>
      </span>
    </el-dialog>

    <!-- 修改用户信息对话框 -->
    <el-dialog
      title="修改用户信息"
      :visible.sync="modifyDialogVisible"
      width="50%"
      @close="modifyDialogClose"
    >
      <el-form
        :model="modifyUserForm"
        :rules="modifyFormRules"
        ref="modifyFormRef"
        label-width="70px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="modifyUserForm.username"></el-input>
        </el-form-item>

        <el-form-item label="密  码" prop="password">
          <el-input
            v-model="modifyUserForm.password"
            type="password"
          ></el-input>
        </el-form-item>

        <el-form-item label="邮  箱" prop="email">
          <el-input v-model="modifyUserForm.email"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="modifyDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="modifyUser">确定修改</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  created() {
    this.getUserList();
  },
  data() {
    return {
      //查询信息封装的实体
      queryInfo: {
        query: "", //查询信息
        pageNum: 1, //查询的当前页
        pageSize: 5, //每页最大数
      },
      // 用户列表
      userList: [],
      total: 0, //总记录数
      addDialogVisible: false, //添加用户的对话框显示状态
      //用户信息
      addForm: {
        username: "",
        password: "",
        email: "",
      },
      //添加用户表单验证
      addFormRules: {
        username: [
          { required: true, message: "请输入您的名称...", tigger: "blur" },
          { min: 2, max: 8, message: "名称由2到8个字符组成", tigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入您的密码...", tigger: "blur" },
          { min: 6, max: 15, message: "名称由6到15个字符组成", tigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入您的邮箱...", tigger: "blur" },
          { min: 5, max: 15, message: "请输入正确的邮箱...", tigger: "blur" },
        ],
      },
      // 修改的用户信息
      modifyUserForm: {
        username: "",
        password: "",
        email: "",
      },
      //显示/隐藏修改用户信息表单
      modifyDialogVisible: false,
      //修改用户表单验证
      modifyFormRules: {
        username: [
          { required: true, message: "请输入您的名称...", tigger: "blur" },
          { min: 2, max: 8, message: "名称由2到8个字符组成", tigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入您的密码...", tigger: "blur" },
          { min: 6, max: 15, message: "名称由6到15个字符组成", tigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入您的邮箱...", tigger: "blur" },
          { min: 5, max: 15, message: "请输入正确的邮箱...", tigger: "blur" },
        ],
      },
    };
  },
  methods: {
    // 获取所有用户列表
    async getUserList() {
      console.log("获取所有用户列表...");
      const { data: res } = await this.$http.get("allUser", {
        params: this.queryInfo,
      });
      // console.log(res.data);s
      this.userList = res.data; //用户数据封装
      this.total = res.userCount; //用户总数
    },
    //最大数
    handleSizeChange(newSize) {
      //页面变化时发生
      this.queryInfo.pageSize = newSize;
      this.getUserList();
    },
    //页面变化时触发动作
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getUserList();
    },
    async changeState(userInfo) {
      const { data: res } = await this.$http.post(
        `changeState?id=${userInfo.id}&state=${userInfo.state}`
      );
      if (res != "succeed") {
        userInfo.state = !userInfo.state;
        // this.$message.success();
        return this.$message.error("修改失败！");
      }
      this.$message.success("操作成功！账号状态已修改！");
    },
    addUser() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) {
          return this.$message.error("输入内容为空或输入不完整！无法提交！");
        }
        const { data: res } = await this.$http.post("addUser", this.addForm);
        if (res == "error") {
          return this.$message.error("连接错误！添加失败！");
        } else if (res != "succeed") {
          return this.$message.error("操作失败：" + res);
        }
        this.$message.success("用户添加成功！");

        this.addDialogVisible = false;
        //刷新用户列表
        this.getUserList();
      });
    },
    //监听添加用户的操作，
    addDialogClose() {
      this.$refs.addFormRef.resetFields();
    },
    //删除用户
    async removeUser(id) {
      const resoult = await this.$confirm(
        "将永久删除用户信息，是否继续？",
        "警告",
        {
          confirmButtonTest: "确定",
          cancelButtonTest: "取消",
          type: "warning",
        }
      ).catch((err) => err);
      if (resoult != "confirm") {
        return this.$message.info("删除已取消！");
      }

      const { data: res } = await this.$http.post(`removeUser?id=${id}`);
      if (res != "succeed") {
        return this.$message.error("连接错误！删除失败！");
      }
      this.$message.success("用户删除成功！");
      //刷新用户列表
      this.getUserList();
    },
    async selectUser(id) {
      //ref: selectUser
      // console.log("查询用户...");
      const { data: res } = await this.$http.post(`selectUser?id=${id}`);
      if (res.flage != "succeed") {
        return this.$message.error("连接错误！");
      }

      this.modifyUserForm = res.user;
      this.modifyDialogVisible = true;
    },
    modifyDialogClose() {
      this.$refs.modifyFormRef.resetFields();
    },
    //修改用户
    modifyUser() {
      //ref: modifyUser
      // console.log("修改用户...");
      this.$refs.modifyFormRef.validate(async (valid) => {
        if (!valid) {
          return this.$message.error("输入内容为空或输入不完整！无法提交！");
        }
        const { data: res } = await this.$http.post(
          "modifyUser",
          this.modifyUserForm
        );
        if (res != "succeed") {
          return this.$message.error("连接错误！信息修改失败！");
        }
        this.$message.success("用户信息修改成功！");

        this.modifyDialogVisible = false;
        //刷新用户列表
        this.getUserList();
      });
    },
  },
};
</script>

<style>
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 12px;
}
.el-table * {
  text-align: center;
}
.el-table el-table-column {
  font-weight: bold;
}
</style>