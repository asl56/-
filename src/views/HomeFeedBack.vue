<!-- 留言视图 -->
<template>
    <div class="HomeFeedBack" style="margin: 0;">
        <div id="HomeFeedBack" style="width: 100%;height: 300px;"></div>
    </div>
</template>

<script>
import axios from "axios";
import * as echarts from 'echarts';
export default {
    data() {
        return {
            reverse: false,
            activities: [],
            dateList: [],
            dataList: [],
        }
    },
    mounted() {
        this.getData()
    },
    methods: {
        // 初始化数据
        getData() {
            axios.get("/main/getData").then(res => {
                console.log(res, 'data')
                this.dateList = res.data.data;
                this.dataList = res.data.data2
                this.init(this.dateList, this.dataList)
            })
        },
        // 初始化视图
        init(date, data) {
            var chartDom = document.getElementById('HomeFeedBack');
            var myChart = echarts.init(chartDom);
            var option;
            option = {
                title: {
                    text: '用户留言',
                    subtext: '数据概览',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    // formatter: '日期：{b0}<br>数量：{c0}条',
                    backgroundColor: "rgba(255,255,255,0.8)"
                },
                xAxis: {
                    type: 'category',
                    name: "日期",
                    data: date
                },
                yAxis: {
                    type: 'value',
                    min: 0,
                    max: 20,
                    interval: 5, // 指定刻度间隔
                    axisLabel: {
                        formatter: '{value} 条'
                    },
                },
                series: [
                    {
                        name: "留言数量",
                        data: data,
                        type: 'line',
                        smooth: true,

                        itemStyle: {
                            normal: {
                                color: '#a80000', //改变折线点的颜色
                                lineStyle: {
                                    color: '#a80000' //改变折线颜色
                                }
                            }
                        },
                    }
                ]
            };
            option && myChart.setOption(option);
        }
    },
}
</script>
<style></style>