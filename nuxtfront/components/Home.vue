<script setup lang="ts">
import { storeToRefs } from 'pinia'
import gsap from 'gsap'
import { ScrollTrigger } from 'gsap/ScrollTrigger'

const store = usePagesStore()
const { pages } = storeToRefs(store)

if (import.meta.client) {
  gsap.registerPlugin(ScrollTrigger)
}

const ctx = ref<any>(null)
const main = ref()

const config = useRuntimeConfig();
const API_URL = config.public.API_URL;

function processPageContent(content: string) {
  return content.replaceAll(`../../../uimages`, API_URL + '/uimages');
}

onMounted(() => {
  if (ctx.value != null) {
    ctx.value.revert()
  }
  ctx.value = gsap.context((self) => {
    if (self == null || self.selector == null) {
      return
    }
    const boxes = self.selector('.box')
    boxes.forEach((box: any) => {
      gsap.to(box, {
        x: 350,
        y: 0,
        scrollTrigger: {
          trigger: box,
          start: 'bottom bottom',
          end: 'top 10%',
          scrub: true,
        },
      })
    })
  }, main.value)
})

onBeforeUnmount(() => {
  if (ctx.value != null) {
    ctx.value.revert()
  }
})
</script>

<template>
  <main ref="main" class="w-full text-left">
    <template v-for="(page, indx) in pages" :key="page.id">
      <section :id="page.slug">
        <h2
          v-animate-onscroll="{
            down: indx != 0 ? 'animate__animated animate__fadeInUp' : '',
          }"
        >
          {{ page.title }}
        </h2>
        <div class="w-full px-4 sm:px-6">
          <div
            class="w-full rounded-lg bg-white px-5 py-5 shadow-md"
            v-animate-onscroll="{
              down: indx != 0 ? 'animate__animated animate__pulse' : '',
            }"
          >
            <div v-html="processPageContent(page.content)"></div>
          </div>
        </div>
      </section>
    </template>
  </main>
</template>

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
h2 {
  text-align: center;
  font-size: 2.5rem;
  padding: 0.8rem;
  text-transform: capitalize;
}
p {
  font-size: 1.5rem;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
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
