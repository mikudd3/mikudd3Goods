new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            dataList: [
                {
                    name: "张三",
                    username: "zhangsan",
                    isAdmin: 0,
                    status: 0,
                    CreateTime: "2023-7-7"
                }
            ],//当前页要展示的列表数据
            formData: {
                name: "",
                username: "",
                password: "",
                status: false,
            },//表单数据
            dialogFormVisible: false,//控制表单是否可见
            dialogFormVisible4Edit: false,//编辑表单是否可见
            rules: {//校验规则
                username: [{required: true, message: '用户名为必填项', trigger: 'blur'}],
            },
            name: "",
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getAll();
    },
    methods: {
        //列表
        getAll() {
            axios({
                method: "post",
                url: "/admin/page",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    name: this.name,
                }
            }).then((res) => {
                this.dataList = res.data.data.records;
                this.totalCount = res.data.data.total;
                console.log(this.totalCount)
            })

        },
        //弹出添加窗口
        handleCreate() {
            this.dialogFormVisible = true;
            this.resetForm();
        },
        //重置表单
        resetForm() {
            this.formData = {};
        },
        //添加
        handleAdd() {
            this.formData.status = this.formData.status ? 1 : 0;
            // console.log(this.formData.status);
            //发送请求
            axios({
                method: "POST",
                url: "/admin/add",
                data: this.formData
            }).then((res) => {
                if (res.data.code == 1) {
                    //添加成功
                    this.$message.success("添加成功");
                    //关闭窗口
                    this.dialogFormVisible = false;
                } else {
                    //添加失败
                    this.$message.success(res.data.msg);
                }

            }).finally(() => {
                this.getAll();
            })
        },
        //弹出编辑窗口
        handleUpdate(row) {
            //根据id查询数据
            axios({
                method: "get",
                url: "/admin/selectById?id=" + row.id,
            }).then((res) => {
                this.formData = res.data.data;
                this.dialogFormVisible4Edit = true;
            })

        },
        //编辑
        handleEdit() {
            this.formData.status = this.formData.status ? 1 : 0;
            //发送请求
            axios({
                method: "post",
                url: "/admin/update",
                data: this.formData
            }).then((res) => {
                //弹窗
                this.$message.success("修改成功")
                //操作成功，关闭窗口
                this.dialogFormVisible4Edit = false;
            }).finally(() => {
                this.getAll();
            })
        },

        // 删除
        handleDelete(row) {
            //弹出提示框
            this.$confirm("此操作将永久删除该数据，是否继续？", "提示", {
                type: "info"
            }).then(() => {
                //做删除业务
                //根据id查询数据
                axios({
                    method: "post",
                    url: "/admin/delete?id=" + row.id,
                }).then((res) => {
                    if (res.data.code == 1) {
                        this.$message.success(res.data.msg);
                    } else {
                        this.$message.error(res.data.msg);
                    }
                }).finally(() => {
                    this.getAll();
                });

            }).catch(() => {
                this.$message.info("取消删除")
            })

        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getAll();
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getAll();
        }
    }
})