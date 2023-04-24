<template>
    <main class="v-100">
        <v-sheet width="100%" border="0" elevation="0">
            <v-col class="text-left">
                <template v-for="(page,indx) in pages" :key="page.id">
                    <div :id="page.slug" :ref="page.slug">
                        <h2 v-animate-onscroll="{down: (indx!=0)?'animate__animated animate__fadeInUp':''}">{{ page.title }}</h2>
                        <v-container fluid>
                            <v-sheet width="100%" rounded="lg" elevation="3" color="white" class="px-5 py-5" v-animate-onscroll="{down: (indx!=0)?'animate__animated animate__pulse':''}">
                                <div v-html="page.content"></div>
                            </v-sheet>
                        </v-container>
                    </div>
                </template>
            </v-col>
        </v-sheet>
    </main>
</template>

<script lang="ts">
import { ref } from 'vue';
import { mapGetters} from 'vuex';
import gsap from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
gsap.registerPlugin(ScrollTrigger);


export default {
  name: 'HomePage',
  setup() {
    const ctx = ref<any>(null);
    const main = ref();



    return {
        ctx,
        main
    }
  },

  computed: {
    ...mapGetters('pages', ['pages'])
  },

  methods: {
    handleVideoPlay() {
        const video: any = this.$refs['videoBack'];
        video[0].pause();
    },
        
  },

  mounted() {

    //window.addEventListener('scroll', this.handleScroll);
    this.ctx = gsap.context((self) => {
            if (self == null || self.selector == null){
                return;
            }
            const boxes = self.selector('.box');
            boxes.forEach((box: any) => {
                gsap.to(box, {
                    x: 350,
                    y:0,
                    scrollTrigger: {
                        trigger: box,
                        start: 'bottom bottom',
                        end: 'top 10%',
                        scrub: true,
                    }
                });
            });
    }, this.main); // <- Scope!

  },

  onMounted() {
    this.ctx.revert();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.sheetPaper{
    opacity:0.8;
}
h2 {
    text-align: center;
    font-size: 2.5rem;
    padding: 2rem;
    text-transform: capitalize;
}
p {
    font-size: 1.5rem;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
.background-cover {
    background: url(@/assets/images/background.jpg) repeat;
    background-size: cover;
    top: 0;
}

.box {
    background-color: green;
    width: 100px;
    height: 100px;
    border-radius: 10px;
    font-size: 24px;
    text-align: center;
    line-height: 100px;
  }


</style>
