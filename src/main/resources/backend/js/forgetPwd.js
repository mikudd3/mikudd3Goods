import th from "../plugins/element-ui/src/locale/lang/th";

new Vue({
    el: "#app",
    data: {
        user: {
            username: "",
            password: "",
        },
        username: "",
        password1: "",
        password2: "",
        checkCode: "",
        imgCode: "/checkCode/code?" + new Date().getMilliseconds()
    },
    mounted() {
        this.changeImg();
    },
    methods: {
        validateForm() {
            if (!this.password1) {
                alert("密码不能为空");
                return false;
            } else if (this.password1 !== this.password2) {
                alert("两次输入的密码不一致");
                return false;
            } else if (!this.password2) {
                alert("密码不能为空");
                return false;
            } else if (!this.checkCode) {
                alert("验证码不能为空");
                return false;
            } else {
                this.user.username = this.username;
                this.user.password = this.password1;
                return true;
            }

        },
        forgetPwd() {
            if (this.validateForm()) {
                // 这里可以提交表单数据
                console.log("密码验证通过，提交表单");
                axios({
                    method: "post",
                    url: "/user/forgetPassword",
                    data: {
                        user: this.user,
                        checkCode: this.checkCode
                    }
                }).then(Response => {
                    let res = Response.data;
                    if (res.code == 1) {
                        //密码修改成功后，跳转到登录页面
                        location.href = "login.html";
                    } else {
                        this.$message.error(res.msg)
                    }
                    // if (Response.data == "checkError") {
                    //     this.$message.error("验证码输入错误")
                    // } else if (Response.data == "userNotFound") {
                    //     this.$message.error("用户未注册")
                    // } else if (Response.data == "success") {
                    //     this.$message.success("修改成功")
                    // }
                }).catch(function () {
                    alert("catch")
                }).finally(function () {
                    alert("finally")
                })
            }
        },
        changeImg() {
            this.imgCode = "/checkCode/code?" + new Date().getMilliseconds();
        }
    }


})