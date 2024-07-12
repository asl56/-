<!-- 数据总览 -->
<template>
    <div class="DataView">
        <el-row :gutter="20">
            <el-col :span="6">
                <div>
                    <el-statistic>
                        <template slot="formatter">
                            <span style="font-size: 16px;font-weight: bold;">用户留言</span>
                            <dv-digital-flop :config="config" style="width:100px;height:50px;color: black;" />
                        </template>
                    </el-statistic>
                </div>
            </el-col>
            <el-col :span="6">
                <div>
                    <el-statistic>
                        <template slot="formatter">
                            
                            <template>
                                <span style="font-size: 16px;font-weight: bold;">今日新增留言</span>
                            </template>
                            <i class="el-icon-star-on" style="color:red" v-show="like"></i>
                           <template>
                             <dv-digital-flop :config="toDayConfig" style="width:100px;height:50px;color: black;" />
                            
                           </template>
                           
                        </template>
                    </el-statistic>
                </div>
            </el-col>
            <el-col :span="6">
                <div>
                    <el-statistic>
                        <template slot="formatter">
                            <span style="font-size: 16px;font-weight: bold;">剩余丢失物品</span>
                            <dv-digital-flop :config="LostConfig" style="width:100px;height:50px;color: black;" />
                        </template>
                    </el-statistic>
                </div>
            </el-col>
            <el-col :span="6">
                <div>
                    <el-statistic>
                        <template slot="formatter">
                            <span style="font-size: 16px;font-weight: bold;">剩余招领物品</span>
                            <dv-digital-flop :config="FoundConfig" style="width:100px;height:50px;color: black;" />
                        </template>
                    </el-statistic>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            today: '',
            like: true,
            title: "增长人数",

            toDayConfig: {
                number: [0],
                content: '{nt}条',
                style: {
                    fontSize: 20,//字体大小
                    fill: 'red',//字体颜色
                    //将字体样式设置为黑体
                    fontFamily: '微软雅黑',
                }
            },
            config: {
                number: [0],
                content: '{nt}条',
                style: {
                    fontSize: 20,//字体大小
                    fill: 'black',//字体颜色
                    //将字体样式设置为黑体
                    fontFamily: '微软雅黑',
                }
            },
            LostConfig: {
                number: [0],
                content: '{nt}件',
                style: {
                    fontSize: 20,//字体大小
                    fill: 'black',//字体颜色
                    //将字体样式设置为黑体
                    fontFamily: '微软雅黑',
                }
            },
            FoundConfig: {
                number: [0],
                content: '{nt}件',
                style: {
                    fontSize: 20,//字体大小
                    fill: 'black',//字体颜色
                    //将字体样式设置为黑体
                    fontFamily: '微软雅黑',
                }
            }
        }
    },
    mounted() {

        this.getData();

    },
    methods: {
        // 初始化数据
        getData() {
            this.today = this.getCurrentDate();
            axios.get("/main/getFeedBackAll").then(res => {
                this.config = {
                    ...this.config,
                    number: [res.data.data.total],
                }
            });
            axios.get("/main/getFeedBack", { params: { released: this.today } }).then(res => {
                this.toDayConfig = {
                    ...this.toDayConfig,
                    number: [res.data.data.total],
                }
                if(res.data.data.total!=0){
                    this.like=true
                }else{
                    this.like=false
                }
            });
            axios.get("/main/getLost", { params: { statusID: 2 } }).then(res => {
                this.LostConfig = {
                    ...this.LostConfig,
                    number: [res.data.data.total],
                }
            });
            axios.get("/main/getFound", { params: { statusID: 4 } }).then(res => {
                this.FoundConfig = {
                    ...this.FoundConfig,
                    number: [res.data.data.total],
                }
            });
        },
        // 获取当前日期
        getCurrentDate() {
            let now = new Date();
            let year = now.getFullYear();
            let month = now.getMonth() + 1;
            if (month < 10) {
                month = '0' + month
            }
            let day = now.getDate();
            if (day < 10) {
                day = '0' + day
            }
            return year + "-" + month + "-" + day;
        }
    },

}
</script>
<style>
.like {
    cursor: pointer;
    font-size: 25px;
    display: inline-block;

}

.DataView {
    padding-top: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    margin-bottom: 20px;
}
</style>