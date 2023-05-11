<template><div>
    <el-table
            :data="classlist"
            stripe
            style="width: 100%">
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
        <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
                <el-button
                        link
                        type="primary"
                        size="small"
                        @click.prevent="selectClass(scope.$index)"
                >
                    打分
                </el-button>

            </template></el-table-column>
    </el-table>
    </div>
</template>
<script>
import {getRequest} from "@/utils/api";


export default {
  name: "aClass",
    created() {
        getRequest('/class/getselclass').then(data => {
            if (data) {
                let selclasss = []
                data.forEach(selclass=>{
                    let{classname,classid,point,teachername,time}=selclass;
                    let aclass={
                        classname:classname,
                        classid:classid,
                        point:point,
                        teachername:teachername,
                        time:time
                    } ; selclasss.push(aclass)},
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
        }

    }
}
</script>

<style scoped>

</style>