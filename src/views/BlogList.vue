<template>
    <div class="BlogList">
    <v-app>
        <bar></bar>
        <v-container>
        <v-toolbar flat style="margin-top:30px">
        <v-toolbar-title>个人日志列表</v-toolbar-title>

        <v-spacer></v-spacer>
        </v-toolbar>
        <template>
        <el-table
            :data="tableData"
            stripe
            style="width: 100%"
            :default-sort = "{prop: 'recentFinishTime', order: 'descending'}">
            <el-table-column
            fixed
            sortable
            prop="blogTitle"
            label="日志名称"
            style="width: 35%">
            </el-table-column>
            <el-table-column
            sortable
            prop="recentFinishTime"
            label="日期"
            style="width: 30%">
            </el-table-column>
        </el-table>
        </template>
        </v-container>
    </v-app>
    </div>
</template>

<script>
import bar from "../components/Bar.vue"

export default({
  inject: ["reload"],
    data() {
      return {
        tableData: [
          {
            blogID: 1, // 任意ID
            blogTitle: "来到墨韵",
            recentFinishTime: "2025-06-02" // 当前日期
          }
        ],
      }
    },

    components:{
    bar,
    },

    methods: {
      show() {
        this.$http.get('http://localhost:3000/api/blogs', { withCredentials: true })
          .then(response => {
            this.tableData = response.data.map(blog => ({
              blogID: blog.id,
              blogTitle: blog.title,
              recentFinishTime: blog.created_at
            }));
          })
          .catch(error => {
            console.error('获取日志列表失败:', error);
          });
      },
    },

})
</script>

<style scoped>
.BlogList{
    background:url("../assets/widthPic.jpg");
    width:100%;
    height:100%;
    background-size:100% 100%;};

</style>
