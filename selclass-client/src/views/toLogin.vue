<template>
<div><el-form ref="loginForm" :model="loginForm" class="loginContainer" :rules="rules">
<h3 class="loginTitle">抢课系统</h3>
  <el-form-item prop="username">
    <el-input type="text"  v-model="loginForm.username" placeholder="请输入用户名或学号">

    </el-input>

  </el-form-item>
  <el-form-item prop="password">
    <el-input type="password" v-model="loginForm.password" placeholder="请输入密码">

    </el-input>

  </el-form-item>

    <el-button type="primary" style="width:100%" @click="submitLogin">登录</el-button>

</el-form></div>
</template>

<script>

import {postRequest} from "@/utils/api";

export default {
  name: "toLogin",
  data(){
    return{
    loginForm: {
      username: '',
      password:'',

    },
      rules:{
        username:[{required:true,message:'请输入学号',trigger:'blur'}],
        password:[{required:true,message:'请输入密码',trigger:'blur'}],
      }
    }
  },
  methods:{
    submitLogin(){
      this.$refs.loginForm.validate((valid)=>{
        if(valid){
          postRequest("/login",this.loginForm).then(resp=>{
            alert(resp);
          })
        }else {
          alert((222));
          return false;
        }
      })
    }

  },
}
</script>

<style >
.loginContainer{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding:15px 35px 15px 35px ;
  background: #fff;
  border:1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;

}
.loginTitle{
  margin: 0 auto 40px auto;
  text-align: center;
}
</style>