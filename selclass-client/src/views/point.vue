<template><div>
  <el-table
      :data="studentlist"
      stripe
      style="width: 100%">
    <el-table-column
        prop="username"
        label="学号">
    </el-table-column>
    <el-table-column
        prop="name"
        label="学生姓名"
        width="180">
    </el-table-column>

    <el-table-column
        prop="grade"
        label="年级">
    </el-table-column>
    <el-table-column
        prop="major"
        label="专业">
    </el-table-column>
      <el-table-column
              prop="fraction"
              label="分数">
      </el-table-column>
    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-input min="0" max="100" v-model="scope.row.fraction" placeholder="未打分" @blur="myblur($event,scope.$index)" type="number" maxlength="3"></el-input>
      </template></el-table-column>
  </el-table>
</div>
</template>

<script>
import {getRequest, postRequest} from "@/utils/api";

export default {
  name: "putPoint",
  data(){
    return{
      studentlist:[],
    }
  },
created() {
    console.log(this.$route.query.id)
getRequest('/class/students/?id='+this.$route.query.id).then(data => {
  if (data) {
    let students = [];
    let promiseList = data.map(student => {
      const {id,username,grade,major,name} = student;
      return getRequest('/cs/getfraction/?id=' + this.$route.query.id + '&sid=' + id).then(data => {
        let aclass = {
          id: id, username: username, grade: grade,
          major: major,name:name,
          fraction: data,
        };
        students.push(aclass);
      });
    });
    Promise.all(promiseList).then(() => {
      this.studentlist = students;
    });
  }
})
},
methods:{
  myblur(e,index){
      console.log(index)
postRequest('/class/modifystudentpoint/?uid='+this.studentlist[index].id+'&cid='+ this.$route.query.id +'&point='+e.target.value)
  }
}

}

</script>

<style scoped>

</style>