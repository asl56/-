<template>
    <div>
        <div class="login_box">
            <div class="login_left">
                <img src="../assets/img/LostLogo.png" class="login_logo">
                <img src="../assets/img/login1.gif" style="height: 100%;width: 100%;">
            </div>
            <div class="login_right">
                <p class="login_title">失物招领系统</p>
                <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 300px;margin: 20px auto;">
                    <el-tab-pane label="登 录" name="first">
                        <el-form :label-position="labelPosition" :model="formLabelAlign" :rules="rules"
                            style="width: 300px;margin: 20px 0;">
                            <el-form-item prop="userName">
                                <el-input v-model="formLabelAlign.userName" prefix-icon="el-icon-user"
                                    placeholder="请输入账号" clearable></el-input>
                            </el-form-item>
                            <el-form-item prop="password">
                                <el-input v-model="formLabelAlign.password" prefix-icon="el-icon-key"
                                    placeholder="请输入密码" show-password clearable></el-input>
                            </el-form-item>
                            <el-checkbox v-model="checked" label="记住密码"
                                style="float: left;margin-bottom: 10px;"></el-checkbox>
                            <el-link type="primary" style="float: right;" :underline="false"
                                @click="activeName = 'second'">注 册</el-link>
                            <Vcode :show="isShow" @success="success" @close="close" />
                            <el-button type="primary" style="width:300px;" @click.prevent="isShow = true">登录</el-button>
                        </el-form>
                    </el-tab-pane>
                    <el-tab-pane label="注 册" name="second">
                        <el-form :label-position="labelPosition" :model="registerForm" :rules="rules"
                            style="width: 300px;margin: 20px 0;" ref="registerForm">
                            <el-form-item prop="userName">
                                <el-input v-model="registerForm.userName" prefix-icon="el-icon-user"
                                    placeholder="请输入账号/用户名" clearable></el-input>
                            </el-form-item>
                            <el-form-item prop="password">
                                <el-input v-model="registerForm.password" prefix-icon="el-icon-key" placeholder="请输入密码"
                                    show-password clearable></el-input>
                            </el-form-item>
                            <el-form-item prop="name">
                                <el-input v-model="registerForm.name" prefix-icon="el-icon-user" placeholder="请输入姓名"
                                    clearable></el-input>
                            </el-form-item>
                            <el-form-item prop="email">
                                <el-input v-model="registerForm.email" prefix-icon="el-icon-message" placeholder="请输入邮箱"
                                    clearable></el-input>
                            </el-form-item>
                            <el-form-item prop="captcha">
                                <el-input v-model="registerForm.captcha" prefix-icon="el-icon-document-checked"
                                    placeholder="请输入验证码" clearable style="width: 150px;float: left;"></el-input>
                                <el-popconfirm title="您的邮箱将会收到一封带有验证码的邮件" @confirm="handleCaptcha()" ref="captchaConfirm" :disabled="disabled">
                                    <el-link type="primary" :underline="false" slot="reference" ref="captchaConfirm" :disabled="disabled"
                                        id="count">获取验证码</el-link>

                                </el-popconfirm>

                            </el-form-item>
                            <el-button type="primary" style="width:300px;"
                                @click="handleRegister('registerForm')">注册</el-button>
                        </el-form>
                    </el-tab-pane>

                </el-tabs>

            </div>
        </div>
    </div>
</template>

<script>

