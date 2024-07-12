<template>
    <div class="AdminIndex">
        <el-container>
            <el-header style="border-bottom: 1px solid #ddd;margin: 0 30px;position: relative;">
                <img src="../assets/img/LostLogo.png" class="index_logo">
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
            </el-header>
            <el-container>
                <el-aside width="300px">
                    <el-menu :default-active="activeIndex" class="el-menu-vertical-demo" @open="handleOpen"
                        @close="handleClose" :router="true">
                        <el-menu-item index="/AdminIndex/AdminHome">
                            <i class="el-icon-s-home"></i>
                            <span slot="title">系统首页</span>
                        </el-menu-item>
                        <el-menu-item index="/UserIndex/UserHome">
                            <i class="el-icon-s-home"></i>
                            <span slot="title">用户首页</span>
                        </el-menu-item>
                        <el-menu-item index="/AdminIndex/AdminVerify">
                            <i class="el-icon-document-checked"></i>
                            <span slot="title">信息审核</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span>信息管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/AdminIndex/AdminNotice">公告信息</el-menu-item>
                                <el-menu-item index="/AdminIndex/AdminLost">失物信息</el-menu-item>
                                <el-menu-item index="/AdminIndex/AdminFound">招领物品</el-menu-item>
                                <el-menu-item index="/AdminIndex/AdminFeedBack">平台建议</el-menu-item>
                            </el-menu-item-group>

                        </el-submenu>

                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span>用户管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/AdminIndex/AdminManage">管理员信息</el-menu-item>
                                <el-menu-item index="/AdminIndex/UserManage">用户信息</el-menu-item>
                            </el-menu-item-group>

                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main style="overflow: scroll;height: 100%;"><router-view /></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import { RouterView } from 'vue-router';

// @ is an alias to /src
import axios from "axios";
export default {
    data() {
        return {
            activeIndex: this.$route.path,
            imageUrl: '',
        }
    },
    created() {
        if (localStorage.getItem('avatar') != null && localStorage.getItem('avatar') != '')
            this.imageUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
    },
    methods: {
        outLogin() {
            localStorage.setItem('jwt', '');
            this.$router.push('/')
        },
        handle() {
            //获取当前路由
            if (this.$route.path != '/PersonalCenter')
                this.$router.push({ path: '/PersonalCenter' });
        },
        handleSelect(key, keyPath) {
        },
        handleOpen(key, keyPath) {
        },
        handleClose(key, keyPath) {
        }

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

.index_avatar {
    /* display: inline-block; */
    width: 50px;
    height: 100%;
    text-align: center;
    line-height: 100%;
    float: right;
    margin-right: 50px;
}
::-webkit-scrollbar {
    width: 0px;
    /* 设置滚动条的宽度 */
}

</style>