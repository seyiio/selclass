<template>
    <el-table
            :data="applylist"

            :row-class-name="tableRowClassName">
            height="500"
            style="width: 100%">
        <el-table-column
                prop="type"
                label="操作"
                width="180">
        </el-table-column>
        <el-table-column
                prop="classname"
                label="课程名称"
                width="180">
        </el-table-column>
        <el-table-column
                prop="teachername"
                label="教师"
                width="180">
        </el-table-column>
        <el-table-column
                prop="point"
                label="学分"
                width="180">
        </el-table-column>
        <el-table-column
                prop="number"
                label="人数"
                width="180">
        </el-table-column>
        <el-table-column
                prop="time"
                label="上课时间"
                width="180">
        </el-table-column>
        <el-table-column
                prop="reason"
                label="申请原因"
                width="180">
        </el-table-column>
        <el-table-column
                prop="applytime"
                label="申请时间"
                width="180">
        </el-table-column>
        <el-table-column
                prop="endtime"
                label="截止时间"
                width="180">
        </el-table-column>
        <el-table-column
                prop="status"
                label="状态"
                width="180">
        </el-table-column>
        <el-table-column
                prop="dealtime"
                label="处理时间"
                width="180">
        </el-table-column>

        <el-table-column
                prop="dealperson"
                label="处理人"
                width="180">
        </el-table-column>
      <el-table-column
          prop="dealreason"
          label="审批建议"
          width="180">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="300" v-if="store.state.type==0">
        <template #default="scope">
          <el-button
              link
              type="primary"
              size="small"
              @click.prevent="permit(scope.$index)" :disabled="scope.row.status!=='未处理'"
          >
            批准
          </el-button>
          <el-button
              link
              type="primary"
              size="small"
              @click.prevent="refuse(scope.$index)" :disabled="scope.row.status!=='未处理'"
          >
            拒绝
          </el-button>

        </template></el-table-column>

</el-table>
</template>

<script>


import {getRequest, postRequest} from "@/utils/api";
import store from "@/store";

export default {
    name: "ApplyTable",
  computed: {
    store() {
      return store
    }
  },
    data(){
        return {
            applylist:[],
        }
    },
    created() {

        getRequest("/apply/getApplyList").then(resp => {
            if (resp) {
                this.applylist = resp.obj;
                this.applylist.forEach(apply=>{
                    if(apply.type==1){
                        apply.type="申请关闭课程";
                    }else if(apply.type==2){
                        apply.type="申请开放课程";
                    }
                    if(apply.status==0){
                        apply.status="未处理";
                    }else if(apply.status==1){
                        apply.status="已通过";
                    }else if(apply.status==2){
                        apply.status="未通过";
                    }
                    if(apply.dealperson==null){
                        apply.dealperson="无";
                    }
                    if (apply.dealtime==null){
                        apply.dealtime="无";
                    }
                } )
            }
        })

    },
  methods:{
    permit(index){
      this.$prompt('请输入建议', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',

      }).then(({ value }) => {
        let apply=this.applylist[index];
        postRequest("/apply/permit",{id:apply.id,dealreason:value})}).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });

    },
    refuse(index){

      this.$prompt('请输入建议', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',

      }).then(({ value }) => {
        let apply=this.applylist[index];
        postRequest("/apply/refuse",{id:apply.id,dealreason:value})
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    tableRowClassName(row) {

      if ( row.row.status === '未通过' ) {

        return 'warning-row';
      } else if ( row.row.status === '已通过') {
        return 'success-row';
      }else if ( row.row.status === '未处理') {

        return 'normal-row';
      }

      return '';
    }
  }

}
</script>

<style >

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
.el-table .normal-row {
  background: #c9dfff;
}
</style>