<script setup lang="ts">
import { storeToRefs } from 'pinia';
import gsap from 'gsap';
import { ScrollToPlugin } from 'gsap/ScrollToPlugin';

const store = usePagesStore();
const { pages } = storeToRefs(store);

gsap.registerPlugin(ScrollToPlugin);

const drawer = ref(false);
const titleShown = ref(true);
const subtitleShown = ref(true);
const currentTextIndex = ref(0);
const items = ref([
  { title: 'Home', icon: 'mdi-home' },
  { title: 'About', icon: 'mdi-account' },
  { title: 'Services', icon: 'mdi-wrench' },
  { title: 'Contact', icon: 'mdi-phone' }
]);
const texts = ref([
  {
    title: 'Programmer and IT developer',
    subtitle: 'Based in Japan'
  },

  {
    title: 'Welcome to my website',
    subtitle: 'IT developer and programmer'
  },

  {
    title: '17 years experience in web development',
    subtitle: 'PHP, JavaScript, HTML, CSS, SQL, VUE.JS, Python etc.'
  },

  {
    title: 'Currently working on a new project',
    subtitle: 'And learning Java Spring Boot'
  },

  {
    title: 'This site was made in Vue.js',
    subtitle: 'And Java Spring Boot'
  },

  {
    title: 'Contact me',
    subtitle: ''
  }
]);
const currentItem = ref('');

const text = computed(() => {
  return texts.value[currentTextIndex.value];
});

const navItems = computed(() => {
  return pages.value.map((item: any) => {
    return {
      ...item,
      icon: item.icon || '',
      link: item.page_slug,
      active: currentItem.value === item.path
    }
  })
});

const changeText = (index: number) => {
  text.value = texts.value[index];
};

const goArea = (e: Event, link: string) => {
  if (link.substring(0,1) != "#") {
    return;
  }

  e.preventDefault();
  gsap.to(window, {
    duration: 0.3,
    scrollTo: { y: link, offsetY: 30 }
  });
};

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
});

</script>
<template>
    <header class="topheader">
        <v-app-bar
            color="black"
            elevation="0"
            rounded
            class="v-app-bar--fixed"
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

            <v-toolbar-title class="titlebar text-left">Ari Adari</v-toolbar-title>
            <!--
            <v-btn variant="text" icon="mdi-magnify"></v-btn>

            <v-btn variant="text" icon="mdi-filter"></v-btn>

            <v-btn variant="text" icon="mdi-dots-vertical"></v-btn>-->

        </v-app-bar>
       
        <video preload="auto" autoplay="true" ref="video_background" loop="true" muted="true" class="video-background">
            <source src="@/assets/videos/EarthMoonZoomS.mp4" type="video/mp4">
        </video>
        <div>
            <div class="container">
                <div class="row">
                    <div class="header-content">
                        <div class="header-content-inner bigtext">
                            <div :key="text.title">
                                <h1 class="homeHeading animate__animated animate__lightSpeedInLeft" v-show="titleShown">{{ text.title }}</h1>
                                <h2 class="homesubHeading animate__animated animate__bounceInLeft" v-show="subtitleShown">{{ text.subtitle }}</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <v-navigation-drawer
            v-model="drawer"
            location="left"
            temporary
            class="nav-drawer-items position-fixed top-0 left-0"
        >
        <v-list
            density="compact"
            nav
            >
                <v-list-item
                    v-for="(page, i) in navItems"
                    :key="i"
                    :value="page.slug"
                    base-color="primary"
                    @click="goArea($event, '#'+page.slug)"
                    ripple
                    :link="true"
                >
                    <template v-slot:prepend>
                        <v-icon v-if="page.icon" :icon="page.icon" color="orange"></v-icon>
                    </template>
                    <v-list-item-title>
                       
                        {{ page.slug }}
                    
                    </v-list-item-title>
                    
                </v-list-item>
            </v-list>
    </v-navigation-drawer>
</template>
<style scoped>
header.topheader{
    position: relative;
    width: 100%;
    max-height: 700px;
    min-height: 500px;
    background-size: contain;
    top: 0;
    overflow:hidden;
    margin-bottom:0;
}
.video-background {
    position: absolute;
    top: 0;
    left: 0;
    min-width: 100%;
    min-height: 100%;
    z-index: -1;
}
.container{
    margin-top:10%;
    width:100%;
}
.titlebar, .bigtext{
    font-size: 2rem;
    font-weight: bold;
    color:#dedede;
    text-shadow: 2px 2px 2px #000000;
    font-style: italic;
}
.homeHeading{
    font-size: 3rem;
    font-weight: bold;
    color:white;
    text-shadow: 2px 2px 2px #000000;
}
.homesubHeading{
    font-size:1.5rem;
}
.v-app-bar--fixed{
    position: fixed !important;
    top: 0;
    left: 0;
}

body {
    scroll-behavior: smooth;
}

</style>
