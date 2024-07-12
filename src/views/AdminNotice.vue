<template>
  <div class="AdminNotice">
    <div style="margin-bottom: 20px;">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="font-weight: bold;">信息管理</el-breadcrumb-item>
        <el-breadcrumb-item>公告信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="AdminNotice_box">
      <div class="AdminNotice_Search">
        <el-form :inline="true" :model="formInline" style="margin: 0px auto;">
          <el-form-item label="标题">
            <el-input v-model="formInline.title" placeholder="支持模糊查询"></el-input>
          </el-form-item>
          <el-form-item label="日期">
            <el-date-picker v-model="formInline.date" value-format="yyyy-MM-dd" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="创建人">
            <el-select v-model="formInline.name" placeholder="创建人">
              <el-option :label="item.name" :value="item.id" v-for="(item, index) in nameList" :key="index"></el-option>

            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="getData">查询</el-button>
            <el-button type="info" @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <div style="text-align: right;width: 90%;margin: 0 auto;">
          <el-button type="primary" icon="el-icon-plus" circle @click="dialogVisible = true"></el-button>
        </div>
        <el-table :data="tableData" style="width: 90%;border-radius: 5px;margin: 0 auto;">
          <el-table-column prop="title" label="公告标题" width="300"
            style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
          </el-table-column>
          <el-table-column prop="releaseDate" label="创建时间" width="300">
          </el-table-column>
          <el-table-column prop="name" label="创建人">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red"
                title="确定要删除该条公告吗？" @confirm="handleDelete(scope.row)">
                <el-link :underline="false" icon="el-icon-delete" type="danger" slot="reference">删除</el-link>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]" :page-size="page.count" layout="total, sizes, prev, pager, next, jumper"
          :total="total" class="AdminNotice_page">
        </el-pagination>
      </div>
      <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" @close="handleClose">
        <el-form label-width="80px">
          <el-form-item label="公告">
            <el-input type="textarea" v-model="noticeTitle"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAddNotice()">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      //添加标题文本框
      noticeTitle: '',

      dialogVisible: false,

      id: '',
      //查询条件
      formInline: {
        title: '',
        name: '',
        date: '',
      },
      //创建人下拉框
      nameList: [],
      currentPage: 1,

      total: 0,
      // 分页
      page: {
        page: 1,
        count: 5,
      },
      // 表格数据
      tableData: []
    }
  },
  created() {
    this.id = localStorage.getItem("userID");
  },
  mounted() {
    this.getData()
  },
  methods: {
    // 重置按钮事件
    handleReset() {
      this.formInline = {
        title: '',
        name: '',
        date: '',
      };
      this.page = {
        page: 1,
        count: 10,
      };
      this.getData()
    },
    // 弹窗关闭事件
    handleClose() {
      this.noticeTitle = ''
    },
    //添加公告
    handleAddNotice() {
      axios.post(`/main/addNotice?userID=${this.id}&title=${this.noticeTitle}`, { userID: this.id, title: this.noticeTitle }).then(res => {
        this.$notify({
          title: '成功',
          message: '添加成功',
          type: 'success',
          offset: 55,
        });
        this.getData();
        this.dialogVisible = false
      })
    },
    //删除公告
    handleDelete(row) {
      axios.get("/main/deleteNotice", { params: { id: row.id } }).then(res => {
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          offset: 55,
        });
        this.getData();
      })
    },
    handleSizeChange(val) { //分页
      this.page.count = val;
      this.getData();
    },
    handleCurrentChange(val) {//分页
      this.page.page = val
      this.getData();
    },
    getData() {//初始化数据
      axios.get("/main/getNotice", { params: { page: this.page.page, count: this.page.count, title: this.formInline.title, releaseDate: this.formInline.date, userID: this.formInline.name } }).then(res => {
        this.total = res.data.data.total;
        this.tableData = res.data.data.rows
        if (this.tableData.length < 1 && this.page.page > 1) {
          this.page.page -= 1;
          this.getData()
          return;
        }
      });
      if (this.nameList.length > 0)
        this.nameList = [];
      axios.get("/main/getUser", { params: { role: '管理员' } }).then(res => {
        for (let index = 0; index < res.data.data.rows.length; index++) {
          this.nameList.push(res.data.data.rows[index]);
        }
      })
    }
  },
}
</script>
<style>
* {
  margin: 0;
  padding: 0;
}


.AdminNotice_box {
  width: 100%;
  height: 92%;
  padding: 15px 0 15px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.AdminNotice_page {
  margin-top: 15px;
  margin: 20px;
}

.AdminNotice_Search {
  margin-top: 20px;
  height: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
