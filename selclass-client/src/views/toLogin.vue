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
      username:'',
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
        if (valid) {
          // alert('submit!')
          postRequest('/login', this.loginForm).then(resp => {
            // alert(JSON.stringify(resp));

            if (resp) {
              // 存储用户 token 到 sessionStorage
              const tokenStr = resp.obj.tokenHead + resp.obj.token
              window.sessionStorage.setItem('tokenStr', tokenStr)
              // 跳转到首页
              // this.$router.push('/home') // 路由跳转，可以回退到上一页
              // this.$router.replace('/home') // 路径替换，无法回退到上一页
              // 页面跳转
              // 拿到用户要跳转的路径
              let path = this.$route.query.redirect;
              // 用户可能输入首页地址或错误地址，让他跳到首页，否则跳转到他输入的地址
              this.$router.replace((path === '/' || path === undefined) ? '/home' : path)
            }
          })
        }else {

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