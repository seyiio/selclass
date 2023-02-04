import axios from "axios";
import { ElMessage  } from 'element-plus'
import router from "@/router";
axios.interceptors.response.use(success=>{
    if(success.status&&success.status==200){
        if (success.data.code==500||success.data.code==401||success.data.code==401){
            ElMessage .error({message:success.data.message});
            return;
        }
        if(success.data.message){
            ElMessage .success({message:success.data.message});
        }
    }
    return success.data;
},error => {
    if(error.response.code==504||error.response.code==404){
        ElMessage .error({message:'服务器被吃了QAQ'});
    }else if (error.response.code==403){
        ElMessage .error({message:'权限不足，请联系管理员！'})

    }else if (error.response.code==401){
        ElMessage.error({message:'尚未登录，请登录'});
        router.replace('/');

    }else {
        if (error.response.data.message){
            ElMessage.error({message:error.response.data.message});
        }else {
            ElMessage.error({message:'未知错误！'});
        }
    }
    return;
});

export const postRequest=(url,params)=>{
    return axios({
        method: 'post',
        url: url,
        data:params,
    })

}