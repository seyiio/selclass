<template>
    <el-form ref="form" :model="form" label-width="80px" >
      <el-form-item label="课程名称"  >
        <el-input v-model="form.classname"></el-input>
      </el-form-item>
      <el-form-item label="学分" :rules="rules">
        <el-input v-model="form.point"></el-input>
      </el-form-item>
      <el-form-item label="授课教师" :rules="rules">
        <el-input v-model="form.teachername"></el-input>
      </el-form-item>
      <el-form-item label="人数" :rules="rules">
        <el-input v-model="form.number" ></el-input>
      </el-form-item>

      <el-form-item label="时间" :rules="rules">
        <el-input v-model="form.time" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button >
          <router-link to="/classManage">取消</router-link></el-button>
      </el-form-item>
  </el-form>
</template>
<script >
import {postRequest} from "@/utils/api";

export default{
  name:"changeUser",
  data(){
    return{
      form:
          {
              classname: '',
              point: 0,
              number: 0,
              time: '',
              teachername: '',
          },
      rules:{
        required: true, message: '不能为空', trigger: 'blur'
      }
    }

  },
  created() {

    if(this.$route.query.type==='change'){
      postRequest('/class/getaclass',{id:this.$route.query.id}).then(data=>{
        this.form=data;
      })
    }


  },
  methods:{
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$confirm('是否提交？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

            postRequest('/class/changeclass',this.form).then(data=>{
              if (data.code==200){
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                });
                this.$router.push('/classManage')
              }
              else{
                this.$message({
                  type: 'error',
                  message: '修改失败!'
                });
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
            this.$router.push('/classManage')
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>
<style scoped>

</style>