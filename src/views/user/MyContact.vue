<template>
    <div class="MyContact">
        <div style="margin-bottom: 20px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item style="font-weight: bold;">首页</el-breadcrumb-item>
                <el-breadcrumb-item>我的</el-breadcrumb-item>
                <el-breadcrumb-item>我的联系</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="MyContact_box">
            <div class="MyContact_Search">
                <el-form :inline="true" :model="formInline" style="margin: 0px auto;">
                    <el-form-item label="日期" style="margin-left: 20px;">
                        <el-date-picker v-model="formInline.date" value-format="yyyy-MM-dd" type="date"
                            placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="handleClick">查询</el-button>
                        <el-button type="info" @click="handleReset">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 95%;margin: 0 auto;">
                <el-tab-pane label="失物留言" name="first">
                    <div>
                        <el-table :data="tableData" style="width: 90%;border-radius: 5px;margin: 20px auto;">
                            <el-table-column prop="title" label="失物照片" width="150"
                                style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                                <template slot-scope="scope">
                                    <el-image style="width: 100px; height: 100px" :src="scope.row.itemPhoto"
                                        fit="cover"></el-image>
                                </template>
                            </el-table-column>
                            <el-table-column prop="title" label="失物标题" width="170"
                                style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                            </el-table-column>
                            <el-table-column prop="name" label="创建人" width="120">
                            </el-table-column>
                            <el-table-column prop="phone" label="创建人联系方式" width="150">
                            </el-table-column>
                            <el-table-column prop="status" label="当前状态" width="150">
                            </el-table-column>
                            <el-table-column prop="content" label="联系内容" width="150">
                            </el-table-column>
                            <el-table-column prop="contactTime" label="联系时间" width="150">
                            </el-table-column>
                            <el-table-column label="操作" fixed="right" width="150">
                                <template slot-scope="scope">
                                    <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                                        icon-color="red" title="确定要删除该条留言吗？" @confirm="handleDelete(scope.row)">
                                        <el-link :underline="false" icon="el-icon-delete" type="danger"
                                            slot="reference">删除</el-link>
                                    </el-popconfirm>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                            :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="page.count"
                            layout="total, sizes, prev, pager, next, jumper" :total="total" class="MyContact_page">
                        </el-pagination>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="招领留言" name="second">
                    <div>
                        <el-table :data="tableData" style="width: 90%;border-radius: 5px;margin: 20px auto;">
                            <el-table-column prop="title" label="招领照片" width="150"
                                style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                                <template slot-scope="scope">
                                    <el-image style="width: 100px; height: 100px" :src="scope.row.itemPhoto"
                                        fit="cover"></el-image>
                                </template>
                            </el-table-column>
                            <el-table-column prop="title" label="招领标题" width="170"
                                style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                            </el-table-column>
                            <el-table-column prop="name" label="创建人" width="120">
                            </el-table-column>
                            <el-table-column prop="phone" label="创建人联系方式" width="150">
                            </el-table-column>
                            <el-table-column prop="status" label="当前状态" width="150">
                            </el-table-column>
                            <el-table-column prop="content" label="联系内容" width="150">
                            </el-table-column>
                            <el-table-column prop="contactTime" label="联系时间" width="150">
                            </el-table-column>
                            <el-table-column label="操作" fixed="right" width="150px">
                                <template slot-scope="scope">
                                    <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                                        icon-color="red" title="确定要删除该条留言吗？" @confirm="handleDelete(scope.row)">
                                        <el-link :underline="false" icon="el-icon-delete" type="danger"
                                            slot="reference">删除</el-link>
                                    </el-popconfirm>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                            :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="page.count"
                            layout="total, sizes, prev, pager, next, jumper" :total="total" class="MyContact_page">
                        </el-pagination>
                    </div>
                </el-tab-pane>
            </el-tabs>

        </div>



    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            imageUrl: '',
            dialogVisible: false,

            rules: {
                title: [
                    { required: true, message: '请输入标题', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
                ItemPhoto: [
                    { required: true, message: '请上传物品图片', trigger: 'blur' }
                ],
                phone: [{ required: true, message: '请输入联系方式', trigger: 'blur' }, { pattern: /^1[34578]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }],
                description: [{ required: true, message: '请输入物品描述', trigger: 'blur' }, { min: 1, max: 60, message: '长度在 1 到 60 个字符', trigger: 'blur' }],
                statusID: [
                    { required: true, message: '请选择状态', trigger: 'blur' }
                ],

            },
            dialogVisible: false,
            id: '',
            formInline: {
                title: '',
                name: '',
                date: '',
            },

            currentPage: 1,
            total: 0,
            page: {
                page: 1,
                count: 5,
            },
            tableData: [],
            activeName: 'first',

        }
    },
    created() {
        this.id = localStorage.getItem("userID");
        this.getData();
    },
    mounted() {

    },
    methods: {
        handleClick() {
            this.tableData = [];
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
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
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
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
            axios.get("/main/deleteContact", { params: { id: row.id } }).then(res => {
                this.$notify({
                    title: '成功',
                    message: '删除成功',
                    type: 'success',
                    offset: 55,
                });
                if (this.activeName == 'first')
                    this.getData();
                else {
                    this.getFoundData();
                }
            })
        },
        handleSizeChange(val) { //分页
            this.page.count = val;
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
        },
        handleCurrentChange(val) {//分页
            this.page.page = val
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
        },
        getData() {//初始化数据
            axios.get("/main/getContact", { params: { page: this.page.page, count: this.page.count, title: this.formInline.title, contactTime: this.formInline.date, userID: this.id } }).then(res => {
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


        },
        getFoundData() {
            axios.get("/main/getFoundContact", { params: { page: this.page.page, count: this.page.count, title: this.formInline.title, contactTime: this.formInline.date, userID: this.id } }).then(res => {
                this.total = res.data.data.total;
                this.tableData = res.data.data.rows
                if (this.tableData.length < 1 && this.page.page > 1) {
                    this.page.page -= 1;
                    this.getFoundData()
                    return;
                }
                for (let index = 0; index < this.tableData.length; index++) {
                    this.tableData[index].itemPhoto = `/main/download?name=${this.tableData[index].itemPhoto}`;

                }
            });
        }
    },
}
</script>
<style>
* {
    margin: 0;
    padding: 0;
}


.MyContact_box {
    width: 100%;
    height: 92%;
    padding: 15px 0 15px 0;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.MyContact_page {
    margin-top: 15px;
    margin: 20px;
}

.MyContact_Search {
    margin-top: 20px;
    height: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>