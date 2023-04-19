<template>
    <v-sheet width="100%" border="0" elevation="0">
        <v-col class="text-left">
            <div  v-animate-onscroll="{down: 'animated flip'}" v-for="page in pages" :key="page.id" :id="page.slug" :ref="page.slug">
                <v-container>
                    <h2>{{ page.title }}</h2>
                    <v-sheet width="100%" rounded="lg" elevation="3" color="white" class="px-5 py-5">
                        <div v-html="page.content"></div>
                        asdf<br/>
                        asdf<br/>
                        asdf<br/>
                        asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>
                        asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>asdf<br/>

                    </v-sheet>
                </v-container>
            </div>
            <v-card height="50" class="background-cover"></v-card>
        </v-col>
    </v-sheet>
</template>

<script>
export default {
  name: 'HomePage',

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
.sheetPaper{
    opacity:0.8;
}
h2 {
    color: #42b983;
    font-size: 2.5rem;
}
p {
    font-size: 1.5rem;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
.background-cover {
    background: url(@/assets/images/background.jpg) repeat;
    background-size: contain;
    top: 0;
}
</style>
