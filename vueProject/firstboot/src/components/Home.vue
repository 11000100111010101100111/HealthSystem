<template>
  <!-- <div>
    home mianpage
    <el-button type="info" @click="logout">退出登录</el-button>
  </div> -->
  <!-- //引入布局 -->
  <el-container class="home-container">
    <!-- 头部 -->
    <el-header>
      <div>
        <img src="../assets/logo.png" alt class="logo_img" />
      </div>
      <span class="head-span iconfont icon-jiankang">健康管理平台</span>
      <el-button type="info" @click="logout">退出登录</el-button>
    </el-header>

    <!-- 主体 -->
    <el-container>
      <!-- 左边侧边栏 -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="close-open-bar iconfont icon-caidan" @click="showBar"></div>
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#409eff"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active="activePath"
        >
          <!-- 一级菜单 -->
          <el-submenu
            :index="item.id + ''"
            v-for="item in menuList"
            :key="item.id"
          >
            <template slot="title">
              <i class="iconfont" :class="item.font"></i>
              <span>{{ item.title }}</span>
            </template>

            <!--二级菜单 -->
            <el-menu-item
              :index="it.path"
              v-for="it in item.sList"
              :key="it.id"
              @click="setPath(it.path)"
            >
              <template slot="title">
                <i class="iconfont" :class="it.font"></i>
                <span>{{ it.title }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!-- 右边信息面板 -->
      <el-main>
        <router-view> </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      // 返回一级菜单列表
      menuList: [],
      isCollapse: true, //伸缩属性
      activePath: "/welcome", //默认路径路由
    };
  },
  // 等同onload(),页面加载时发生
  created() {
    //查询menulist 列表
    this.getMenuList();
    // 拉取活动路径
    this.activePath = window.sessionStorage.getItem("activePath");
  },
  methods: {
    logout() {
      // 清除session内的登录状态信息
      window.sessionStorage.removeItem("user");

      //回退到登录页面
      this.$router.push("/login");
      this.$message.success("操作成功！安全退出！");
    },
    //获取导航菜单方法
    async getMenuList() {
      console.log("获取到菜单列表...");

      const { data: res } = await this.$http.get("menus");

      if (res.status != 200) {
        return this.$message.error("获取菜单列表失败！");
      }

      this.menuList = res.menus; //访问成功后数据回填

      console.log(res);
    },
    // 控制侧边栏伸缩
    showBar() {
      this.isCollapse = !this.isCollapse;
    },
    // 更改路由路径,保存当前路径
    setPath(path) {
      // 将路径暂存到session
      window.sessionStorage.setItem("activePath", path);

      this.activePath = path;
    },
  },
};
</script>

<style>
/* 总体布局样式 */
.home-container {
  height: 100%;
}
/* //头部样式 */
.el-header {
  background-color: #373d41;
  display: flex;

  /* //左右元素靠边 */
  justify-content: space-between;

  padding-left: 0%;
  /* 元素水平、垂直居中 */
  align-items: center;

  font-size: 20px;
  align-content: center;
}
/* .el-header *{
    display: inline-block;
  } */
.el-header img {
  height: 55px;
  height: 55px;
}
.el-header .head-span {
  position: relative;
  text-align: center;
  display: inline-block;
  /* height: 50%;
  top: 50%;
  transform: translateY(-50%); */
  color: #fff;
  /* background-color: darkcyan; */
}
/* 侧边栏样式 */
.el-aside {
  background-color: #333744;
  transition: all 0.5s;
}
/* 伸缩按钮 */
.el-aside .close-open-bar {
  background-color: #4a5064;
  /* font-size: 10px; */
  line-height: 24px;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 26px;
  color: #fff;
}
.el-aside .el-menu {
  border-right: none;
  transition: all 0.5s;
}
/* 主体样式 */
.el-main {
  background-color: #eaedf1;
}
</style>