<template>
    <div>
        <el-container>
            <el-header>
                <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :router="true"
                    @select="handleSelect">
                    <el-menu-item style="margin-right: 200px;"><img src="../../assets/img/LostLogo.png"
                            class="index_logo"></el-menu-item>

                    <el-menu-item index="/UserIndex/UserHome">首页</el-menu-item>
                    <el-menu-item index="/UserIndex/UserLostHome">失物中心</el-menu-item>
                    <el-menu-item index="/UserIndex/UserFoundHome">招领中心</el-menu-item>
                    <el-menu-item index="/UserIndex/UserFeedBack">反馈</el-menu-item>
                    <el-submenu index="2-4">
                        <template slot="title">我的</template>
                        <el-menu-item index="/UserIndex/MyLost">我发布的失物</el-menu-item>
                        <el-menu-item index="/UserIndex/MyFound">我发布的招领</el-menu-item>
                        <el-menu-item index="/UserIndex/MyContact">我的联系</el-menu-item>
                        <el-menu-item index="/UserIndex/ContactMe">联系我的</el-menu-item>
                    </el-submenu>
                    <el-menu-item index="/AdminIndex/AdminHome" v-if="role == '管理员'">管理员页面</el-menu-item>
                    <div class="index_avatar">
                        <el-dropdown>
                            <span>
                                <el-avatar :src="imageUrl" :size="50" style="cursor: pointer;"></el-avatar>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item @click.native="handle()" index="6">个人中心</el-dropdown-item>
                                <el-dropdown-item @click.native="outLogin()">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </el-menu>

            </el-header>
            <el-main style="width: 95%;margin: 0 auto;"><router-view /></el-main>
        </el-container>
    </div>
</template>
<script>
export default {
    data() {
        return {
            activeIndex: this.$route.path,
            role: '',
        }
    },
    created() {

        if (localStorage.getItem('avatar') != null && localStorage.getItem('avatar') != '')
            this.imageUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
        this.role=localStorage.getItem('role')
    },
    mounted() {

    },
    methods: {
        handle() {
            //获取当前路由
            if (this.$route.path != '/UserIndex/UserPersonalCenter')
                this.$router.push({ path: '/UserIndex/UserPersonalCenter' });
            this.$router.go(0)
        },
        handleSelect(key, keyPath) {
        },
        outLogin() {
            localStorage.setItem('jwt', '');
            this.$router.push('/')
        },
    },
}
</script>
<style scoped>
.index_logo {
    width: 200px;
    float: left;
    position: absolute;
    top: -12px;
    left: 12px;
}
.index_avatar{
    float: right;
    margin-right: 100px;
}
</style>