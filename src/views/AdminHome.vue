<template>
    <div class="Adminhome">
        <DataView></DataView>
        <div style="display: flex;justify-content:space-between">
            <div class="home_datav">
                <dv-border-box-7 :color="['#fff', '#ddd']" style="overflow: hidden;">
                    <Notice></Notice>
                </dv-border-box-7>
            </div>
            <div class="home_datav"><dv-border-box-7 :color="['#fff', '#ddd']">
                    <HomeFeedBack></HomeFeedBack>
                </dv-border-box-7></div>
        </div>
        <div style="display: flex;justify-content:space-between;margin-top: 20px">
            <div class="home_datav"><dv-border-box-7 :color="['#fff', '#ddd']">
                    <HomeLost></HomeLost>
                </dv-border-box-7></div>
            <div class="home_datav"><dv-border-box-7 :color="['#fff', '#ddd']">
                    <HomeFound></HomeFound>
                </dv-border-box-7></div>
        </div>


    </div>
</template>

<script>
// @ is an alias to /src
import Notice from './HomeNoticeView.vue'
import DataView from './DataView.vue'
import HomeFeedBack from './HomeFeedBack.vue'
import HomeLost from './HomeLost.vue'
import HomeFound from './HomeFound.vue'
export default {
    components: { Notice, DataView, HomeFeedBack, HomeLost, HomeFound },
    data() {
        return {

        }
    },
    mounted() {
        if (localStorage.getItem('avatar') != null && localStorage.getItem('avatar') != '')
            this.imgUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
        console.log(this.imgUrl, '1')
    },
}
</script>
<style scoped>
.home_datav {
    width: 550px;
    height: 250px;
}
</style>