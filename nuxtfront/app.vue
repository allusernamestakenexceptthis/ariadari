<script setup lang="ts">

import Home from '@/components/Home.vue'
import HeaderPart from '@/components/HeaderPart.vue'

const store = usePagesStore();
const { fetchPages } = store;


useHead({
    title: 'Ari Adari - web developer portfolio'
});

await fetchPages();

</script>
<template>
    <v-app>
      <v-layout>
          <div class="w-100 h-100 m-0">
              <HeaderPart/>
              <v-row>
                  <v-col>
                      <Home/>
                  </v-col>
              </v-row>
          </div>
      </v-layout>
  </v-app>
</template>

