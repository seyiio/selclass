<template><div>
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
                </el-table>

            </template>
        </el-table-column>
        <el-table-column
                prop="classname"
                label="课程名称"
                width="180">
        </el-table-column>
        <el-table-column
                prop="point"
                label="学分">
        </el-table-column>
        <el-table-column
                prop="time"
                label="时间">
        </el-table-column>
        <el-table-column
            prop="selected"
            label="人数">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="300">
            <template #default="scope">
                <el-button
                        link
                        type="primary"
                        size="small"
                        @click.prevent="selectClass(scope.$index)"
                >
                    打分
                </el-button>
                <el-button
                    link
                    type="primary"
                    size="small"
                    @click.prevent="closeclass(scope.$index)"
                >
                    申请关闭课程
                </el-button>

            </template></el-table-column>
    </el-table>
  <el-button type="primary">
    <router-link to="/addApply">添加申请</router-link>
  </el-button>
    </div>
</template>
<script>
import {getRequest, postRequest} from "@/utils/api";


export default {
  name: "aClass",
    created() {
        getRequest('/class/getselclass').then(data => {
            if (data) {
                let selclasss = []
                data.forEach(selclass=>{
                    let{classname,classid,point,teachername,time,selected}=selclass;
                    let aclass={
                        classname:classname,
                        classid:classid,
                        point:point,
                        selected:selected,
                        teachername:teachername,
                        time:time,
                        students:[]
                    } ;
                    getRequest('/class/students/?id='+classid).then(data=>{
                        if (data){
                            let students = []
                            data.forEach(student=>{
                                let{name,faculty,major,grade}=student;
                                let astudent={
                                    name:name,
                                    faculty:faculty,
                                    major:major,
                                    grade:grade,
                                } ; students.push(astudent)},
                            );aclass.students=students;
                        }
                    })

                    selclasss.push(aclass)},
                );this.classlist=selclasss;

            }
        })
    }
,
    data(){
        return {
            classlist:[],
        }
    },
    methods:{
        selectClass(s){
            this.$router.push({path:'/point',query:{id:this.classlist[s].classid}
            })
        },
        closeclass(s){
            this.$prompt('请输入理由', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',

            }).then(({ value }) => {
                postRequest('/apply/delclass/',{classid:this.classlist[s].classid,reason:value}).then(data=>{
                    if (data){
                        this.$message({
                            type: 'success',
                            message: '提交成功'
                        });
                    }
                })

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });
            });
        }


    }
}
</script>

<style scoped>

</style>