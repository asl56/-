<template>
  <div class="AdminFound">
    <div style="margin-bottom: 20px;">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item style="font-weight: bold;">信息管理</el-breadcrumb-item>
        <el-breadcrumb-item>招领信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="AdminFound_box">
      <div class="AdminFound_Search">
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
          <el-table-column prop="status" sortable label="状态" width="150">
          </el-table-column>
          <el-table-column label="操作" fixed="right" width="250px">
            <template slot-scope="scope">
              <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red"
                title="确定要删除该条失物信息吗？" @confirm="handleDelete(scope.row)">
                <el-link :underline="false" icon="el-icon-delete" type="danger" slot="reference">删除</el-link>
              </el-popconfirm>
              <el-link icon="el-icon-view" :underline="false" type="primary" style="margin:0 20px;"
                @click="handelView(scope.row)">详情</el-link>
              <el-link :underline="false" icon="el-icon-edit" type="primary" slot="reference"
                @click="showEdit(scope.row)">修改</el-link>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]" :page-size="page.count" layout="total, sizes, prev, pager, next, jumper"
          :total="total" class="AdminFound_page">
        </el-pagination>
      </div>

    </div>
    <el-dialog title="添加失物" :visible.sync="dialogFormVisible" width="500px" @close="handleClose">
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
        <el-button type="primary" @click="addLost('addForm')">确 定</el-button>
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
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="handleClose">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改失物信息" :visible.sync="dialogUpdateForm" width="500px" @close="handleClose">
      <el-form :model="updateForm" :rules="rules" ref="updateForm" style="margin: 0;">
        <el-form-item label="物品图片" :label-width="formLabelWidth" prop="ItemPhoto">
          <el-upload class="avatar-uploader" action="/main/upload" :show-file-list="false"
            :on-success="handleUpdateSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"
              style="display: flex;justify-content: center;align-items: center;"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
          <el-input v-model="updateForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth" prop="description">
          <el-input v-model="updateForm.description" type="textarea" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="updateForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth" prop="statusID">
          <el-select v-model="updateForm.statusID" placeholder="请选择" style="width: 340px;">
            <el-option v-for="(item, index) in statusList" :key="index" :label="item.status" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="editLost('updateForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      updateForm: {

      },
      dialogShowView: false,
      dialogUpdateForm: false,
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
      dialogVisible: false,
      updateForm: {},
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        itemPhoto: [
          { required: true, message: '请上传物品图片', trigger: 'blur' }
        ],
        phone: [{ required: true, message: '请输入联系方式', trigger: 'blur' }, { pattern: /^1[34578]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }],
        description: [{ required: true, message: '请输入物品描述', trigger: 'blur' }, { min: 1, max: 60, message: '长度在 1 到 60 个字符', trigger: 'blur' }],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        checkPassword: [
          { required: true, message: '请输入确认密码', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      id: '',
      formInline: {
        title: '',
        name: '',
        date: '',
      },
      nameList: [],
      currentPage: 1,
      total: 0,
      page: {
        page: 1,
        count: 5,
      },
      tableData: [],
      statusList: [],
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
    editLost(updateForm) {
      this.$refs[updateForm].validate((valid) => {
        if (valid) {
          axios.post("/main/editFound", JSON.stringify(this.updateForm), {
            headers: {
              "Content-Type": "application/json",
            }
          }).then(res => {
            this.$notify({
              title: '成功',
              message: '修改成功',
              type: 'success',
              offset: 55,
            });
            this.dialogUpdateForm = false
            this.getData()
          })
        }
      })
    },
    showEdit(row) {
      axios.get("/main/getFound", { params: { id: row.id } }).then(res => {
        this.updateForm = res.data.data.rows[0];
        this.imageUrl = `/main/download?name=${this.updateForm.itemPhoto}`;
        this.dialogUpdateForm = true;
      })
    },
    handelView(row) {
      axios.get("/main/getFound", { params: { id: row.id } }).then(res => {
        this.showView = res.data.data.rows[0];
        this.showView.itemPhoto = `/main/download?name=${this.showView.itemPhoto}`;
        this.dialogShowView = true;
      })
    },
    addLost(addForm) {
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
    handleUpdateSuccess(res, file) {
      this.imageUrl = `/main/download?name=${file.name}`;
      this.updateForm.itemPhoto = file.name;

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
    },

    //删除物品
    handleDelete(row) {
      axios.get("/main/deleteFound", { params: { id: row.id } }).then(res => {
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
      axios.get("/main/getFound", { params: { page: this.page.page, count: this.page.count, title: this.formInline.title, releaseDate: this.formInline.date, userID: this.formInline.name } }).then(res => {
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
      if (this.nameList.length > 0)
        this.nameList = [];
      axios.get("/main/getUser").then(res => {
        if (this.nameList != null || this.nameList.length > 1)
          this.nameList = [];
        for (let index = 0; index < res.data.data.rows.length; index++) {
          this.nameList.push(res.data.data.rows[index]);
        }
      })
      axios.get("/main/getFoundStatus").then(res => {
        this.statusList = res.data.data
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


.AdminFound_box {
  width: 100%;
  height: 92%;
  padding: 15px 0 15px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.AdminFound_page {
  margin-top: 15px;
  margin: 20px;
}

.AdminFound_Search {
  margin-top: 20px;
  height: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
