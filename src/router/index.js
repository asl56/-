import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import AdminIndex from '../views/AdminIndex.vue'
import AdminHome from '../views/AdminHome.vue'
import AdminNotice from '@/views/AdminNotice.vue'
import PersonalCenter from '@/views/PersonalCenter.vue'
import AdminLost from '@/views/AdminLost.vue'
import AdminFound from '@/views/AdminFound.vue'
import AdminFeedBack from '@/views/AdminFeedBack.vue'
import AdminManage from '@/views/AdminManage.vue'
import UserManage from '@/views/UserManage.vue'
import UserIndex from '@/views/user/UserIndex.vue'
import UserHome from '@/views/user/UserHome.vue'
import UserLostHome from '@/views/user/UserLostHome.vue'
import UserFoundHome from '@/views/user/UserFoundHome.vue'
import UserFeedBack from '@/views/user/UserFeedBack.vue'
import UserPersonalCenter from '@/views/user/UserPersonalCenter.vue'
import MyLost from '@/views/user/MyLost.vue'
import MyFound from '@/views/user/MyFound.vue'
import MyContact from '@/views/user/MyContact.vue'
import ContactMe from '@/views/user/ContactMe.vue'
import AdminVerify from '@/views/AdminVerify.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/PersonalCenter',
    name: 'PersonalCenter',
    component: PersonalCenter
  },
  {
    path: '/UserIndex',
    name: 'UserIndex',
    component: UserIndex,
    children:[
      { path: 'UserHome', name: 'home', component: UserHome },
      { path: 'UserLostHome', name: 'Losthome', component: UserLostHome },
      { path: 'UserFoundHome', name: 'FoundHome', component: UserFoundHome },
      { path: 'UserFeedBack', name: 'feedback', component: UserFeedBack },
      { path: 'UserPersonalCenter', name: 'PersonalCenter', component: UserPersonalCenter },
      { path: 'MyLost', name: 'MyLost', component: MyLost },
      { path: 'MyFound', name: 'MyFound', component: MyFound },
      { path: 'MyContact', name: 'MyContact', component: MyContact },
      { path: 'ContactMe', name: 'ContactMe', component: ContactMe },
    ]
  },
  {
    path: '/AdminIndex',
    name: 'index',
    component: AdminIndex,
    children: [
      { path: 'AdminHome', name: 'home', component: AdminHome },
      { path: 'AdminNotice', name: 'notice', component: AdminNotice },
      { path: 'AdminLost', name: 'lost', component: AdminLost },
      { path: 'AdminFound', name: 'found', component: AdminFound },
      { path: 'AdminFeedBack', name: 'feedback', component: AdminFeedBack },
      { path: 'AdminManage', name: 'manage', component: AdminManage },
      { path: 'UserManage', name: 'userManage', component: UserManage },
      { path: 'UserManage', name: 'userManage', component: UserManage },
      { path: 'AdminVerify', name: 'AdminVerify', component: AdminVerify },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
