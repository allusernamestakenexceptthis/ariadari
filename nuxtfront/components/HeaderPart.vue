<script setup lang="ts">
import { storeToRefs } from 'pinia'
import gsap from 'gsap'
import { ScrollToPlugin } from 'gsap/ScrollToPlugin'

const store = usePagesStore()
const { pages } = storeToRefs(store)

if (import.meta.client) {
  gsap.registerPlugin(ScrollToPlugin)
}

const drawer = ref(false)
const titleShown = ref(true)
const subtitleShown = ref(true)
const currentTextIndex = ref(0)
const texts = ref([
  {
    title: 'Programmer and IT developer',
    subtitle: 'Based in Japan',
  },
  {
    title: 'Welcome to my website',
    subtitle: 'IT developer and programmer',
  },
  {
    title: '17 years experience in web development',
    subtitle: 'PHP, JavaScript, HTML, CSS, SQL, VUE.JS, Python etc.',
  },
  {
    title: 'Currently working on a new project',
    subtitle: 'And learning Java Spring Boot',
  },
  {
    title: 'This site was made in Vue.js',
    subtitle: 'And Java Spring Boot',
  },
  {
    title: 'Contact me',
    subtitle: '',
  },
])
const currentItem = ref('')

const text = computed(() => texts.value[currentTextIndex.value])

const navItems = computed(() =>
  pages.value.map((item: any) => ({
    ...item,
    icon: item.icon || '',
    link: item.page_slug,
    active: currentItem.value === item.path,
  })),
)

const goArea = (e: Event, link: string) => {
  if (link.substring(0, 1) != '#') {
    return
  }

  e.preventDefault()
  drawer.value = false
  gsap.to(window, {
    duration: 0.3,
    scrollTo: { y: link, offsetY: 30 },
  })
}

onMounted(() => {
  setInterval(() => {
    currentTextIndex.value++
    if (currentTextIndex.value >= texts.value.length) {
      currentTextIndex.value = 0
    }
    titleShown.value = false
    subtitleShown.value = false
    setTimeout(() => {
      titleShown.value = true
      setTimeout(() => {
        subtitleShown.value = true
      }, 500)
    }, 500)
  }, 3000)
})
</script>

<template>
  <header class="topheader">
    <div class="fixed left-0 top-0 z-40 flex w-full items-center gap-3 bg-black px-3 py-3 text-left">
      <button
        type="button"
        class="inline-flex h-10 w-10 items-center justify-center rounded text-white hover:bg-white/10"
        aria-label="Open navigation"
        @click.stop="drawer = !drawer"
      >
        <svg class="h-6 w-6" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true">
          <path d="M3 6h18v2H3V6zm0 5h18v2H3v-2zm0 5h18v2H3v-2z" />
        </svg>
      </button>
      <div class="titlebar">Ari Adari</div>
    </div>

    <video
      preload="auto"
      autoplay
      ref="video_background"
      loop
      muted
      playsinline
      class="video-background"
    >
      <source src="@/assets/videos/EarthAndMoon.mp4" type="video/mp4" />
    </video>

    <div class="header-content">
      <div class="header-content-inner bigtext">
        <div :key="text.title">
          <h1
            class="homeHeading animate__animated animate__lightSpeedInLeft"
            v-show="titleShown"
          >
            {{ text.title }}
          </h1>
          <h2
            class="homesubHeading animate__animated animate__bounceInLeft"
            v-show="subtitleShown"
          >
            {{ text.subtitle }}
          </h2>
        </div>
      </div>
    </div>
  </header>

  <Teleport to="body">
    <div
      v-if="drawer"
      class="fixed inset-0 z-50 bg-black/40"
      @click="drawer = false"
    />
    <aside
      class="fixed left-0 top-0 z-50 h-full w-72 transform bg-white shadow-xl transition-transform duration-200"
      :class="drawer ? 'translate-x-0' : '-translate-x-full'"
      @click.stop
    >
      <nav class="px-2 py-4">
        <ul class="space-y-1">
          <li v-for="(page, i) in navItems" :key="i">
            <button
              type="button"
              class="flex w-full items-center gap-3 rounded px-3 py-2 text-left text-lg text-slate-800 hover:bg-slate-100"
              @click="goArea($event, '#' + page.slug)"
            >
              <span
                v-if="page.icon"
                class="inline-block h-2 w-2 rounded-full bg-orange-500"
                aria-hidden="true"
              />
              {{ page.slug }}
            </button>
          </li>
        </ul>
      </nav>
    </aside>
  </Teleport>
</template>

<style scoped>
header.topheader {
  position: relative;
  isolation: isolate;
  width: 100%;
  max-height: 700px;
  min-height: 500px;
  background-color: #000;
  top: 0;
  overflow: hidden;
  margin-bottom: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}
.video-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 0;
  pointer-events: none;
}
.header-content {
  position: relative;
  z-index: 1;
  width: 100%;
  padding: 0 1.5rem;
  text-align: center;
}
.titlebar,
.bigtext {
  font-size: 2rem;
  font-weight: bold;
  color: #dedede;
  text-shadow: 2px 2px 2px #000000;
  font-style: italic;
}
.homeHeading {
  font-size: 3rem;
  font-weight: bold;
  color: white;
  text-shadow: 2px 2px 2px #000000;
  text-align: center;
}
.homesubHeading {
  font-size: 1.5rem;
  text-align: center;
  color: #dedede;
  text-shadow: 2px 2px 2px #000000;
}
</style>
