<template>
    <div>
        <div style="margin-top: 20px;margin-left: 30px;">

        </div>
        <el-card class="userP_card">
            <div @click="dialogVisible = true"
                style="width: 150px;height: 150px;margin: 0 auto;cursor: pointer; margin-bottom: 10px;">
                <el-avatar :size=150 :src="imageUrl"></el-avatar>
            </div>
            <el-tabs v-model="activeName">
                <el-tab-pane label="个人信息" name="first">
                    <div class="userP_msg">

                        <el-link icon="el-icon-edit" style="float: right;margin-right: 60px;"
                            @click="dialogFormVisible = true" :underline="false">修改个人信息</el-link>

                        <el-descriptions title="个人信息" :column=2
                            :contentStyle="{ marginBottom: '20px', fontSize: '16px' }">
                            <el-descriptions-item label="用户名">{{ formData.userName }}</el-descriptions-item>
                            <el-descriptions-item label="手机号">{{ formData.phone }}</el-descriptions-item>
                            <el-descriptions-item label="姓名">{{ formData.name }}</el-descriptions-item>
                            <el-descriptions-item label="权限">
                                <el-tag size="small">{{ formData.role }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item label="邮箱">{{ formData.email }}

                            </el-descriptions-item>
                        </el-descriptions>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="修改密码" name="second">
                    <div class="userP_msg">
                        <el-descriptions title="修改密码" :column=2
                            :contentStyle="{ marginBottom: '20px', fontSize: '16px' }">
                        </el-descriptions>
                        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                            class="demo-ruleForm" style="margin: 0;">
                            <!-- <el-form-item label="原始密码" prop="password">
                                <el-input type="password" placeholder="原始密码" v-model="ruleForm.password"
                                    show-password></el-input>
                            </el-form-item> -->
                            <el-form-item label="新密码" prop="newPassword">
                                <el-input type="password" placeholder="新密码" v-model="ruleForm.newPassword"
                                    show-password></el-input>
                            </el-form-item>
                            <el-form-item label="确认密码" prop="checkPassword">
                                <el-input type="password" placeholder="确认密码" v-model="ruleForm.checkPassword"
                                    show-password></el-input>
                            </el-form-item>
                            <el-form-item prop="captcha" label="验证码">
                                <el-input v-model="ruleForm.captcha" placeholder="请输入验证码" clearable
                                    style="width: 150px;float: left;"></el-input>
                                <el-popconfirm title="您的邮箱将会收到一封带有验证码的邮件" @confirm="handleCaptcha"
                                    :disabled="pwdDisabled">
                                    <el-link type="primary" :underline="false" slot="reference" :disabled="pwdDisabled"
                                        id="pwdCode">获取验证码</el-link>

                                </el-popconfirm>

                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                                <el-button @click="resetForm('ruleForm')">重置</el-button>
                            </el-form-item>
                        </el-form>
                    </div>

                </el-tab-pane>
                <el-tab-pane label="修改邮箱" name="three">
                    <div class="userP_msg">
                        <el-descriptions title="修改邮箱" :column=2
                            :contentStyle="{ marginBottom: '20px', fontSize: '16px' }">
                        </el-descriptions>
                        <el-form :model="updateEmail" status-icon :rules="rules" ref="Email" label-width="100px"
                            class="demo-ruleForm" style="margin: 0;">
                            <el-form-item label="新邮箱" prop="email">
                                <el-input type="text" placeholder="请输入新邮箱" v-model="updateEmail.email"></el-input>
                            </el-form-item>
                            <el-form-item prop="captcha" label="验证码">
                                <el-input v-model="updateEmail.captcha" placeholder="请输入验证码" clearable
                                    style="width: 150px;float: left;"></el-input>
                                <el-popconfirm title="您的旧邮箱将会收到一封带有验证码的邮件" @confirm="handleCaptcha"
                                    :disabled="disabled">
                                    <el-link type="primary" :underline="false" slot="reference" :disabled="disabled"
                                        id="count">获取验证码</el-link>

                                </el-popconfirm>

                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="setEmail('Email')">提交</el-button>
                                <el-button @click="resetForm('ruleForm')">重置</el-button>
                            </el-form-item>
                        </el-form>
                    </div>

                </el-tab-pane>

            </el-tabs>

        </el-card>
        <el-dialog title="修改头像" :visible.sync="dialogVisible" width="20%" @close="handleClose"
            style="padding-top: 30px;">
            <div>
                <el-upload class="avatar-uploader" action="/main/upload" :show-file-list="false"
                    :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"
                        style="display: flex;justify-content: center;align-items: center;"></i>
                </el-upload>
            </div>

            <el-button type="info" @click="handleClose()">关闭</el-button>
            <el-button type="primary" @click=editAvatar()>修改</el-button>
        </el-dialog>
        <el-dialog title="修改信息" :visible.sync="dialogFormVisible" width="500px" @close="handleClose">
            <el-form :model="updateForm" :rules="rules" ref="updateForm">
                <el-form-item label="头像" :label-width="formLabelWidth">
                    <el-upload class="avatar-uploader" action="/main/upload" :show-file-list="false"
                        :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"
                            style="display: flex;justify-content: center;align-items: center;"></i>
                    </el-upload>
                </el-form-item>

                <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName">
                    <el-input v-model="updateForm.userName" autocomplete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="updateForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="身份" :label-width="formLabelWidth">
                    <el-input v-model="updateForm.role" autocomplete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="手机号" :label-width="formLabelWidth" prop="phone">
                    <el-input v-model="updateForm.phone" autocomplete="off"></el-input>
                </el-form-item>
                <!-- <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                    <el-input v-model="updateForm.email" autocomplete="off"></el-input>
                </el-form-item> -->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleClose">取 消</el-button>
                <el-button type="primary" @click="editUser('updateForm')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import axios from "axios";

export default {
    data() {
        return {
            pwdDisabled: false,
            disabled: false,
            TrueCaptcha: 'error',
            updateEmail: {
                email: '',
                captcha: '',
            },
            //修改密码表单
            ruleForm: {
                password: '',
                newPassword: '',
                checkPassword: '',
                captcha: '',
            },
            //导航
            activeName: 'first',
            // 文本宽度
            formLabelWidth: '120px',
            //图片路径
            imageUrl: '',
            //修改 弹窗
            dialogFormVisible: false,
            //修改头像弹窗
            dialogVisible: false,
            //修改信息 对象
            updateForm: {},
            //个人信息
            formData: {},
            // 当前登录用户id和头像
            user: {
                id: '',
                avatar: '',
            },
            //表单验证规则
            rules: {
                captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }, { pattern: /^\d{6}$/, message: '验证码格式不正确', trigger: 'blur' }],
                userName: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ],
                phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }, { pattern: /^1[34578]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }],
                email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }, { pattern: /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/, message: '邮箱格式不正确', trigger: 'blur' }],
                password: [
                    { required: true, message: '请输入原始密码', trigger: 'blur' }
                ],
                newPassword: [
                    { required: true, message: '请输入新密码', trigger: 'blur' }
                ],
                checkPassword: [
                    { required: true, message: '请输入确认密码', trigger: 'blur' }
                ]
            }
        }
    },
    created() {

        this.user.id = localStorage.getItem('userID');
        if (localStorage.getItem('avatar') != null && localStorage.getItem('avatar') != '')
            this.imageUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
        this.getData();
    },
    methods: {
        setEmail(Email) {
            this.$refs[Email].validate((valid) => {
                if (valid) {
                    if (this.updateEmail.captcha == this.TrueCaptcha) {
                        var userID = parseInt(localStorage.getItem('userID'));
                        const data = {
                            id: userID,
                            email: this.updateEmail.email
                        }
                        axios.post("/main/editUser", JSON.stringify(data), {
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        }).then(res => {
                            this.$notify({
                                title: '成功',
                                message: '修改成功',
                                type: 'success',
                                offset: 55,
                            });
                            this.getData();
                            this.TrueCaptcha = 'error'
                            this.updateEmail = {
                                email: '',
                                captcha: '',
                            }
                            this.activeName = 'first';
                            this.$router.go(0);
                        })
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: '验证码错误'
                        });
                    }

                }
            })
        },
        pwdTime() {
            var count = document.querySelector('#pwdCode');
            //console.log(count, 'count')
            this.pwdDisabled = true
            var time = 59;
            var timer = setInterval(function () {
                // 判断剩余秒数
                if (time == 0) {
                    // 清除定时器和复原按钮
                    clearInterval(timer);
                    this.pwdDisabled = false;
                    count.innerHTML = '<span class="el-link--inner">获取验证码</span>';
                } else {
                    count.innerHTML = `<span class="el-link--inner">${time}秒后可重新获取</span>`;
                    time--;
                }
            }, 1000);

        },
        time() {
            var count = document.querySelector('#count');
            //console.log(count, 'count')
            this.disabled = true
            var time = 59;
            var timer = setInterval(function () {
                // 判断剩余秒数
                if (time == 0) {
                    // 清除定时器和复原按钮
                    clearInterval(timer);
                    this.disabled = false;
                    count.innerHTML = '<span class="el-link--inner">获取验证码</span>';
                    this.TrueCaptcha='error'
                } else {
                    count.innerHTML = `<span class="el-link--inner">${time}秒后可重新获取</span>`;
                    time--;
                }

            }.bind(this), 1000);
                

        },
        handleCaptcha() {
            
            if (this.activeName == 'three') { 
                    this.time()
                    this.$notify({
                        title: '成功',
                        message: '验证码发送中，请稍等',
                        type: 'success'
                    });
                    axios.get("/main/email?email=" + this.formData.email).then(res => {

                        this.TrueCaptcha = res.data.data
                        // console.log(this.TrueCaptcha, 'true')
                       
                    })
            } else {
                this.pwdTime()
                this.$notify({
                    title: '成功',
                    message: '验证码发送中，请稍等',
                    type: 'success'
                });
                axios.get("/main/email?email=" + this.formData.email).then(res => {

                    this.TrueCaptcha = res.data.data
                  
                    // console.log(this.TrueCaptcha, 'true')
                })
            }

        },
        //重置修改密码
        resetForm() {
            this.ruleForm = {
                password: '',
                newPassword: '',
                checkPassword: ''
            }
        },
        // 修改密码事件
        submitForm(ruleForm) {
            this.$refs[ruleForm].validate((valid) => {
                if (valid) {
                    if (this.ruleForm.captcha == this.TrueCaptcha) {
                        if (this.ruleForm.newPassword != '' && this.ruleForm.checkPassword != '')
                            if (this.ruleForm.newPassword !== this.ruleForm.checkPassword) {
                                this.$confirm('两次输入的密码不一致， 请重新输入！', '提示', {
                                    confirmButtonText: '确定',
                                    cancelButtonText: '取消',
                                    type: 'warning'
                                }).then(() => {
                                    this.ruleForm.newPassword = '';
                                    this.ruleForm.checkPassword = '';
                                    this.$refs.ruleForm.password.focus();
                                }).catch(() => {
                                    this.$notify.info({
                                        title: '消息',
                                        message: '已取消修改'
                                    });
                                });
                            } else
                                this.$confirm('此操作将修改密码, 是否继续?', '提示', {
                                    confirmButtonText: '确定',
                                    cancelButtonText: '取消',
                                    type: 'warning'
                                }).then(() => {
                                    var userID = parseInt(localStorage.getItem('userID'));
                                    const data = {
                                        id: userID,
                                        password: this.ruleForm.newPassword
                                    }
                                    axios.post("/main/editUser", JSON.stringify(data), {
                                        headers: {
                                            'Content-Type': 'application/json'
                                        }
                                    }).then(res => {
                                        this.$notify({
                                            title: '成功',
                                            message: '修改成功,请重新登录',
                                            type: 'success',
                                            offset: 55,
                                        });
                                        localStorage.setItem('jwt', '');
                                        this.$router.push('/')
                                    }).catch(err => {
                                        this.$notify.error({
                                            title: '错误',
                                            message: '修改失败！请检查原密码或修改后的密码是否一致'
                                        });
                                    })

                                }).catch(() => {
                                    this.$notify.info({
                                        title: '消息',
                                        message: '已取消修改'
                                    });
                                });
                        else {
                            this.$notify.info({
                                title: '消息',
                                message: '请输入修改后的密码！'
                            });
                        }
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: '验证码错误'
                        });
                    }
                }
            })
        },
        //返回
        goBack() {
            this.$router.go(-1)
        },
        outLogin() {
            localStorage.setItem('jwt', '');
            this.$router.push('/')
        },
        // 修改用户信息
        editUser(updateForm) {
            const data = {
                id: this.user.id,
                avatar: this.user.avatar,
                name: this.updateForm.name,
                phone: this.updateForm.phone,
                email: this.updateForm.email
            }
            this.$refs[updateForm].validate((valid) => {
                if (valid) {
                    this.updateForm.avatar = localStorage.getItem('avatar')
                    axios.post(`/main/editUser`, JSON.stringify(data), {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).then(res => {
                        this.$notify({
                            title: '成功',
                            message: '修改成功',
                            type: 'success',
                            offset: 55,
                        });
                        if (this.user.avatar != null && this.user.avatar != '')
                            localStorage.setItem('avatar', this.user.avatar)
                        this.imageUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
                        this.dialogFormVisible = false;
                        location.reload();
                    })
                }
            })


        },
        // 初始化数据
        getData() {
            axios.get("/main/getUser", { params: { id: this.user.id } }).then(res => {
                console.log(res, 'user');
                this.formData = res.data.data.rows[0];
                this.updateForm = res.data.data.rows[0];

            })
        },
        // 弹窗关闭事件
        handleClose() {
            //this.imageUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
            this.dialogVisible = false;
            this.dialogFormVisible = false
            this.imageUrl = `/main/download?name=${localStorage.getItem('avatar')}`;
            this.user.avatar = localStorage.getItem('avatar')
            this.getData();
        },
        // 修改头像
        editAvatar() {
            const data = {
                id: this.user.id,
                avatar: this.user.avatar
            }
            axios.post(`/main/editUser`, JSON.stringify(data), {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                localStorage.setItem('avatar', this.user.avatar)
                this.dialogVisible = false;
            })
            this.$notify({
                title: '成功',
                message: '修改成功',
                type: 'success',
                offset: 55,
            });
            location.reload();
        },
        // 头像上传成功事件
        handleAvatarSuccess(res, file) {
            this.imageUrl = `/main/download?name=${file.name}`;
            this.user.avatar = file.name;
        },
        // 头像上传规则
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        }
    }
}
</script>
<style>
.userP_card {
    width: 500px;
    height: 520px;
    margin: 30px auto;

}

.userP_msg {

    padding: 10px;

}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;

}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>