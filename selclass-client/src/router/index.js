import {createRouter, createWebHistory} from 'vue-router'
import toLogin from "@/views/toLogin.vue";
import Home from "@/views/Home.vue";
import selClass from "@/views/selclass.vue";
import timeTable from "@/views/Timetable.vue";
import info from "@/views/inFo.vue";
import classPoint from "@/views/classPoint.vue";
import aclass from "@/views/aClass.vue"
import putpoint from "@/views/point.vue"
import selclasstime from "@/views/selclasstime.vue";
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
        redirect:'/info',
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

            },
            {path:'/selclasstime',
                name:'selclasstime',
                component:selclasstime

            },{
        path:'/point',
                name:'putpoint',
                component:putpoint,

            }]
    }  ,

]})
export default router