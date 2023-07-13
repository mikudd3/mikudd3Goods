//热销
new Vue({
    el: "#hotgame",
    data() {
        return {
            games: [
                {
                    image: "uploads/1.jpg",
                    name: "无主之地3",
                    price: "￥199",
                },
            ]
        }
    },
    mounted() {
        this.gethot();
    },
    methods: {
        gethot() {
            axios({
                method: "get",
                url: "/goods/getHot"
            }).then(resp => {
                let r = resp.data;
                this.games = r.data.records;
            })
        }
    }
})


//热销
new Vue({
    el: "#goodgame",
    data() {
        return {
            games: [
                {
                    image: "uploads/1.jpg",
                    name: "无主之地3",
                    price: "￥199",
                },
            ]
        }
    },
    mounted() {
        this.gethot();
    },
    methods: {
        gethot() {
            axios({
                method: "get",
                url: "/goods/getGoodgame"
            }).then(resp => {
                let r = resp.data;
                this.games = r.data.records;
            })
        }
    }
})

//打折
new Vue({
    el: "#dazhegame",
    data() {
        return {
            games: [
                {
                    image: "uploads/1.jpg",
                    name: "无主之地3",
                    price: "￥199",
                },
            ]
        }
    },
    mounted() {
        this.gethot();
    },
    methods: {
        gethot() {
            axios({
                method: "get",
                url: "/goods/getDazhegame"
            }).then(resp => {
                let r = resp.data;
                this.games = r.data.records;
            })
        }
    }
})