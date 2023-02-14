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
            >
              <el-menu-item index="1">
                <el-icon><icon-menu /></el-icon>
                <span>抢课</span>
              </el-menu-item>
              <el-menu-item index="2">
                <el-icon><icon-menu /></el-icon>
                <span>课表</span>
              </el-menu-item>
              <el-menu-item index="3" >
                <el-icon><document /></el-icon>
                <span>个人信息</span>
              </el-menu-item>
              <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>成绩查询</span>
              </el-menu-item>
            </el-menu>
        </el-aside>
      <el-main>  <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="classname " label="课程名称" width="180" />
        <el-table-column prop="point" label="学分" width="180" />
        <el-table-column prop="address" label="任课老师" />
        <el-table-column prop="number" label="剩余数量" />
      </el-table></el-main>
      </el-container>
      <el-footer>Footer</el-footer>
      </el-container>

  </div>
</template>

<script >
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Home"

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