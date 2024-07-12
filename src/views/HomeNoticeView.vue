<!-- 公告视图 -->
<template>
    <div class="HomeNoticeView">
        <el-timeline :reverse="false" style="width: 250px;margin: 0 auto;">
            <el-timeline-item v-for="(activity, index) in activities" :key="index" :icon="activity.icon"
                :type="activity.type" :color="activity.color" :size="activity.size" :timestamp="activity.releaseDate" style="height: 80px;">
                {{ activity.title }}
            </el-timeline-item>
        </el-timeline>
    </div>
</template>

<script>
import axios from "axios";
export default {
    data() {
        return {
            reverse: false,
            activities: []
        }
    },
    mounted() {
        this.getData()
    },
    methods: {
        // 初始化数据
        getData(){
            axios.get("/main/getNoticeAll",{params:{page:1,count:3} }).then(res => {
                 console.log(res) 
                this.activities=res.data.data.rows
                this.activities[0].color='#67C23A'
            })
        }
    },
}
</script>
<style>
.HomeNoticeView{
    margin-top: 10px;
}

</style>