import axios from "axios";
import { ElMessage  } from 'element-plus'
import router from "@/router";
axios.interceptors.request.use(config => {
    // 如果存在 token，请求携带这个 token( 登录的时候 把 token 存入了 sessionStorage ）
    if (window.sessionStorage.getItem("tokenStr")) {
        // token 的key : Authorization ; value: tokenStr
        config.headers['Authorization'] = window.sessionStorage.getItem('tokenStr')
    }
    return config;
},error => {
    console.log(error)
})
axios.interceptors.response.use(success=>{

    if(success.status&&success.status===200){
        if (success.data.code===500||success.data.code===403){

            ElMessage .error({message:success.data.message});
            return;
        }else if(success.data.code===401){
            router.replace('/')
            return ;
        }
        if(success.data.message){
            ElMessage .success({message:success.data.message});
        }
    }
    return success.data;
},error => {

    if(error.response.code===504||error.response.code===404){
        ElMessage .error({message:'服务器被吃了QAQ'});
    }else if (error.response.code===403){
        ElMessage .error({message:'权限不足，请联系管理员！'})

    }else if (error.response.code===401){
        ElMessage.error({message:'尚未登录，请登录'});



    }else {
        if (error.response.data.message){
            ElMessage.error({message:error.response.data.message});
        }else {
            ElMessage.error({message:'未知错误！'});
        }
    }
    return;
});
const base='/api'
export const postRequest=(url,params)=>{
    return axios({
        method: 'post',
        url: base+url,
        data:params,
    })

}
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url:base+url,
        data: params
    })
}

// 传送 json 格式的 put 请求
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url:base+ url,
        data: params
    })
}

// 传送 json 格式的 delete 请求
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: base+url,
        data: params
    })
}