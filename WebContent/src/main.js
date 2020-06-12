// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axiosInit from "./axios-init";
import Homebtn from "./components/adminbtn/homebtn.vue"
import Sidermenu from "./components/sidermenu/menu.vue"
window.axios = axiosInit();
Vue.prototype.$http = window.axios;

Vue.config.productionTip = false;
Vue.use(ElementUI);

Vue.component("homebtn",Homebtn);
Vue.component("sidermenu",Sidermenu);
/* eslint-disable no-new */

router.beforeEach((to, from, next) => {
  let islogin = localStorage.getItem("islogin");
  islogin = Boolean(Number(islogin));

  if(to.path == "/"){
    
      next();
    
  }else{
    // requireAuth:可以在路由元信息指定哪些页面需要登录权限
    if(to.meta.requireAuth && islogin) {
      next();
    }else{
      next("/");
    }
  }
})


new Vue({
  el: '#app',
  router,
  render: h => h(App),
  components: { App },
  template: '<App/>'
});
