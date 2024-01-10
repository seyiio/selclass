

<template>
<el-form>
  <el-form ref="form" :model="form" label-width="80px" >
    <el-form-item label="id"  >
      <el-input v-model="form.id" disabled></el-input>
    </el-form-item>
    <el-form-item label="学号" :rules="rules">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="职位或专业" :rules="rules">
      <el-input v-model="form.major"></el-input>
    </el-form-item>
    <el-form-item label="年级" :rules="rules">
      <el-input v-model="form.grade" ></el-input>
    </el-form-item>

    <el-form-item label="电话" :rules="rules">
      <el-input v-model="form.phone" ></el-input>
    </el-form-item>
    <el-form-item label="学院" :rules="rules">
      <el-input v-model="form.faculty" >

      </el-input>
    </el-form-item>
    <el-form-item label="类别" :rules="rules">
    <el-select v-model="form.type" placeholder="管理员">
      <el-option label="管理员" value="0"></el-option>
      <el-option label="教师" value="1"></el-option>
      <el-option label="学生" value="3"></el-option>
    </el-select>
    </el-form-item>
    <el-form-item label="姓名" :rules="rules">
    <el-input v-model="form.name"></el-input>
  </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">提交</el-button>
      <el-button >
        <router-link to="/userManage">取消</router-link></el-button>
    </el-form-item>
  </el-form>
</el-form>
</template>
<script >
import { postRequest} from "@/utils/api";

export default{
  name:"changeUser",
  data(){
    return{
      form:
        {
          id:null,
          username:'',
          major:'',
          grade:'',
          phone:'',
          faculty:'',
          type:'',
          name:'',
        },
      rules:{
        required: true, message: '不能为空', trigger: 'blur'
      }
      }

    },
  created() {

    if(this.$route.query.type==='change'){
     postRequest('/user/getuser',{data:this.$route.query.id}).then(data=>{
       console.log(data);
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

            postRequest('/user/changeuser',this.form).then(data=>{
              if (data.code==200){
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                });
                this.$router.push('/userManage')
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
            this.$router.push('/userManage')
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