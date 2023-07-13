new Vue({
    el: "#app",
    data() {
        return {
            checkCode: "",
            user: {
                username: "",
                password: "",
                phone: "",
                email: "",
            },
            imgCode: "/checkCode/code?" + new Date().getMilliseconds()
        }
    },
    methods: {
        validateForm() {
            if (!this.user.username) {
                alert("账号不能为空");
                return false;
            } else if (!this.user.password) {
                alert("密码不能为空");
                return false;
            } else if (!this.user.phone) {
                alert("电话不能为空");
                return false;
            } else if (!this.checkCode) {
                alert("验证码不能为空");
                return false;
            } else if (!this.user.email) {
                alert("邮箱不能为空")
                return false;
            } else {
                return true;
            }
        },
        dosubmit() {
            if (this.validateForm()) {
                axios({
                    method: "post",
                    url: "/user/register",
                    data: {
                        user: this.user,
                        checkCode: this.checkCode,
                    },
                }).then(resp => {
                    resp = resp.data;
                    if (resp.code == 1) {
                        //表示注册成功,则跳转到登录页面
                        this.$message.success("注册成功");
                        location.href = "login.html";
                    } else {
                        this.$message.error(resp.msg);
                    }
                }).catch(function () {
                    alert("这是catch")
                })

            }
        },
        changeImg() {
            this.imgCode = "/checkCode/code?" + new Date().getMilliseconds();
        }

    }
})