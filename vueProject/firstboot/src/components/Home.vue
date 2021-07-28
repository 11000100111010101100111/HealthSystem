<template>
  <div>
    home mianpage
    <el-button type="info" @click="logout">退出登录</el-button>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      // 清除session内的登录状态信息
      window.sessionStorage.removeItem("user");

      //回退到登录页面
      this.$router.push("/login");
      this.$message.success("操作成功！安全退出！");
    },
  },
};
</script>

<style>
</style>