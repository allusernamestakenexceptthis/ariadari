// https://nuxt.com/docs/api/configuration/nuxt-config
import dotenv from 'dotenv';
dotenv.config();

export default defineNuxtConfig({
  compatibilityDate: '2025-01-01',
  devtools: { enabled: true },
  devServer: {
    port: 8086,
  },
  ssr: true,
  css: ['~/assets/css/global.scss'],
  modules: [
    '@nuxtjs/tailwindcss',
    '@pinia/nuxt',
  ],
  runtimeConfig: {
    public: {
      API_URL: process.env.API_URL || '',
    },
  },
  vite: {
    optimizeDeps: {
      exclude: ['gsap'],
    },
  },
})
