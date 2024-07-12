<template>
    <div>

        <div style="width: 95%;margin: 0 auto;">
            <el-carousel height="400px">
                <el-carousel-item v-for="item in imageList" :key="item" :loop="true">

                    <img src="../../assets/img/1.jpg" alt="image" style="height: 100%;width: 100%;">

                </el-carousel-item>
            </el-carousel>
        </div>
        <!-- 公告 -->
        <div class="userHome_Notice">
            <span class="el-icon-bell" style="position: absolute ;left: 0; top: 4px;"></span>
            <marquee behavior="scroll" direction="left" style="position: absolute ;left: 17px;">
                <span>{{ notice.title }}</span>
            </marquee>
        </div>
        <el-divider></el-divider>

        <div style="margin: 20px 35px 0;display: flex; justify-content: space-between;">
            <div>
                <el-card class="box-card" shadow="hover">
                    <div slot="header" class="clearfix">
                        <h3 style="float: left;">失物中心</h3>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="handleLost">前往<i
                                class="el-icon-arrow-right"></i></el-button>
                    </div>
                    <div class="text item" style="display: flex;justify-content: space-between;flex-wrap: wrap;">
                        <el-card :body-style="{ padding: '0px' }" style="width: 48%;" shadow="hover"
                            v-for="(item, index) in LostList" :key="index">
                            <img :src="item.itemPhoto" class="image">
                            <div style="padding: 14px;">
                                <span>{{ item.title }}</span>
                                <p style="color: #aaa;">{{ item.releaseDate }}</p>
                                <div class="bottom clearfix">
                                    <el-button type="text" class="button" style="float: left ;margin-left: 20px;"
                                        @click="handelView(item)">详情</el-button>
                                    <el-button type="text" class="button" style="margin-right: 20px;"
                                        @click="showContact(item.id)">留言</el-button>
                                </div>
                            </div>
                        </el-card>
                    </div>
                </el-card>
            </div>
            <div>
                <el-card class="box-card" shadow="hover">
                    <div slot="header" class="clearfix">
                        <h3 style="float: left;">招领中心</h3>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="handleFound">前往<i
                                class="el-icon-arrow-right"></i></el-button>
                    </div>
                    <div class="text item" style="display: flex;justify-content: space-between;flex-wrap: wrap;">
                        <el-card :body-style="{ padding: '0px' }" style="width: 48%;" shadow="hover"
                            v-for="(item, index) in foundList" :key="index">
                            <img :src="item.itemPhoto" class="image">
                            <div style="padding: 14px;">
                                <span>{{ item.title }}</span>
                                <p style="color: #aaa;">{{ item.releaseDate }}</p>
                                <div class="bottom clearfix">
                                    <el-button type="text" class="button" style="float: left ;margin-left: 20px;"
                                        @click="handelFoundView(item)">详情</el-button>
                                    <el-button type="text" class="button" style="margin-right: 20px;"
                                        @click="showFoundContact(item.id)">留言</el-button>
                                </div>
                            </div>
                        </el-card>
                    </div>
                </el-card>
            </div>
        </div>
        <el-dialog title="详细信息" :visible.sync="dialogShowView" width="500px">
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
                <el-form-item label="联系方式" :label-width="formLabelWidth" prop="phone">
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
        <el-dialog title="留言" :visible.sync="dialogFormVisible" @close="handleClose()">
            <el-form>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <el-input v-model="content" type="textarea" autosize autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addContact">确 定</el-button>
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
            imageList: [
                {img:'../../assets/img/1.jpg'}
                
                
            ],//轮播图列表
            page: {
                page: 1,
                count: 2,
             },//分页
            data: {
                lostID: '',
                userID: '',
                content: '',
                foundID: '',
            }, //添加留言
            content: '', //留言
            dialogFormVisible: false,// 失物留言弹窗

            dialogShowView: false,//详细信息弹窗

            showView: {}, //详细信息

            FoundDialogFormVisible: false, //招领留言弹窗

            formLabelWidth: '120px',//表单文本宽度

            LostList: [], //失物列表
            foundList: [],//招领列表

            notice: {},//公告对象
        }
    },
    created() {
        this.getData()
        
    },
    methods: {
        // 弹窗关闭事件
        handleClose() {
            this.data = {
                lostID: '',
                userID: '',
                content: '',
            },
                this.content = ''
        },
        // 添加失物留言
        addContact() {
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
                this.dialogFormVisible = false
            })
        },
        // 添加招领留言
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
        //  失物列表展示
        showContact(id) {
            this.data = {
                lostID: id,
                foundID: '',
                userID: localStorage.getItem('userID'),
                content: this.content
            }
            this.dialogFormVisible = true
        },
        // 招领列表展示
        showFoundContact(id) {
            this.data = {
                lostID: '',
                foundID: id,
                userID: localStorage.getItem('userID'),
                content: this.content
            }
            this.FoundDialogFormVisible = true
        },
        //招领物品详情
        handelFoundView(row) {
            axios.get("/main/getFound", { params: { id: row.id } }).then(res => {
                this.showView = res.data.data.rows[0];
                this.showView.itemPhoto = `/main/download?name=${this.showView.itemPhoto}`;
                this.dialogShowView = true;
            })
        },
        //失物详情
        handelView(row) {
            axios.get("/main/getLost", { params: { id: row.id } }).then(res => {
                this.showView = res.data.data.rows[0];
                this.showView.itemPhoto = `/main/download?name=${this.showView.itemPhoto}`;
                this.dialogShowView = true;
            })
        },
        // 前往失物中心
        handleLost() {
            this.$router.push('/UserIndex/UserLostHome');
            this.$router.go(0)
        },
        // 前往招领中心
        handleFound() {
            this.$router.push('/UserIndex/UserFoundHome');
            this.$router.go(0)
        },
        // 初始化数据
        getData() {
            axios.get("/main/getLost", { params: { page: 1, count: 2, statusID: 2 } }).then(res => {
                this.LostList = res.data.data.rows
                for (let index = 0; index < this.LostList.length; index++) {
                    this.LostList[index].itemPhoto = `/main/download?name=${this.LostList[index].itemPhoto}`;

                }
            })
            axios.get("/main/getFound", { params: { page: 1, count: 2, statusID: 4 } }).then(res => {
                this.foundList = res.data.data.rows
                for (let index = 0; index < this.LostList.length; index++) {
                    this.foundList[index].itemPhoto = `/main/download?name=${this.foundList[index].itemPhoto}`;

                }
            })
            axios.get("/main/getNoticeAll", { params: { page: 1, count: 1 } }).then(res => {
                this.notice = res.data.data.rows[0]
            })
            
        },
    },
}
</script>
<style scoped>
.userHome_Notice {
    width: 30%;
    height: 20px;
    margin-left: 35px;
    position: relative;
    margin-top: 10px;
    
}

.el-divider--horizontal {
    display: block;
    height: 1px;
    width: 30%;
    margin: 5px 35px;
}

.el-icon-message-solid {
    float: left;
}

.time {
    font-size: 13px;
    color: #999;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
}

.button {
    padding: 0;
    float: right;
}

.image {
    width: 100%;
    height: 250px;
    display: block;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
}


.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;

}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

.box-card {
    width: 650px;
}
</style>