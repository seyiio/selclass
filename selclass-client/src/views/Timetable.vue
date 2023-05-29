<template>
  <div class="kbBox">
    <el-table
        :data="classList"
        border
        style="width: 100%"
        height="309"
        class="courseForm"
        :header-cell-style="{ background: '#5D87EB', color: '#fff' }"
    >
      <el-table-column
          prop="Course"
          label="课时/节次"
      ></el-table-column>
      <el-table-column prop="one" label="周一"></el-table-column>
      <el-table-column prop="two" label="周二" ></el-table-column>
      <el-table-column prop="three" label="周三"></el-table-column>
      <el-table-column prop="four" label="周四" ></el-table-column>
      <el-table-column prop="five" label="周五"></el-table-column>
      <el-table-column prop="six" label="周六" ></el-table-column>
      <el-table-column prop="seven" label="周天"></el-table-column>
    </el-table>
    <div></div>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";
export default {
  name: "timeTable",

  created() {


    getRequest('/class/getselclass').then(data=>{
      if (data){
        data.forEach(selclass=>{
          let{classname,classid,point,teachername,time}=selclass;
          let aclass={
            classname:classname,
            classid:classid,
            point:point,
            teachername:teachername,
            time:time
          }
         const selclasss= aclass.time.toString().split(' ');

          selclasss.forEach(item=>{

            if(item.toString().charAt(7)==='1')
            {this.classList[item.toString().charAt(9)-1].one=classname; }
            else if(item.toString().charAt(7) === '2') {
              this.classList[item.toString().charAt(9)-1].two=classname;
            } else if(item.toString().charAt(7) === '3') {
              this.classList[item.toString().charAt(9)-1].three=classname;
            } else if(item.toString().charAt(7) === '4') {
              this.classList[item.toString().charAt(9)-1].four=classname;
            } else if(item.toString().charAt(7) === '5') {
              this.classList[item.toString().charAt(9)-1].five=classname;
            }else if(item.toString().charAt(7) === '6') {
              this.classList[item.toString().charAt(9)-1].six=classname;
            }else if(item.toString().charAt(7) === '7') {
              this.classList[item.toString().charAt(9)-1].seven=classname;
            }

          })
        });

      }
    })
  },
  data(){
    return{
      classList:[ {
      Course: "第一节",
      one: "",
      two: "",
      three: "",
      four: "",
      five: "",  six: "",
        seven: ""
    },
      {
        Course: "第二节",
        one: "",
        two: "",
        three: "",
        four: "",
        five: "",
        six: "",
        seven: ""
      },
        {
          Course: "第三节",
          one: "",
          two: "",
          three: "",
          four: "",
          five: "",
          six: "",
          seven: ""
        },
        {
          Course: "第四节",
          one: "",
          two: "",
          three: "",
          four: "",
          five: "",
          six: "",
          seven: ""
        },
        {
          Course: "第五节",
          one: "",
          two: "",
          three: "",
          four: "",
          five: "",
          six: "",
          seven: ""
        },

      ]}
  }
}
</script>

<style scoped>

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  border: 1px solid black;
  text-align: center;
  min-height: 36px;
}
</style>