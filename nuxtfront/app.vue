<script setup lang="ts">
import Home from '@/components/Home.vue'
import HeaderPart from '@/components/HeaderPart.vue'

const store = usePagesStore()
const { fetchPages } = store

useHead({
  title: 'Ari Adari - web developer portfolio',
})

await fetchPages()
</script>

<template>
  <div class="m-0 h-full w-full">
    <HeaderPart />
    <Home />
  </div>
</template>
