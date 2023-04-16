<template>
  <div class="mainpage v-container--fluid">
    <v-layout row wrap>
        <v-card>
          <v-card-title>
            <v-layout row wrap>
                <h1>Home</h1>
            </v-layout>
          </v-card-title>
          <v-card-text>
                <v-card v-for="page in pages" :key="page.id">
                    <v-card-title>
                        <h2>{{ page.title }}</h2>
                    </v-card-title>
                    <v-card-text>
                        <div v-html="page.content">

                        </div>
                    </v-card-text>
                </v-card>
          </v-card-text>
        </v-card>
    </v-layout>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      pages: []
    }
  },
  mounted () {
    fetch('http://localhost:8082/get/allpages',
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json'
        }
      }
    )
      .then(response => response.json())
      .then(data => { this.pages = data })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
