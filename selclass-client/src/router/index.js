import {createRouter, createWebHistory} from 'vue-router'
import toLogin from "@/views/toLogin.vue";
import Home from "@/views/Home.vue";
import selClass from "@/views/selclass.vue";
import timeTable from "@/views/Timetable.vue";
import info from "@/views/inFo.vue";
import classPoint from "@/views/classPoint.vue";
import aclass from "@/views/aClass.vue"
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

    },
    {path:'/home',
        name:'Home',
        component:Home,
        children:[{path:'/selclass',
            name:'selclass',
            component:selClass

        },
            {path:'/timetable',
                name:'timetable',
                component:timeTable

            },
            {path:'/info',
                name:'info',
                component:info

            },
            {path:'/classpoint',
                name:'classpoint',
                component:classPoint

            },
            {path:'/aclass',
                name:'aclass',
                component:aclass

            }]
    }  ,

]})
export default router