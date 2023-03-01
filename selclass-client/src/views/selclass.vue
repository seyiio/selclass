<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="classname " label="课程名称" width="180" />
    <el-table-column prop="point" label="学分" width="180" />
    <el-table-column prop="address" label="任课老师" />
    <el-table-column prop="number" label="剩余数量" />
  </el-table>
</template>

<script>
export default {

  name: "seClass",
  data() {
    return {
      tableData: []
    }
}}
</script>

<style scoped>

</style>