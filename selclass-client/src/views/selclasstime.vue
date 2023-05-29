<template>
  <div class="block">
    <span class="demonstration">Default</span>
    <el-form :inline="true" ref="Form"
             height="309"
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
    <el-button type="primary" class="button" @click="submit">修改</el-button> </el-form-item>
    </el-form>
  </div>

</template>

<script>

import {getRequest, postRequest} from "@/utils/api";

export default {
  name: "selClassTime",
created() {
getRequest('/time/selclasstime').then(data=>{
  this.form.time=[new Date(data.starttime),new Date(data.endtime)]
})
},
  data(){
    return {

      form: {
        time: [new Date(),new Date()],
      }
    }
  },
  methods:{
    submit(){this.$refs.Form.validate((valid)=>{
      if (valid) {

        postRequest('/time/changeTime', this.form).then(resp => {
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