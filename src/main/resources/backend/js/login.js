new Vue({
    el: "#app",
    data: {
        user: {
            username: "",
            password: "",
        },

    },
    methods: {
        dosubmit() {
            axios({
                url: "/user/login",
                method: "post",
                data: this.user
            }).then(resp => {
                let r = resp.data;
                console.log(r)
                if (r.code == 1) {
                    //登录成功
                    location.href = "index.html";
                    localStorage.setItem('user', r.data.id);
                } else {
                    console.log(resp)
                    this.$message.error(r.msg)
                }
            })


            /*axios.post("/login", "username=" + this.user.username + "&password=" + this.user.password)
                .then(resp => {
                    // alert("这是then")
                    console.log(resp.data)
                    if (resp.data == 1) {
                        location.href = '../pages_admin/admin/main.html';
                    } else if (resp.data == 2) {
                        location.href = '../index.html';
                    } else if (resp.data == 0) {
                        this.$message.error("登录失败");
                    }
                }).catch(function () {
                // alert("这是catch")
            }).finally(function () {
                // alert("这是finally")
            })*/
        },

    },
})