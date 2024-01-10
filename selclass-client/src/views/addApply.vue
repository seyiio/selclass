<template>
  <el-form ref="form" :model="form" label-width="80px" >
    <el-form-item label="课程名称" :rules="rules">
      <el-input v-model="form.classname"></el-input>
    </el-form-item>
    <el-form-item label="学分" :rules="rules">
      <el-input v-model="form.point"></el-input>
    </el-form-item>
    <el-form-item label="人数" :rules="rules">
      <el-input v-model="form.number"></el-input>
    </el-form-item>
    <el-form-item label="上课时间 " :rules="rules">

      <el-input v-model="form.time" ></el-input>
    </el-form-item>
    <el-form-item label="任课老师" :rules="rules">
      <el-input v-model="form.teachername" disabled></el-input>
    </el-form-item>
    <el-form-item label="申请原因" :rules="rules">
      <el-input v-model="form.reason"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">提交</el-button>
      <el-button >
        <router-link to="/aClass">取消</router-link></el-button>
    </el-form-item>
  </el-form>
</template>
<script >
import store from "@/store";
import {postRequest} from "@/utils/api";

export default {
  computed: {
    store() {
      return store
    }
  },
  data() {
    return {
      form: {
        type:2,
        classname: '',
        teacher: store.state.id,
        point: 0,
        number: 0,
        time: '第1-16周周3第2节',
        teachername: store.state.name,
        reason: '',
      },
    rules:{
    required: true, message: '不能为空', trigger: 'blur'
  }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$confirm('是否提交申请？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            postRequest('/apply/addApply', this.form).then(resp => {
              if (resp.code===200) {
                this.$message({
                  type: 'success',
                  message: '提交成功!'
                });
              }
            })

            this.$router.push('/aClass')
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消提交'
            });
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>
<style scoped>

</style>