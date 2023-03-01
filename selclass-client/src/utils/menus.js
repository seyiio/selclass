import {getRequest} from "@/utils/api";
import c from "@/views/aClass.vue";
import Home from "@/views/Home.vue";

export const initMenu=(router,store)=>{
if(store.state.route.length>0){
    return;
}
getRequest('/admin/info').then(data=>{
    if(data["type"]!=3){
        let fmtRouts={
            path:"/Home",
            name:"home",
            children:[{
                path:"/class",
                name: "aClass",
                comment: c,
            }
            ],
            comment:Home,}

        router.addRoute(fmtRouts);   }
})
}