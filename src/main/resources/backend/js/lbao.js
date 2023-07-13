new Vue({
    el: '#slider',
    data() {
        return {
            slides: [
                {url: 'image/slider01.jpg', title: '来感受类魂开放世界的无限魅力', color: '#644344'},
                {url: 'image/slider02.jpg', title: '去无主之地体验不一样的人生', color: '#2B231A'},
            ],
            currentIndex: 0,
            timerId: null
        };
    },
    computed: {
        currentSlide() {
            return this.slides[this.currentIndex];
        }
    },
    mounted() {
        this.fetchSlides();
    },
    methods: {
        fetchSlides() {
            axios({
                url: "/lbt/getAll",
                method: "get"
            }).then(response => {
                let r = response.data;
                this.slides = r.data;
                console.log(r.data)
                this.startAutoPlay();
            }).catch(error => {
                console.error('Error fetching slides:', error);
            });
        },
        nextSlide() {
            this.currentIndex = (this.currentIndex + 1) % this.slides.length;
        },
        prevSlide() {
            this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
        },
        startAutoPlay() {
            this.timerId = setInterval(() => {
                this.nextSlide();
            }, 1500);
        },
        stopAutoPlay() {
            clearInterval(this.timerId);
        }
    },
    beforeDestroy() {
        this.stopAutoPlay();
    }
})