<template>
    <el-table
            :data="classlist"
            stripe
            height="309"
            style="width: 100%">
        <el-table-column
                prop="classname"
                label="课程名称"
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
            prop="fraction"
            label="成绩">
        </el-table-column>
    </el-table>
</template>

<script>
import {getRequest} from "@/utils/api";
import store from "@/store/index";

export default {
  name: "classPoint",
    created() {
        getRequest('/class/getselclass').then(data => {
            if (data) {
                let selclasss = [];
                let promiseList = data.map(selclass => {
                    const {classname, classid, point, teachername, time} = selclass;
                    return getRequest('/cs/getfraction/?id=' + classid + '&sid=' + store.state.id).then(data => {
                        let aclass = {
                            classname: classname, classid: classid, point: point,
                            teachername: teachername, time: time,
                            fraction: data,
                        };
                        selclasss.push(aclass);
                    });
                });
                Promise.all(promiseList).then(() => {
                    this.classlist = selclasss;
                });
            }
        });
    },
    data(){
        return {

                classlist:[],

        }
    }
    }
</script>

<style scoped>

</style>