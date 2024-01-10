

<template>
  <el-table
      :data="userList"
      stripe
      height="309"
      style="width: 100%">
    <el-table-column
        prop="id"
        label="id">
    </el-table-column>
    <el-table-column
        prop="username"
        label="学号">
    </el-table-column>
    <el-table-column
        prop="major"
        label="专业">
    </el-table-column>
    <el-table-column
        prop="name"
        label="姓名">
    </el-table-column>
    <el-table-column
        prop="faculty"
        label="学院">

    </el-table-column>
    <el-table-column
        prop="grade"
        label="年级">
    </el-table-column>
    <el-table-column
        prop="phone"
        label="手机">
    </el-table-column>
    <el-table-column
        prop="type"
        label="类别">
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-button
            link
            type="primary"
            size="small"
            @click.prevent="deleteUser(scope.$index)"
        >
          删除
        </el-button>
        <el-button
            link
            type="primary"
            size="small"
            @click.prevent="changeUser(scope.$index)"
            >
          修改
        </el-button>
      </template></el-table-column>
  </el-table>
  <el-button
      link
      type="primary"
      size="small"
      @click.prevent="addUser()"
  >
    添加
  </el-button>

</template>

<script >
import {getRequest, postRequest} from "@/utils/api";
export default {
  name: "userManage",
  data(){
    return{
      userList:[],
    }
  },
  created() {
    getRequest('/user/getusers').then(data => {
      if (data) {
        this.userList = data.obj;
      }
    });
  },
  methods:{
    deleteUser(index){
      postRequest('/user/deleteuser',this.userList[index]).then(data=>{
        if(data){
          this.userList.splice(index,1);
        }
      })
    },
    changeUser(index){
      this.$router.push({path:'/changeUser',query:{type:'change',id:this.userList[index].username}})
    },
    addUser(){
      this.$router.push({path:'/changeUser',query:{type:'add'}})
    }
  }
}
</script>
<style scoped>

</style>