import VueAnimateOnScroll from 'vue3-animate-onscroll'
import '@/assets/css/animate.min.css'
import '@/assets/css/animate-override.css'

export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.vueApp.use(VueAnimateOnScroll as any)
});
