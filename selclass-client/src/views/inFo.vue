<template>

  <el-descriptions
      title="个人信息"
      :column="4"
      border
  >
    <el-descriptions-item label="姓名"><span v-text="name"></span></el-descriptions-item>
    <el-descriptions-item label="年级"><span v-text="grade"></span></el-descriptions-item>
    <el-descriptions-item label="专业" :span="2"><span v-text="major"></span></el-descriptions-item>
    <el-descriptions-item label="手机号"><span v-text="phone"></span></el-descriptions-item>
    <el-descriptions-item label="学院"><span v-text="faculty"></span></el-descriptions-item>
  </el-descriptions>

</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "inFo",
  created() {
    getRequest('/admin/info').then(dataa=>{
    this.username=dataa.username;
    this.major=dataa.major;
    this.grade=dataa.grade;
    this.faculty=dataa.faculty;
    this.phone=dataa.phone;
    this.name=dataa.name;
        }
    )
  },
  data(){
      return{
        username:"",
        major:"",
        faculty:"",
        grade:"",
        phone:"",
        name:"",
    }
}
}
</script>

<style scoped>

</style>