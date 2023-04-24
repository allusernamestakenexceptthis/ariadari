import { createApp } from 'vue'
import App from './App.vue'

// material design icons
import '@mdi/font/css/materialdesignicons.min.css'

//global style
import '@/assets/css/global.scss'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, mdi } from 'vuetify/iconsets/mdi'
import { md3 } from 'vuetify/blueprints'

//Vue animate
import VueAnimateOnScroll from 'vue3-animate-onscroll'

// Store
import { store } from './store/store.ts'

// for header control
import { createHead } from "@vueuse/head"

// for smooth scroll
import VueSmoothScroll from 'vue3-smooth-scroll'

const head = createHead();

const vuetify = createVuetify({
  blueprint: md3,
  
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
        mdi
    }
  },
  components,
  directives
})

createApp(App)
  .use(VueAnimateOnScroll as any)
  .use(vuetify)
  .use(store)
  .use(head)
  .use(VueSmoothScroll)
  .mount('#app')
