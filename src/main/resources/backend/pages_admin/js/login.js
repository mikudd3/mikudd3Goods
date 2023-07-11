new Vue({
    el: "#app",
    data() {
        return {
            username: "",
            password: "",
        }

    },
    methods: {
        dosubmit() {
            axios({
                url: "/admin/login",
                method: "post",
                data: {
                    username: this.username,
                    password: this.password
                }
            }).then(resp => {
                if(resp.data.code == 1){
                    //登录成功
                    location.href="main.html"
                }else {
                    this.$message.error(resp.data.msg)
                }
            })


            /*axios.post("http://localhost:8080/login", "username=" + this.employee.username + "&password=" + this.employee.password)
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