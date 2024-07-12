<template>
    <div class="UserFeedBack">

        <div style="margin-bottom: 20px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item style="font-weight: bold;">首页</el-breadcrumb-item>
                <el-breadcrumb-item>反馈</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div style="display: flex;">
            <div style="width: 60%;padding: 10px;border-radius: 10px; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
                <el-input type="textarea" :rows="20" placeholder="留下您宝贵的意见" v-model="textarea">
                </el-input>
                <el-button type="primary" style="margin-top: 20px;float: left;" @click="addFeedBack">提交</el-button>
            </div>

            <el-main style="width: 30%; height: 520px;">

                <el-card class="box-card" v-for="(item, index) in tableData" :key="index">
                    <div style="display: flex;align-items: center;flex-direction: row;width: 480px;position: relative;">
                        <el-avatar :src="item.avatar"></el-avatar>
                        <span style="margin-left: 20px;">{{ item.name }}</span>
                        <span style="margin-left: 20px;font-size: 12px;color: gray;">发布日期：{{item.released}}</span>
                        <el-popconfirm title="确定删除该条建议吗？" v-if="item.userID == id" style="position: absolute; right: 10%;" @confirm="handleDelete(item.id)">
                            <el-link :underline="false"  icon="el-icon-delete" type="danger"
                            slot="reference">删除</el-link>
                        </el-popconfirm>
                       
                    </div>
                    <div style="text-align: left;margin-top: 10px;overflow: hidden;text-overflow: ellipsis;">

                        <el-popover placement="bottom-start" width="200" trigger="hover" :content="item.content">
                            <p slot="reference">{{ item.content }}</p>
                        </el-popover>
                    </div>
                </el-card>
            </el-main>

        </div>

    </div>
</template>
<script>
import axios from "axios";
export default {
    data() {
        return {
            textarea: '',
            id: '',
            tableData: [],
        }
    },
    created() {
        this.id = localStorage.getItem('userID')
        this.getData()
        
    },
    mounted(){
        
    },
    methods: {
        handleDelete(id) {
            axios.get("/main/deleteFeedBack", { params: { id: id } }).then(res => {
                this.$notify({
                    title: '成功',
                    message: '删除成功',
                    type: 'success',
                    offset: 55,
                });
                this.getData()
            })
        },
        getData() {
            axios.get("/main/getFeedBackAll").then(res => {
                this.tableData = res.data.data.rows
                for (let index = 0; index < this.tableData.length; index++) {
                    this.tableData[index].avatar = `/main/download?name=${this.tableData[index].avatar}`;
                }
            })
        },
        addFeedBack() {
            const data = {
                userID: this.id,
                content: this.textarea
            }
            axios.post("/main/addFeedBack", JSON.stringify(data), {
                headers: { 'Content-Type': 'application/json' }
            }).then(res => {
                this.$notify({
                    title: '成功',
                    message: '发布成功',
                    type: 'success',
                    offset: 55,
                });
                this.textarea = '';
                this.getData()
            })
        },
    },
}
</script>
<style scoped>
.UserFeedBack .box-card {
    margin: 0 auto;
    margin-bottom: 10px;
    width: 95%;
    height: 120px;
    border-radius: 10px;
}

::-webkit-scrollbar {
    width: 0px;
    /* 设置滚动条的宽度 */
}
</style>