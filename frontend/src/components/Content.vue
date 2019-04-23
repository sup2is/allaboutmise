<template>
  <div>
    <nav>
      <Citys/>
    </nav>
    <article>
      <div class="container">
        <transition-group name="fade" v-on:enter-active>
        <div v-for="content in contents" class="row mb-4" :key="content.name">
          <div class="col-sm-1" v-show="content.show" :key="content.name">{{ content.name }}</div>
          <div class="col-sm-11 pt-1">
            <b-progress v-show="content.show" :value="content.value" :variant="content.class" :key="content.value" show-value></b-progress>
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
  components : {
    Citys
  },
  data () {
    return {
        contents: [
          { name: '종로구', value: 112, class: 'dark', show: false},
          { name: '강남구', value: 98, class: 'danger', show: false},
          { name: '송파구', value: 88, class: 'danger', show: false},
          { name: '강동구', value: 72, class: 'warning', show: false},
          { name: '중구', value: 56, class: 'warning', show: false},
          { name: '노원구', value: 28, class: 'success', show: false},
          { name: '강북구', value: 13, class: 'primary', show: false}
        ],
        fadeCount: 0
    }
  },
  methods: {
    reload() {
      //todo 미세먼지 데이터 api 요청
      this.contents = [
          { name: '종로구', value: 112, class: 'dark', show: false},
          { name: '강남구', value: 98, class: 'danger', show: false}
          ] 
    },
    fadeNext() {
      this.contents[this.fadeCount++].show = true
      console.log(this.contents[this.fadeCount])
    }
  },
  mounted() {
    this.$EventBus.$on('reload', () => {
      this.reload()
    }),
    // setTimeout(this.fadeNext(),500)
    setInterval(() =>
      this.fadeNext()
      ,1000)
  },
  props : [
  ]
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

nav {
  margin-bottom: 50px;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

</style>
