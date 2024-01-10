<template>
  <el-table
      :data="classlist"
      stripe
      height="100%"
      style="width: 100%">
    <el-table-column type="expand" >
      <template #default="props" >
        <el-table :data="props.row.students" >
          <el-table-column label="姓名" prop="name" />
          <el-table-column label="学院" prop="faculty" />
          <el-table-column label="专业" prop="major" />
          <el-table-column label="年级" prop="grade" />
          <el-table-column label="分数" prop="fraction" />
          <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
              <el-button
                  link
                  type="primary"
                  size="small"
                  @click.prevent="deletestu(props.$index,scope.$index)"
              >
                删除
              </el-button>
            </template></el-table-column>
        </el-table>
      </template>
    </el-table-column>
    <el-table-column
        prop="classid"
        label="课程编号"
        width="180">
    </el-table-column>
    <el-table-column
        prop="teachername"
        label="教师姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="point"
        label="学分">
    </el-table-column>
    <el-table-column
        prop="number"
        label="人数">
    </el-table-column>
    <el-table-column
        prop="time"
        label="上课时间">
    </el-table-column>
    <el-table-column
        prop="selected"
        label="选课人数">
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-button
            link
            type="primary"
            size="small"
            @click.prevent="deleteClass(scope.$index)"
        >
          删除
        </el-button>
        <el-button
            link
            type="primary"
            size="small"
            @click.prevent="changeClass(scope.$index)"
        >
          修改
        </el-button>

      </template></el-table-column>
  </el-table>
  <el-button
      link
      type="primary"
      size="small"
      @click.prevent="addClass()"
  >
    添加
  </el-button>
</template>

<script >
import {getRequest, postRequest} from "@/utils/api";

export default {
  data(){
    return{
      classlist:[],
    }
  },
  methods:{
    deleteClass(index){
      this.$confirm('是否删除课程？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        postRequest('/class/deleteclass/',this.classlist[index]).then(data => {
          if (data) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.classlist.splice(index,1);
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    changeClass(index){
      this.$router.push({path:'/changeClass',query:{type:'change',id:this.classlist[index].classid}});
    },
    addClass(){
      this.$router.push({path:'/changeClass',query:{type:'add'}});
    },
    deletestu(index,j){
      this.$confirm('是否删除学生？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        postRequest('/class/deletestu/',{cs:{studentid:this.classlist[index].students[j],classid:this.classlist[index].classid}}).then(data => {
          if (data) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.classlist.splice(index,1);
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },

  created() {
    getRequest('/class/getclass').then(data => {
      if (data) {
          this.classlist=data;
      }
      data.forEach(aclass=>{

        getRequest('/class/students/?id='+aclass.classid).then(data => {
          if (data) {
            aclass.students=data;
          }
        })}
      )
    });
  }
}
</script>

<style >

</style>