<template>
  <el-table :data="tableData" style="width: 100%" v-if="time" ref="table">
    <el-table-column prop="classname" label="课程名称" width="180" />
    <el-table-column prop="point" label="学分" width="180" />
    <el-table-column prop="time" label="时间" width="168"/>
    <el-table-column prop="teachername" label="任课老师" />
    <el-table-column prop="selected" label="剩余数量" />
    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-button
            link
            type="primary"
            size="small"
            @click.prevent="selectClass(scope.$index)"
        >
          选课
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-result
      v-else
      icon="error"
      title="当前还不是选课时间"
      sub-title="请稍后再来"
  >
  </el-result>
</template>

<script>
import {getRequest} from "@/utils/api";
import {postRequest} from "@/utils/api";
export default {
created() {
  getRequest('/time/selclasstime').then(data=>{
    if(new Date(data.starttime).getTime()<=new Date().getTime()&&new Date(data.endtime).getTime()>=new Date().getTime()){
      this.time=true;
      getRequest("/class/getclass").then(data=>{
        if (data){
          let selclasss = []
          data.forEach(selclass=>{
            let{classname,classid,point,teachername,selected,number,time}=selclass;
            let aclass={
              classname:classname,
              classid:classid,
              point:point,
              teachername:teachername,
              selected:number-selected,
              time:time
            }
            selclasss.push(aclass)
          });
          this.tableData=selclasss;
        }
      })
    }else this.time=false;

  },
  getRequest('/admin/info').then(data=>{this.id=data.id;}))
},
  name: "seClass",
  data() {
    return {
      tableData: [],
      time:false,
      id:0,
    }
},
methods:{
  selectClass(s){
    let a={classid:this.tableData[s].classid,studentid:this.id};
    postRequest("/cs/selectclass",a);
  }
}
}
</script>

<style scoped>

</style>