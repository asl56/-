<template>
    <div class="UserManage">
        <div style="margin-bottom: 20px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item style="font-weight: bold;">用户管理</el-breadcrumb-item>
                <el-breadcrumb-item>用户信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="UserManage_box">
            <div class="UserManage_Search">
                <el-form :inline="true" :model="formInline" style="margin: 0px auto;">
                    <el-form-item label="用户名">
                        <el-input v-model="formInline.userName" placeholder="支持模糊查询"></el-input>
                    </el-form-item>

                    <el-form-item label="姓名" style="margin-left: 20px;">
                        <el-input v-model="formInline.name" placeholder="支持模糊查询"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="formInline.status" placeholder="状态">
                            <el-option :label="item.status" :value="item.id" v-for="(item, index) in statusList"
                                :key="index"></el-option>

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
                    <el-button type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
                </div>
                <el-table :data="tableData" style="width: 90%;border-radius: 5px;margin: 0 auto;">
                    <el-table-column prop="name" label="头像" width="120">
                        <template slot-scope="scope">
                            <el-avatar :src="scope.row.avatar"></el-avatar>
                        </template>

                    </el-table-column>
                    <el-table-column prop="userName" label="用户名" width="120"
                        style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                    </el-table-column>
                    <el-table-column prop="name" label="姓名" width="120">
                    </el-table-column>
                    <el-table-column prop="phone" label="联系方式">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱">
                    </el-table-column>
                    <el-table-column prop="status" label="状态">
                        <template slot-scope="scope">
                            <el-tag type="success" v-if="scope.row.status == '启用'">{{ scope.row.status }}</el-tag>
                            <el-tag type="danger" v-else>{{ scope.row.status }}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                                icon-color="red" title="确定要停用该账户吗？" @confirm="handleDelete(scope.row)"
                                v-if="scope.row.status == '启用'">
                                <el-link :underline="false" icon="el-icon-turn-off" type="danger" slot="reference">{{
                    scope.row.status == '启用' ? '停用' : '启用' }}</el-link>
                            </el-popconfirm>

                            <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                                icon-color="red" title="确定要启用该账户吗？" @confirm="handleDelete(scope.row)" v-else>
                                <el-link :underline="false" icon="el-icon-open" type="primary" slot="reference">{{
                    scope.row.status == '启用' ? '停用' : '启用' }}</el-link>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="page.count"
                    layout="total, sizes, prev, pager, next, jumper" :total="total" class="UserManage_page">
                </el-pagination>
            </div>
            <el-dialog title="添加用户" :visible.sync="dialogFormVisible" width="500px" @close="handleClose">
                <el-form :model="addForm" :rules="rules" ref="addForm">
                    <el-form-item label="头像" :label-width="formLabelWidth" prop="avatar">
                        <el-upload class="avatar-uploader" action="/main/upload" :show-file-list="false"
                            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                            <img v-if="imageUrl" :src="imageUrl" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"
                                style="display: flex;justify-content: center;align-items: center;"></i>
                        </el-upload>
                    </el-form-item>

                    <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName">
                        <el-input v-model="addForm.userName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                        <el-input v-model="addForm.password" type="password" autocomplete="off"
                            show-password></el-input>
                    </el-form-item>
                    <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                        <el-input v-model="addForm.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="身份" :label-width="formLabelWidth" prop="role">
                        <el-input v-model="addForm.role" autocomplete="off" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" :label-width="formLabelWidth" prop="phone">
                        <el-input v-model="addForm.phone" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                        <el-input v-model="addForm.email" autocomplete="off"></el-input>
                    </el-form-item>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button>取 消</el-button>
                    <el-button type="primary" @click="handleAddUser">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            //表单验证规则
            rules: {
                userName: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ],
                phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }, { pattern: /^1[345789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }],
                email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }, { pattern: /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/, message: '邮箱格式不正确', trigger: 'blur' }],
                avatar: [
                    { required: true, message: '请上传头像', trigger: 'blur' }
                ],
                role: [
                    { required: true, message: '请填写身份', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
            },

            dialogFormVisible: false, //添加用户弹窗
            statusList:[],//状态下拉列表
            
            id: '',//当前登录用户id
            //查询条件
            formInline: {
                userName: '',
                name: '',
                status:'',
            },
            //创建人下拉框
            nameList: [],
            
            currentPage: 1,//当前页

            addForm: {//添加用户
                avatar: '',
                userName: '',
                role: '用户',
                name: '',
                phone: '',
                email: '',
                password: '',
            },

            formLabelWidth: '120px',//文本宽度
            imageUrl: '',//图片路径
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
        //图片上传成功事件
        handleAvatarSuccess(res, file) {
            this.imageUrl = `/main/download?name=${file.name}`;
            this.addForm.avatar = file.name;
        },
        //图片上传规则
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
            // this.noticeTitle = ''
        },
        //添加用户
        handleAddUser() {
            axios.post("/main/addUser", JSON.stringify(this.addForm), {
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
                this.getData();
                this.dialogFormVisible = false
            })
        },
        //删除公告
        handleDelete(row) {
            if (row.id == this.id) {
                this.$notify.error({
                    title: '错误',
                    message: '无法停用当前账户',
                    offset: 55,
                });
            } else {


                var id = 0;
                if (row.statusID == 5) {
                    id = 6;
                } else {
                    id = 5
                }
                const data = {
                    id: row.id,
                    statusID: id,
                }
                axios.post(`/main/editUser`, JSON.stringify(data), {
                    headers: {
                        "Content-Type": "application/json",
                    }
                }).then(res => {
                    if (id == 5)
                        this.$notify({
                            title: '成功',
                            message: '停用成功',
                            type: 'success',
                            offset: 55,
                        });
                    else
                        this.$notify({
                            title: '成功',
                            message: '启用成功',
                            type: 'success',
                            offset: 55,
                        });
                    this.getData();
                })
            }
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
            axios.get("/main/getUser", { params: { role: '用户', userName: this.formInline.userName, name: this.formInline.name,statusID:this.formInline.status,page:this.page.page,count:this.page.count} }).then(res => {
                this.total = res.data.data.total;
                this.tableData = res.data.data.rows
                for (let index = 0; index < this.tableData.length; index++) {
                    this.tableData[index].avatar = `/main/download?name=${this.tableData[index].avatar}`;
                }
                if (this.tableData.length < 1 && this.page.page > 1) {
                    this.page.page -= 1;
                    this.getData()
                    return;
                }
            })
            axios.get("/main/getUserStatus").then(res => {
                this.statusList=res.data.data
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


.UserManage_box {
    width: 100%;
    height: 92%;
    padding: 15px 0 15px 0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.UserManage_page {
    margin-top: 15px;
    margin: 20px;
}

.UserManage_Search {
    margin-top: 20px;
    height: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>