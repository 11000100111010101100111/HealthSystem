<template>
  <div class="login_container">
    <!-- 登录块 -->
    <div class="login_box">
      <!-- logo区域 -->
      <div class="avatar_box">
        <img src="../assets/login-logo.png" alt />
      </div>
      <!-- 表单区域 -->
      <el-form
        ref="loginFromRef"
        :rules="loginRules"
        :model="loginForm"
        class="login_from"
        label-width="0px"
      >
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            prefix-icon="iconfont icon-yonghu"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="iconfont icon-mima"
            type="password"
          ></el-input>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="login()">提交</el-button>
          <el-button type="info" @click="resetLoginFrom()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //表单数据
      loginForm: {
        username: "四原色",
        password: "123456",
      },
      //验证规则
      loginRules: {
        username: [
          { required: true, message: "请输入登录账号", trigger: "blur" },
          { min: 2, max: 5, message: "长度在 2 到 5 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入登录密码", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    resetLoginFrom() {
      console.log("重置表单...");
      //表单重置
      this.$refs.loginFromRef.resetFields();
    },

    //处理登录业务，
    login() {
      //表单提交
      console.log("登录...");

      //先完成验证：验证验证校验规则，
      this.$refs.loginFromRef.validate(async (valid) => {
        //判断验证输入是否成功，TRUE、false
        console.log(valid);
        if (!valid) {
          return;
        }

        // 访问后端接口
        const { data: res } = await this.$http.post("login", this.loginForm); //异步访问后台
        console.log(res);
        if (res.flage == "ok") {
          console.log(res.user);
          //存储当前登录的user对象
          window.sessionStorage.setItem("user", res.user);

          this.$message.success("登录成功！");
          console.log(window.sessionStorage.getItem("user"));
          //跳转页面到主页,页面路由跳转
          this.$router.push({ path: "/home" });

          //返回结果为成功！
          this.$message.success(
            "欢迎登录，亲爱的：" + res.user.role + "-" + res.user.username
          );
          console.log(res.user);
        } else {
          //错误提示
          // alert("登录失败了！");
          this.$message.error("登录失败：账号或密码有误！");
        }
      });
    },
  },
};
</script>

<style>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.avatar_box {
  width: 130px;
  height: 130px;
  border-radius: 50%;
  padding: 5px;
  box-shadow: 0 0 12px rgb(42, 186, 165);
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
}
.avatar_box img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}
.login_from {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: flex-end;
}
</style>