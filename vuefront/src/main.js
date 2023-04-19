import { createApp } from 'vue'
import App from './App.vue'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, mdi } from 'vuetify/iconsets/mdi'
import { md3 } from 'vuetify/blueprints'
import VueAnimateOnScroll from 'vue3-animate-onscroll'

import '@mdi/font/css/materialdesignicons.min.css'

const vuetify = createVuetify({
  defaultSet: mdi,
  aliases,
  blueprint: md3,
  sets: {
    mdi
  },
  VueAnimateOnScroll,
  components,
  directives
})

createApp(App).use(vuetify).mount('#app')
