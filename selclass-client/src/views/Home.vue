<template>
  <div class="common-layout">
    <el-container>
      <el-header>大学生抢课系统</el-header>
      <el-container>
        <el-aside width="200px">
            <h5 class="mb-2">Default colors</h5>
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
      <el-footer>Footer</el-footer>
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

</style>
<script  setup>

import {
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons-vue'
</script>