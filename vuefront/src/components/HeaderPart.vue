<template>
    <header>
        <v-app-bar
            color="transparent"
            elevation="24"
            rounded
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

            <v-toolbar-title class="titlebar text-left">Ari Adari</v-toolbar-title>

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
                >
                </v-list>
        </v-navigation-drawer>
        <video preload="auto" autoplay="false" ref="video_background" loop="loop" muted="muted" class="video-background">
            <source src="@/assets/videos/EarthMoonZoomS.mp4" type="video/mp4">
        </video>
        <div>
            <div class="container">
                <div class="row">
                    <div class="header-content">
                        <div class="header-content-inner bigtext">
                            <div :key="text.title">
                                <v-scroll-x-transition origin="left left">
                                    <h1 class="homeHeading" v-show="titleShown">{{ text.title }}</h1>
                                </v-scroll-x-transition>
                                <v-scroll-x-transition origin="right right">
                                    <h2 class="homesubHeading" v-show="subtitleShown">{{ text.subtitle }}</h2>
                                </v-scroll-x-transition>
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
      titleShown: true,
      subtitleShown: true,
      currentTextIndex: 0,
      items: [
        { title: 'Home', icon: 'mdi-home' },
        { title: 'About', icon: 'mdi-account' },
        { title: 'Services', icon: 'mdi-wrench' },
        { title: 'Contact', icon: 'mdi-phone' }
      ],
      texts: [
        {
          title: 'Programmer and IT developer',
          subtitle: 'Based in Japan'
        },

        {
          title: 'Welcome to my website',
          subtitle: 'IT developer and programmer'
        },

        {
          title: '17 years experience in web development',
          subtitle: 'PHP, JavaScript, HTML, CSS, SQL, VUE.JS, Python etc.'
        },

        {
          title: 'Currently working on a new project',
          subtitle: 'And learning Java Spring Boot'
        },

        {
          title: 'This site was made in Vue.js',
          subtitle: 'And Java Spring Boot'
        },

        {
          title: 'Contact me',
          subtitle: ''
        }
      ]
    }
  },
  methods: {
    changeText (index) {
      this.text = this.texts[index]
    }
  },
  computed: {
    text () {
      return this.texts[this.currentTextIndex]
    }
  },
  mounted () {
    window.addEventListener('scroll', function () {
      const video = this.$refs.video_background
      const scrollPosition = window.pageYOffset
      video.currentTime = scrollPosition / 100
    })

    setInterval(() => {
      this.currentTextIndex++
      if (this.currentTextIndex >= this.texts.length) {
        this.currentTextIndex = 0
      }
      this.titleShown = false
      this.subtitleShown = false
      setTimeout(() => {
        this.titleShown = true
        setTimeout(() => {
          this.subtitleShown = true
        }, 500)
      }, 500)
    }, 3000)
  }
}

</script>
<style scoped>
header{
    position: relative;
    width: 100%;
    max-height: 400px;
    min-height: 300px;
    background-size: contain;
    top: 0;
    overflow:hidden;
    margin-bottom:50px;
}
.video-background {
    position: absolute;
    top: 0;
    left: 0;
    min-width: 100%;
    min-height: 100%;
    z-index: -1;
}
.container{
    margin-top:55px;
    width:100%;
}
.titlebar, .bigtext{
    font-size: 2rem;
    font-weight: bold;
    color:#dedede;
    text-shadow: 2px 2px 2px #000000;
    font-style: italic;
}
.homeHeading{
    font-size: 3rem;
    font-weight: bold;
    color:white;
    text-shadow: 2px 2px 2px #000000;
}
.homesubHeading{
    font-size:1.5rem;
}

</style>
