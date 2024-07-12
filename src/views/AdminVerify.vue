<!-- 审核 -->
<template>
    <div class="AdminVerify">
        <div style="margin-bottom: 20px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item style="font-weight: bold;">首页</el-breadcrumb-item>
                <el-breadcrumb-item>信息审核</el-breadcrumb-item>

            </el-breadcrumb>
        </div>

        <div class="AdminVerify_box">
            <div class="AdminVerify_Search">
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
                <el-tab-pane label="失物审核" name="first">
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
                            <el-table-column prop="releaseDate" label="创建时间" width="150">
                            </el-table-column>
                            <el-table-column label="操作" fixed="right" width="150">
                                <template slot-scope="scope">
                                    <el-link icon="el-icon-view" :underline="false" type="primary"
                                        style="margin:0 20px;" @click="handelView(scope.row, 1)">详情</el-link>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                            :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="page.count"
                            layout="total, sizes, prev, pager, next, jumper" :total="total" class="AdminVerify_page">
                        </el-pagination>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="招领审核" name="second">
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
                            <el-table-column prop="releaseDate" label="创建时间" width="150">
                            </el-table-column>
                            <el-table-column label="操作" fixed="right" width="150px">
                                <template slot-scope="scope">
                                    <el-link icon="el-icon-view" :underline="false" type="primary"
                                        style="margin:0 20px;" @click="handelFoundView(scope.row, 0)">详情</el-link>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                            :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="page.count"
                            layout="total, sizes, prev, pager, next, jumper" :total="total" class="AdminVerify_page">
                        </el-pagination>
                    </div>
                </el-tab-pane>
            </el-tabs>

        </div>
        <el-dialog title="详细信息" :visible.sync="dialogShowView" width="500px" @close="handleClose">
            <el-form :model="showView" style="margin: 0;">
                <el-form-item label="物品图片" :label-width="formLabelWidth">

                    <img :src="showView.itemPhoto" class="avatar">

                </el-form-item>

                <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
                    <el-input v-model="showView.title" autocomplete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth" prop="description">
                    <el-input v-model="showView.description" type="textarea" autocomplete="off"
                        :disabled="true"></el-input>
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
                <el-button @click="handleNO()" type="danger">不通过</el-button>
                <el-button type="primary" @click="handleVerify()">通 过</el-button>
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
            showView: {
                key: '',
            },
            formLabelWidth: '120px',
            imageUrl: '',
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
        this.getData();
    },
    mounted() {

    },
    methods: {
        // 打回
        handleNO() {
            if (this.showView.key) {
                const data = {
                    id: this.showView.id,
                    statusID: 8
                }
                axios.post("/main/editLost", JSON.stringify(data), {
                    headers: { 'Content-Type': 'application/json' }
                }).then(res => {
                    this.$notify({
                        title: '成功',
                        message: '审核成功',
                        type: 'success',
                        offset: 55,
                    });
                })
            } else {
                const data = {
                    id: this.showView.id,
                    statusID: 8
                }
                axios.post("/main/editFound", JSON.stringify(data), {
                    headers: { 'Content-Type': 'application/json' }
                }).then(res => {
                    this.$notify({
                        title: '成功',
                        message: '审核成功',
                        type: 'success',
                        offset: 55,
                    });
                })
            }
            this.dialogShowView = false
            this.handleClose()
        },
        // 失物详情
        handelView(row, key) {
            axios.get("/main/getLost", { params: { id: row.id } }).then(res => {
                //console.log(res, 'get')
                this.showView = res.data.data.rows[0];
                this.showView.itemPhoto = `/main/download?name=${this.showView.itemPhoto}`;
                this.showView.key = key
                this.dialogShowView = true;

            })
        },
        // 招领物品详情
        handelFoundView(row, key) {
            axios.get("/main/getFound", { params: { id: row.id } }).then(res => {
                //console.log(res, 'get')
                this.showView = res.data.data.rows[0];
                this.showView.itemPhoto = `/main/download?name=${this.showView.itemPhoto}`;
                this.showView.key = key
                this.dialogShowView = true;
            })
        },
        // 审核
        handleVerify() {
            if (this.showView.key) {
                const data = {
                    id: this.showView.id,
                    statusID: 2
                }
                axios.post("/main/editLost", JSON.stringify(data), {
                    headers: { 'Content-Type': 'application/json' }
                }).then(res => {
                    this.$notify({
                        title: '成功',
                        message: '审核成功',
                        type: 'success',
                        offset: 55,
                    });
                })
            } else {
                const data = {
                    id: this.showView.id,
                    statusID: 4
                }
                axios.post("/main/editFound", JSON.stringify(data), {
                    headers: { 'Content-Type': 'application/json' }
                }).then(res => {
                    this.$notify({
                        title: '成功',
                        message: '审核成功',
                        type: 'success',
                        offset: 55,
                    });
                })
            }
            this.dialogShowView = false
            this.handleClose()
        },
        //查询
        handleClick() {
            // console.log(tab, event);
            this.tableData = [];
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
        },
        // 重置
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
        // 弹窗关闭
        handleClose() {
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
        },

        handleSizeChange(val) { //分页
            console.log(`每页 ${val} 条`);
            this.page.count = val;
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
        },
        handleCurrentChange(val) {//分页
            console.log(`当前页: ${val}`);
            this.page.page = val
            if (this.activeName == 'first')
                this.getData();
            else {
                this.getFoundData();
            }
        },
        getData() {//初始化数据
            axios.get("/main/getLost", { params: { page: this.page.page, count: this.page.count, releaseDate: this.formInline.date, statusID: 7 } }).then(res => {
                //console.log(res)
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
            axios.get("/main/getFound", { params: { page: this.page.page, count: this.page.count, releaseDate: this.formInline.date, statusID: 7 } }).then(res => {
                console.log(res, 'v')
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
        }
    },
}
</script>
<style>
* {
    margin: 0;
    padding: 0;
}


.AdminVerify_box {
    width: 100%;
    height: 92%;
    padding: 15px 0 15px 0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.AdminVerify_page {
    margin-top: 15px;
    margin: 20px;
}

.AdminVerify_Search {
    margin-top: 20px;
    height: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>