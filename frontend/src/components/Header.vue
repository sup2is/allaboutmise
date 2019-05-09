<template>
  <div>
    <TimeBar/>
    <b-container fluid>
      <h1>{{msg}}</h1>
      <b-nav>
        <b-nav-item v-for="mode in modes" :key="mode.value" @click="changeMode">{{mode.text}}</b-nav-item>
        <b-nav-item-dropdown :text="selected">
          <b-dropdown-item v-for="option in sortOptions" :key="option" :value="option" @click="sort">{{option}}</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-nav>
    </b-container>
    {{selected}}
  </div>
</template>

<script>

import TimeBar from './TimeBar'

export default {
  name: 'Header',
  components: {
    TimeBar
  },
  data () {
    return {
      msg: 'allaboutmise.',
      sortOptions: ['오름차순', '내림차순'],
      selected: '오름차순',
      modes: [
        {'text': '실시간', 'value': 'pm10Value'},
        {'text': '평균', 'value': 'pm10Value24'}
      ],
      mode: ''
    }
  },
  methods: {
    sort (e) {
      this.selected = e.target.innerText
      this.$EventBus.$emit('sort')
    },
    changeMode (e) {
      this.mode = e.target.innerText
      this.$EventBus.$emit('mode', this.mode)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  text-align: center;
}

ul {
  float: right;
}

</style>
