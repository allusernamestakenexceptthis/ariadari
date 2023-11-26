import { RuntimeConfig } from 'nuxt/schema';
import { defineStore } from 'pinia'
import type { Page } from '@/types/page'

export const usePagesStore = defineStore('pages', () => {
    const pages = ref<Page[]>([])
    const isLoaded = ref(false)
    
    const fetchPages = async () => {
        if (isLoaded.value) {
            return
        }

        const response = await useFetch(useRuntimeConfig().public.API_URL + '/get/allpages',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            }
        )
    

        pages.value = response.data.value as Page[];
        isLoaded.value = true
    }

    return {
        pages,
        isLoaded,
        fetchPages
    }
})