<template>
  <el-table :data="tableData"
            height="100%"
            style="width: 100%" v-if="time" ref="table">
    <el-table-column prop="classname" label="课程名称" width="180" />
    <el-table-column prop="point" label="学分" width="180" />
    <el-table-column prop="time" label="时间" width="168"/>
    <el-table-column prop="teachername" label="任课老师" />
    <el-table-column prop="value" label="剩余数量" />
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
        <el-button
            link
            type="primary"
            size="small"
            @click.prevent="deleteClass(scope.$index)"
        >
          退课
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
import store from '@/store/index'
export default {
created() {

  getRequest('/time/selclasstime?id=1').then(data=>{
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
              selected:selected,
              number:number,
              value:selected+'/'+number,
              time:time
            }
            selclasss.push(aclass)
          });
          this.tableData=selclasss;
        }
      })
    }else this.time=false;

  },
  )
},
  name: "seClass",
  data() {
    return {
      tableData: [],
      time:false,

    }
},
methods:{
  selectClass(s){
    let a={classid:this.tableData[s].classid,studentid:store.state.id};
   postRequest("/cs/selectclass",a).then(data=>{
     if (data.code===200){
        this.tableData[s].selected++;
        this.tableData[s].value=this.tableData[s].selected+'/'+this.tableData[s].number
     }
    })
  },
  deleteClass(s){
    let a={classid:this.tableData[s].classid,studentid:store.state.id};
    postRequest("/cs/deleteclass",a).then(data=>{
      if (data.code===200){
        this.tableData[s].selected--;
        this.tableData[s].value=this.tableData[s].selected+'/'+this.tableData[s].number
      }
    });
  }

}

}
</script>

<style scoped>

</style>