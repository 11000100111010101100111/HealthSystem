import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入login组件
import Login from '../components/login.vue'
import Home from '../components/Home.vue'

Vue.use(VueRouter)

// 配置路由
const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    //转发
    path: "/login",
    component: Login
  },
  {
    path: "/home",
    component: Home

  },
]

const router = new VueRouter({
  routes
})

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this,location).catch(err => err);
};

//挂载路由导航守卫
router.beforeEach((to, from, nextdo) => {
  // to：将要访问
  // from：从哪访问
  // nextdo：接下来要做的 next(url)： 重定向到url上 || next()： 继续访问to路径
  if (to.path == '/login') {
    // 如果访问的是首页，对这个访问放行
    return nextdo();
  }
  //从session中获取登录状态的user
  const userFlage = window.sessionStorage.getItem("user");
  if (!userFlage) {
    //如果session中没有登录状态用户，则返回登录页
    return nextdo('/login');
  }

  nextdo();
});
export default router
