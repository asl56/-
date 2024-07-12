<!-- 招领视图 -->
<template>
    <div class="HomeFound" style="margin: 0;">
        <div id="HomeFound" style="width: 100%;height: 300px;"></div>
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
            axios.get("/main/getFoundData").then(res => {
                console.log(res, 'Found')
                // this.dateList = res.data.data;
                // this.dataList = res.data.data2
                this.init(res.data.data)

            })
        },
        // 初始化视图
        init(data) {
            var chartDom = document.getElementById('HomeFound');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    text: '招领信息',
                    subtext: '数据概览',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',


                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {

                        name: '招领信息',
                        type: 'pie',
                        radius: '50%',
                        data: data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: function (colors) {
                                    var colorList = [
                                        '#f15bb5',
                                        '#8d15b8',
                                        '#9fe6b8',
                                        '#ffa317',
                                        '#00bbf9'
                                    ];
                                    return colorList[colors.dataIndex];
                                }
                            },
                        }

                    },

                ]
            };

            option && myChart.setOption(option);


        }
    },
}
</script>
<style></style>