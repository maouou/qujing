import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import AdminIndex from '@/components/admin/AdminIndex'
import UserManage from '@/components/admin/UserManage'
import ReportManage from '@/components/admin/ReportManage'
import RequireManage from '@/components/admin/RequireManage'
import ReportDetail from '@/components/admin/ReportDetail'
import RequireDetail from '@/components/admin/RequireDetail'
import FeedbackManage from '@/components/admin/FeedbackManage'
import FeedbackDetail from '@/components/admin/FeedbackDetail'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/admin',
      name: 'AdminIndex',
      component: AdminIndex,
      meta: { // 在路由配置中加入meta:{requireAuth: true}
             requireAuth: true
            }
    },
    {
      path: '/admin/user',
      name: 'UserManage',
      component: UserManage,
      meta: { // 在路由配置中加入meta:{requireAuth: true}
             requireAuth: true
            }
    },
   
    {
      path: '/admin/report',
      name: 'ReportManage',
      component: ReportManage,
      meta: { // 在路由配置中加入meta:{requireAuth: true}
             requireAuth: true
            }
    },
    {
        path: '/admin/feedback',
        name: 'FeedbackManage',
        component: FeedbackManage,
        meta: { // 在路由配置中加入meta:{requireAuth: true}
               requireAuth: true
              }
      },
      
    {
        path: '/admin/require',
        name: 'RequireManage',
        component: RequireManage,
        meta: { // 在路由配置中加入meta:{requireAuth: true}
               requireAuth: true
              }
      },
      {
          path: '/admin/feedbackdetail',
          name: 'FeedbackDetail',
          component: FeedbackDetail,
          meta: { // 在路由配置中加入meta:{requireAuth: true}
                 requireAuth: true
                }
        },
      {
          path: '/admin/reportdetail',
          name: 'ReportDetail',
          component: ReportDetail,
          meta: { // 在路由配置中加入meta:{requireAuth: true}
                 requireAuth: true
                }
        },
        {
            path: '/admin/requiredetail',
            name: 'RequireDetail',
            component: RequireDetail,
            meta: { // 在路由配置中加入meta:{requireAuth: true}
                   requireAuth: true
                  }
          }

      

  ]
})