import Vcode from "vue-puzzle-vcode";
import axios from "axios";
export default {
    components: {
        Vcode
    },
    data() {

        return {
            disabled: false,
            deadline: 60,
            getCaptcha: false,
            TrueCaptcha: 'error',
            activeName: 'first',
            isShow: false,//验证码
            checked: true,//记住密码
            loading: false,
            fullscreenLoading: false,//页面加载
            labelPosition: 'right',
            formLabelAlign: {
                userName: '',
                password: '',
            },
            registerForm: {
                userName: '',
                name: '',
                password: '',
                email: '',
                captcha: '',
                role: '用户',
            },
            rules: {
                userName: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ],
                email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }, { pattern: /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/, message: '邮箱格式不正确', trigger: 'blur' }],

                captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }, { pattern: /^\d{6}$/, message: '验证码格式不正确', trigger: 'blur' }],
            }
        }
    },
    mounted() {
        this.getCookie();

    },
    methods: {
        time() {
            var count = document.querySelector('#count');
            console.log(count, 'count')
            this.disabled = true
            var time = 59;
            var timer = setInterval(function () {
                // 判断剩余秒数
                if (time == 0) {
                    // 清除定时器和复原按钮
                    console.log(this.disabled,'1')
                    clearInterval(timer);
                    this.disabled = false
                    count.innerHTML = '<span class="el-link--inner">获取验证码</span>';
                    this.TrueCaptcha='error'
                } else {
                    count.innerHTML = `<span class="el-link--inner">${time}秒后可重新获取</span>`;
                    time--;
                }
            }.bind(this), 1000);

        },
        handleCaptcha() {
            if (this.registerForm.email != null && this.registerForm.email != '') {
                this.time()
                this.$notify({
                    title: '成功',
                    message: '验证码发送中，请稍等',
                    type: 'success'
                });
                axios.get("/login/email?email=" + this.registerForm.email).then(res => {

                    this.TrueCaptcha = res.data.data
                    console.log(this.TrueCaptcha, 'true')
                })
              
            }
        },
        handleRegister(registerForm) {
            this.$refs[registerForm].validate((valid) => {
                if (valid) {
                    if (this.registerForm.captcha == this.TrueCaptcha) {
                        axios.get("/login/getUser?userName=" + this.registerForm.userName).then(res => {
                            if (res.data.data.total == 0) {
                                axios.post("/login/addUser", JSON.stringify(this.registerForm), {
                                    headers: {
                                        "Content-Type": "application/json"
                                    }
                                }).then(res => {
                                    this.registerForm = {
                                        userName: '',
                                        name: '',
                                        password: '',
                                        email: '',
                                        role: '用户',
                                    }
                                    this.$notify({
                                        title: '成功',
                                        message: '注册成功',
                                        type: 'success'
                                    });
                                    this.activeName = 'first'
                                })
                            } else {
                                this.$notify.error({
                                    title: '错误',
                                    message: '注册失败，用户名重复'
                                });
                            }
                        })
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: '注册失败，验证码错误'
                        });
                    }

                }
            })


        },
        handleClick(tab, event) {
        },
        getData() {
            const data = {
                userName: this.formLabelAlign.userName,
                password: this.formLabelAlign.password
            }
            axios.post("/login/userLogin?userName=" + data.userName + "&password=" + data.password).then(res => {
                if (res.data.code != 0 && res.data.data2.status != '停用') {
                    this.$message({
                        message: '登录成功',
                        type: 'success',
                        duration: 1500
                    });
                    localStorage.setItem('jwt', res.data.data);
                    localStorage.setItem('userID', res.data.data2.id)
                    localStorage.setItem('avatar', res.data.data2.avatar)
                    localStorage.setItem('role', res.data.data2.role)
                    if (res.data.data2.role == '管理员')
                        this.$router.push('/AdminIndex/AdminHome')
                    else {
                        this.$router.push('/UserIndex/UserHome')
                    }
                } else {
                    this.$message({
                        message: '登录失败，请检查账号或密码',
                        type: 'error',
                        duration: 1500
                    });
                }
            }).catch(err => {
                this.$message({
                    message: 'error',
                    type: 'error',
                    duration: 1500
                });

            });
        },

        submit() {
            this.isShow = true;
        },
        //设置cookie
        setCookie(c_name, c_pwd, c_checked, exdays) {
            var exdate = new Date(); //获取时间
            exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays); //保存的天数
            //字符串拼接cookie
            window.document.cookie = "userName" + "=" + c_name + ";path=/;expires=" + exdate.toGMTString();
            window.document.cookie = "password" + "=" + c_pwd + ";path=/;expires=" + exdate.toGMTString();
            window.document.cookie = "checked" + "=" + c_checked + ";path=/;expires=" + exdate.toGMTString();
        },
        //读取cookie
        getCookie: function () {
            if (document.cookie.length > 0) {
                var arr = document.cookie.split('; ');
                for (var i = 0; i < arr.length; i++) {
                    var arr2 = arr[i].split('='); //再次切割
                    //判断查找相对应的值
                    if (arr2[0] == 'userName') {
                        this.formLabelAlign.userName = arr2[1]; //保存到保存数据的地方
                    } else if (arr2[0] == 'password') {
                        this.formLabelAlign.password = arr2[1];
                    }
                }
            }
        },
        //清除cookie
        clearCookie: function () {
            this.setCookie("", "", -1); //修改2值都为空，天数为负1天就好了
        },
        // 用户通过了验证
        success(msg) {
            //console.log(msg)
            this.isShow = false; // 通过验证后，需要手动隐藏模态框
            //判断复选框是否被勾选 勾选则调用配置cookie方法
            if (this.checked == true) {
                //传入账号名，密码，和保存天数3个参数
                this.setCookie(this.formLabelAlign.userName, this.formLabelAlign.password, this.checked, 7);
            } else {
                //清空Cookie
                this.clearCookie();
            }

            this.getData()
        },
        // 用户点击遮罩层，应该关闭模态框
        close() {
            this.isShow = false;
        }
    }


}
</script>
<style>
.login_logo {
    width: 200px;
    position: absolute;

}

.login_box {
    margin: 80px auto;
    width: 60%;
    height: 520px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);


}

.el-form {
    margin: 50px auto;
}

.login_title {
    font-size: 32px;
    margin-top: 10px;
}

.login_left {
    float: left;
    width: 55%;
    height: 100%;
    border-radius: 10px 0 0 10px;
    position: relative;

}

.login_right {
    margin-top: 0px;
    border-radius: 0 10px 10px 0;
    float: left;
    width: 45%;
    height: 100%;
}
</style>