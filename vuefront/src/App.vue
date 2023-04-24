<template>
    <v-app>
        <v-layout>
            <div class="w-100 h-100 m-0">
                <headerPart/>
                <v-row>
                    <v-col>
                        <home/>
                    </v-col>
                </v-row>
            </div>
        </v-layout>
    </v-app>

</template>

<script  lang="ts">
import Home from './pages/Home.vue'
import HeaderPart from '@/components/HeaderPart.vue'
import { useHead } from '@vueuse/head'

export default {
  name: 'App',
  setup() {

    useHead({
        title: 'Ari Adari - web developer portfolio'
    });
  },
  components: {
    Home,
    HeaderPart
  },
  mounted () {
    this.$store.dispatch('pages/getPages')
  },
  
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
