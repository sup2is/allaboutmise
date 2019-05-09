<template>
  <div>
    <TimeBar/>
    <b-container fluid>
      <h1>{{msg}}</h1>
      <b-nav>
        <b-nav-item v-for="mode in modes" :key="mode.value" @click="changeMode(mode.value)" >{{mode.text}}</b-nav-item>
        <b-nav-item-dropdown :text="selected">
          <b-dropdown-item v-for="option in sortOptions" :key="option.value" :value="option.value" @click="sort(option)">{{option.name}}</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-nav>
    </b-container>
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
      sortOptions: [
        {'name': '오름차순', 'value': 'asc'},
        {'name': '내림차순', 'value': 'desc'}
        ],
      selected: '오름차순',
      modes: [
        {'text': '실시간', 'value': 'pm10Value'},
        {'text': '평균', 'value': 'pm10Value24'}
      ]
    }
  },
  methods: {
    sort (option) {
      this.selected = option.name
      this.$EventBus.$emit('sort', option.value)
    },
    changeMode (mode) {
      this.$EventBus.$emit('mode', mode)
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
