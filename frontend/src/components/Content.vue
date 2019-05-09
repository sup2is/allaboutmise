<template>
  <div>
    <nav>
      <Citys/>
    </nav>
    <article>
      <div ref="content" class="container">
        <transition-group name="fade" :key="transitionTrigger">
        <div v-for="content in contents" class="row mb-4 content" :key="content.stationName">
          <div class="col-sm-1" :key="content.stationName">{{ content.stationName }}</div>
          <div class="col-sm-11 pt-1">
            <b-progress :value="parseInt(content.pm10Value)" :variant="content.cssClass" :key="content.stationName" show-value></b-progress>
          </div>
        </div>
        </transition-group>
      </div>
    </article>
    <div>
      <b-spinner v-if="loading" label="Loading..."></b-spinner>
    </div>
    <button @click="!loading"></button>
  </div>
</template>

<script>

import Citys from './Citys'

export default {
  name: 'Content',
  components: {
    Citys
  },
  data () {
    return {
      contents: [],
      fadeCount: 0,
      contentShow: true,
      transitionTrigger: false,
      loading: false,
      mode: ''
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
        })
    },
    sortByPm10value () {
      this.contents.sort(function (a, b) {
        if (parseInt(a.pm10Value) > parseInt(b.pm10Value)) return -1
        if (parseInt(a.pm10Value) < parseInt(b.pm10Value)) return 1
        return 0
      })
    },
    sortByPm10value24 () {
      this.contents.sort(function (a, b) {
        if (parseInt(a.pm10Value24) > parseInt(b.pm10Value24)) return -1
        if (parseInt(a.pm10Value24) < parseInt(b.pm10Value24)) return 1
        return 0
      })
    },
    setContents (contents) {
      this.contents = contents
      this.sortByPm10value()
    }
  },
  mounted () {
    this.$EventBus.$on('reload', () => {
      this.reload()
    })
    this.$EventBus.$on('sort', () => {
    })
    this.$EventBus.$on('mode', (mode) => {
      this.mode = mode
    })
  },
  created () {
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

</style>
