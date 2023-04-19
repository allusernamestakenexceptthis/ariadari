<template>
    <header>
        <v-app-bar
            color="primary"
            prominent
        >
            <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

            <v-toolbar-title>Ari Adari</v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn variant="text" icon="mdi-magnify"></v-btn>

            <v-btn variant="text" icon="mdi-filter"></v-btn>

            <v-btn variant="text" icon="mdi-dots-vertical"></v-btn>
        </v-app-bar>

        <v-navigation-drawer
            v-model="drawer"
            location="left"
            dark
            temporary
        >
            <v-list
            :items="items"
            ></v-list>
        </v-navigation-drawer>
        <div class="h-100 w-100">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="header-content">
                            <div class="header-content-inner">
                                <h1 id="homeHeading">Welcome To Our Studio!</h1>
                                <hr>
                                <p>IT'S NICE TO MEET YOU</p>
                                <a href="#about" class="btn btn-primary btn-xl page-scroll">Tell Me More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>
<script allowJs>

export default {
  name: 'HeaderPart',
  data () {
    return {
      drawer: false,
      items: [
        { title: 'Home', icon: 'mdi-home' },
        { title: 'About', icon: 'mdi-account' },
        { title: 'Services', icon: 'mdi-wrench' },
        { title: 'Contact', icon: 'mdi-phone' }
      ]
    }
  }
}

</script>
