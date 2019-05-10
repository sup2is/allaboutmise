<template>
  <div>
    <nav>
      <b-container>
        <a @click="changeCity(city)" href="#" class="under" v-for="city in cities" :key="city"><span>{{city}}</span></a>
      </b-container>
    </nav>
    <article>
      <div ref="content" class="container">
        <transition-group name="fade" :key="transitionTrigger">
        <div v-for="content in contents" class="row mb-4 content" :key="content.stationName">
          <div class="col-sm-1" :key="content.stationName">{{ content.stationName }}</div>
          <div class="col-sm-11 pt-1">
            <b-progress :value="parseInt(content[mode])" :variant="content.cssClass" :key="content.stationName" show-value></b-progress>
          </div>
        </div>
        </transition-group>
      </div>
    </article>
   <!--  <div>
      <b-spinner v-if="loading" label="Loading..."></b-spinner>
    </div>
    <button @click="!loading"></button> -->
  </div>
</template>

<script>

export default {
  name: 'Content',
  data () {
    return {
      contents: [],
      cities: [],
      fadeCount: 0,
      contentShow: true,
      transitionTrigger: false,
      loading: false,
      mode: 'pm10Value',
      sortMode: 'asc'
    }
  },
  methods: {
    reload () {
      this.getRealtimeMiseDatas()
    },
    getRealtimeMiseDatas () {
      this.$http.post(this.$baseUrl + '/api/realtime-mise', {
        city: this.$globalCity
      })
        .then((result) => {
          this.setContents(result.data.param.miseList)
          this.$EventBus.$emit('reloadTime', result.data.param.reloadTime)
        })
    },
    getCities () {
      this.$http.get(this.$baseUrl + '/api/cities')
        .then((result) => {
          this.cities = result.data.param.cities
        })
    },
    sort () {
      var mode = this.mode
      console.log(this.sortMode === 'asc')
      if (this.sortMode === 'asc') {
        this.contents.sort(function (a, b) {
          if (parseInt(a[mode]) > parseInt(b[mode])) return -1
          if (parseInt(a[mode]) < parseInt(b[mode])) return 1
          return 0
        })
      } else {
        this.contents.sort(function (a, b) {
          if (parseInt(a[mode]) > parseInt(b[mode])) return 1
          if (parseInt(a[mode]) < parseInt(b[mode])) return -1
          return 0
        })
      }
    },
    setContents (contents) {
      this.contents = contents
      this.sort()
    },
    changeCity (city) {
      this.$setGlobalCity(city)
      this.reload()
    }
  },
  mounted () {
    this.$EventBus.$on('reload', () => {
      console.log('on load call')
      this.reload()
    })
    this.$EventBus.$on('mode', (mode) => {
      this.mode = mode
      this.sort()
    })
    this.$EventBus.$on('sort', (sortMode) => {
      this.sortMode = sortMode
      this.sort()
    })
  },
  created () {
    this.getCities()
    this.getRealtimeMiseDatas()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

nav {
  margin-bottom: 50px;
}

.fade-enter-active, .fade-leave-active {
  transition: all 0.7s;
}

.fade-enter-active {
  transition-delay: 0.7s
}

.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
  transform: translateY(20px);
}

a > span{ color: #aaa; }
a:link { color: #fff; text-decoration: none;}
a:visited { color: black; text-decoration: none;}
a:hover { color: black; text-decoration: none;}

.under {
  font-size: 20px;
  font-weight: 500;
  margin: 5px;
  border-bottom: 2px solid #eee;
  display: inline-block;
  padding-bottom: 3px;
  font-family: sans-serif;
}

</style>
