<template>
  <div>
    <nav>
      <Citys/>
    </nav>
    <article>
      <div id="contents" class="container">
        <transition-group name="fade" v-on:after-enter="fadeNext">
        <div v-for="content in contents" class="row mb-4" :key="content.stationName">
          <div class="col-sm-1" :key="content.stationName">{{ content.stationName }}</div>
          <div class="col-sm-11 pt-1">
            <b-progress :value="content.value" :variant="content.class" :key="content.pm10Grade" show-value></b-progress>
          </div>
        </div>
        </transition-group>
      </div>
    </article>
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
      contentShow: true
    }
  },
  methods: {
    reload () {
      // todo 미세먼지 데이터 api 요청
      this.fadeCount = 0
    },
    fadeNext () {
      // this.contents[this.fadeCount++].show = true
      console.log(this.fadeCount)
    }
  },
  mounted () {
    this.$EventBus.$on('reload', () => {
      this.reload()
    })
    // setTimeout(this.fadeNext(),500)
    setTimeout(() =>
      this.fadeNext()
      , 100)
  },
  created () {
    this.$http.post(this.$baseUrl + '/api/realtime-mise', {
      city: '인천'
    })
      .then((result) => {
        console.log(result)
        this.content = result.data.param.miseList
        console.log(this.content)
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

nav {
  margin-bottom: 50px;
}

.fade-enter-active, .fade-leave-active {
  transition: all 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
  transform: translateY(20px);
}

</style>
