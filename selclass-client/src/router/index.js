import {createRouter, createWebHistory} from 'vue-router'
import toLogin from "@/views/toLogin.vue";
const routerHistory = createWebHistory()

const router = createRouter({
    history: routerHistory,
    /*
     *   在这里和vue2一样写路由配置
    */
routes:[
    {path:'/',
    name:'tologin',
    component:toLogin
    }
]})
export default router