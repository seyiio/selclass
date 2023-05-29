<template>
  <div class="common-layout">
    <el-container id="container">
      <el-header ><h4 class="title">大学生抢课系统</h4></el-header>
      <el-container id="body" >
        <el-aside width="200px">
            <h5 class="mb-2 " style="text-align: center">菜单</h5>
            <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                router
            >
              <el-menu-item index="/selclass" v-show="student">
                <el-icon><icon-menu /></el-icon>
                <span>抢课</span>
              </el-menu-item>
              <el-menu-item index="/selclasstime" v-show="admin">
                <el-icon><icon-menu /></el-icon>
                <span>抢课时间</span>
              </el-menu-item>
              <el-menu-item index="/timetable" v-show="!admin">
                <el-icon><icon-menu /></el-icon>
                <span>课表</span>
              </el-menu-item>
              <el-menu-item index="/info" >
                <el-icon><document /></el-icon>
                <span>个人信息</span>
              </el-menu-item>
              <el-menu-item index="/classpoint" v-show="student">
                <el-icon><setting /></el-icon>
                <span>成绩查询</span>
              </el-menu-item>
              <el-menu-item index="/aClass" v-show="t">
                <el-icon><setting /></el-icon>
                <span>成绩打分</span>
              </el-menu-item>
            </el-menu>
        </el-aside>
      <el-main> <router-view/> </el-main>
      </el-container>
      <el-footer><h4 class="footer">QVQ</h4></el-footer>
      </el-container>

  </div>
</template>

<script  >
import {getRequest} from "@/utils/api";
import store from '@/store/index'
export default {
  created() {
getRequest('/admin/info').then(dataa=>{
store.state.id=dataa.id;
  if (dataa.type==="0"){
      this.admin=true;
    }else if(dataa.type==="1"){
      this.t=true;
    }
    else this.student=true;
  }
)
  },
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Home",
  data() {

    return {
      t:false,
      admin:false,
      student:false,
    }
  }
}
</script >

<style scoped>

.el-header{

  background-color: #ffeaea;

}
.title{
  color: #333;

  text-align: center;

}
.footer{
  color: #333;

  text-align: center;
}
.el-footer {

  background-color: #efdbd3;

}
#body{
  background-color: #ceff78;
}
#container{
  background-clip: padding-box;
  margin: 5% 10%;
  border-radius: 30px ;
  border:1px solid #eaeaea;
overflow: hidden;
  box-shadow: 0 0 25px #cac6c6;
}
.el-main{
  background-color: #d2faff;
  height: 349px;
}
</style>
<script  setup>

import {
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons-vue'
</script>