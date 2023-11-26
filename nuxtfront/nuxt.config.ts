import { RuntimeConfig } from 'nuxt/schema';
// https://nuxt.com/docs/api/configuration/nuxt-config
import path from 'path';
import vuetify, { transformAssetUrls } from 'vite-plugin-vuetify'

export default defineNuxtConfig({
  devtools: { enabled: true },
  devServer: {
    port: 8086,
  },
  build: {
    transpile: ['vuetify'],
  },
  ssr: true,
  css: ['~/assets/css/global.scss'],
  modules: [
    (_options, nuxt) => {
      nuxt.hooks.hook('vite:extendConfig', (config) => {
        // @ts-expect-error
        config.plugins.push(vuetify({ autoImport: true }))
      })
    },
    '@pinia/nuxt'
  ],
  runtimeConfig: {
    public: {
      API_URL: process.env.API_URL || '',
    },
  }, 
  vite: {
    resolve: {
      alias: {
        "@": path.resolve(__dirname, "./"),
      },
    },
    optimizeDeps: {
      exclude: ["gsap"],
    },
    vue: {
      template: {
        transformAssetUrls,
      },
    },
  },
});
