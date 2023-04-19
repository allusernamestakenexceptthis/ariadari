<template>
    <v-app>
        <v-layout>
            <div class="w-100 h-100 m-0">
                <headerPart/>

                <v-container>
                    <v-row>
                        <home/>
                    </v-row>
                </v-container>
            </div>
        </v-layout>
    </v-app>

</template>

<script>
import Home from './pages/Home.vue'
import HeaderPart from '@/components/HeaderPart'

export default {
  name: 'App',
  components: {
    Home,
    HeaderPart
  },
  mounted () {
    window.addEventListener('scroll', function () {
      const video = this.$refs.video_background
      const scrollPosition = window.pageYOffset
      video.currentTime = scrollPosition / 100
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
  scroll-behavior: smooth;
}
</style>
