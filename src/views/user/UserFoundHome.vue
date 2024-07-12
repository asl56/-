<template>
  <div class="UserFoundHome">
    <div style="margin-bottom: 20px;">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="font-weight: bold;">首页</el-breadcrumb-item>
        <el-breadcrumb-item>招领信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="UserFoundHome_box">
      <div class="UserFoundHome_Search">
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
          <!-- 添加按钮 -->
          <el-button type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
        </div>
        <el-table :data="tableData" style="width: 90%;border-radius: 5px;margin: 0 auto;">
          <el-table-column prop="title" label="物品照片" width="150"
            style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
            <template slot-scope="scope">
              <el-image style="width: 100px; height: 100px" :src="scope.row.itemPhoto" fit="cover"></el-image>
            </template>
          </el-table-column>
          <el-table-column prop="title" label="物品标题" width="250"
            style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
          </el-table-column>
          <el-table-column prop="releaseDate" label="创建时间" width="150">
          </el-table-column>
          <el-table-column prop="name" label="创建人" width="150">
          </el-table-column>
          <el-table-column prop="phone" label="联系方式" width="150">
          </el-table-column>
          <el-table-column prop="status" label="状态" width="150">
          </el-table-column>
          <el-table-column label="操作" fixed="right" width="250px">
            <template slot-scope="scope">

              <el-link icon="el-icon-view" :underline="false" type="primary" style="margin:0 20px;"
                @click="handelView(scope.row)">详情</el-link>
              <el-link icon="el-icon-edit" :underline="false" type="primary" style="margin:0 20px;"
                @click="showFoundContact(scope.row.id)">留言</el-link>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]" :page-size="page.count" layout="total, sizes, prev, pager, next, jumper"
          :total="total" class="UserFoundHome_page">
        </el-pagination>
      </div>

    </div>
    <el-dialog title="添加招领物品" :visible.sync="dialogFormVisible" width="500px" @close="handleClose">
      <el-form :model="addForm" :rules="rules" ref="addForm" style="margin: 0;">
        <el-form-item label="物品图片" :label-width="formLabelWidth" prop="itemPhoto">
          <el-upload class="avatar-uploader" action="/main/upload" :show-file-list="false"
            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"
              style="display: flex;justify-content: center;align-items: center;"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
          <el-input v-model="addForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth" prop="description">
          <el-input v-model="addForm.description" type="textarea" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="addForm.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="addFound('addForm')">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="详细信息" :visible.sync="dialogShowView" width="500px" @close="handleClose">
      <el-form :model="showView" style="margin: 0;">
        <el-form-item label="物品图片" :label-width="formLabelWidth">

          <img :src="showView.itemPhoto" class="avatar">

        </el-form-item>

        <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
          <el-input v-model="showView.title" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth" prop="description">
          <el-input v-model="showView.description" type="textarea" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="showView.phone" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="创建人" :label-width="formLabelWidth">
          <el-input v-model="showView.name" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="showView.releaseDate" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="showView.status" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">

        <el-button type="primary" @click="dialogShowView = false">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="留言" :visible.sync="FoundDialogFormVisible" @close="handleClose()">
      <el-form>
        <el-form-item label="内容" :label-width="formLabelWidth">
          <el-input v-model="content" type="textarea" autosize autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="FoundDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addFoundContact">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      dialogShowView: false,
      data: {
        userID: '',
        content: '',
        foundID: '',
      },
      addForm: {
        userID: '',
        title: '',
        description: '',
        phone: '',
        itemPhoto: '',
      },
      formLabelWidth: '120px',
      imageUrl: '',
      dialogFormVisible: false,

      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        itemPhoto: [
          { required: true, message: '请上传物品图片', trigger: 'blur' }
        ],
        phone: [{ required: true, message: '请输入联系方式', trigger: 'blur' }, { pattern: /^1[345789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }],
        description: [{ required: true, message: '请输入物品描述', trigger: 'blur' }, { min: 1, max: 60, message: '长度在 1 到 60 个字符', trigger: 'blur' }],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ],

      },
      id: '',
      formInline: {
        title: '',
        name: '',
        date: '',
      },
      content: '',
      nameList: [],
      currentPage: 1,
      total: 0,
      page: {
        page: 1,
        count: 5,
      },
      tableData: [],
      FoundDialogFormVisible: false,
      showView: {},
    }
  },
  created() {
    this.id = localStorage.getItem("userID");
    this.getData();
  },
  mounted() {

  },
  methods: {
    addFoundContact() {
      this.data.content = this.content
      axios.post("/main/addContact", JSON.stringify(this.data), {
        headers: { 'Content-Type': 'application/json' }
      }).then(res => {
        this.$notify({
          title: '成功',
          message: '留言成功',
          type: 'success',
          offset: 55,
        })
        this.FoundDialogFormVisible = false
      })
    },
    showFoundContact(id) {
      this.data = {
        foundID: id,
        userID: localStorage.getItem('userID'),
        content: this.content
      }
      this.FoundDialogFormVisible = true
    },

    handelView(row) {
      axios.get("/main/getFound", { params: { id: row.id } }).then(res => {      
        this.showView = res.data.data.rows[0];
        this.showView.itemPhoto = `/main/download?name=${this.showView.itemPhoto}`;
        this.dialogShowView = true;
      })
    },
    addFound(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.addForm.userID = this.id
          axios.post("/main/addFound", JSON.stringify(this.addForm), {
            headers: {
              "Content-Type": "application/json",
            }
          }).then(res => {
            this.$notify({
              title: '成功',
              message: '添加成功',
              type: 'success',
              offset: 55,
            });
            this.dialogFormVisible = false
            this.getData()
          })
        }
      })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = `/main/download?name=${file.name}`;
      this.addForm.itemPhoto = file.name;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    handleReset() {
      this.formInline = {
        title: '',
        name: '',
        date: '',
      };
      this.page = {
        page: 1,
        count: 5,
      };
      this.getData()
    },
    handleClose() {
      this.imageUrl = '';
      this.addForm = {
        title: '',
        description: '',
        phone: '',
        itemPhoto: '',
      };
      this.content=''
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
      axios.get("/main/getFound", { params: { page: this.page.page, count: this.page.count, title: this.formInline.title, releaseDate: this.formInline.date, userID: this.formInline.name, statusID: 4 } }).then(res => {
        this.total = res.data.data.total;
        this.tableData = res.data.data.rows
        if (this.tableData.length < 1 && this.page.page > 1) {
          this.page.page -= 1;
          this.getData()
          return;
        }
        for (let index = 0; index < this.tableData.length; index++) {
          this.tableData[index].itemPhoto = `/main/download?name=${this.tableData[index].itemPhoto}`;

        }
      });
      axios.get("/main/getUser").then(res => {
        if (this.nameList != null || this.nameList.length > 1)
          this.nameList = [];
        for (let index = 0; index < res.data.data.rows.length; index++) {
          this.nameList.push(res.data.data.rows[index]);
        }
      })
    }
  },
}
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}


.UserFoundHome_box {
  width: 100%;
  height: 92%;
  padding: 15px 0 15px 0;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.UserFoundHome_page {
  margin-top: 15px;
  margin: 20px;
}

.UserFoundHome_Search {
  margin-top: 20px;
  height: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>