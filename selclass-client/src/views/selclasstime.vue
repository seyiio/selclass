<template>
  <div class="block">
    <span class="demonstration">选课时间</span>
    <el-form :inline="true" ref="Form"
             height="100%"
             :model="form" >
      <el-form-item label="Approved by">
    <el-date-picker
        v-model="form.time"
        type="datetimerange"
        range-separator="To"
        start-placeholder="Start date"
        end-placeholder="End date"
    />
      </el-form-item>
      <el-form-item>
    <el-button type="primary" class="button" @click="submit(form)">修改</el-button> </el-form-item>
    </el-form>
  </div>
  <div class="block">
    <span class="demonstration">成绩录入时间</span>
    <el-form :inline="true" ref="Form"
             height="100%"
             :model="form1" >
      <el-form-item label="Approved by">
        <el-date-picker
            v-model="form1.time"
            type="datetimerange"
            range-separator="To"
            start-placeholder="Start date"
            end-placeholder="End date"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="button" @click="submit(form1)">修改</el-button> </el-form-item>
    </el-form>
  </div>

</template>

<script>

import {getRequest, postRequest} from "@/utils/api";

export default {
  name: "selClassTime",
created() {
getRequest('/time/selclasstime?id=1').then(data=>{
  this.form.time=[new Date(data.starttime),new Date(data.endtime)]
})
  getRequest('/time/selclasstime?id=2').then(data=>{
    this.form1.time=[new Date(data.starttime),new Date(data.endtime)]
  })
},
  data(){
    return {

      form: {
        time: [new Date(),new Date()],
        id:1
      },
      form1: {
        time: [new Date(),new Date()],
        id:2
      }
    }
  },
  methods:{
    submit(f){this.$refs.Form.validate((valid)=>{
      if (valid) {

        postRequest('/time/changeTime', f).then(resp => {
          if (resp) {
            alert('submit!')
          }
    })
  }
    })
    }

  }
}


</script>

<style scoped>
.block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.block:last-child {
  border-right: none;
}
.block .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
.block .button {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-top: 20px;
  margin-left: 50%;
}
</style>