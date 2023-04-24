import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';


// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    server: {
        port: 8086,
        proxy: {
            '/get': {
                target: 'http://localhost:8082',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, ''),
            },
        }
    },
    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src'),
        },
    },
    optimizeDeps: {
        exclude: ['gsap']
    }
})
