<template>
  <div>
    <TimeBar/>
    <b-container fluid>
      <h1><a target="_blank" href="https://github.com/sup2is/allaboutmise">{{msg}}</a></h1>
      <b-nav>
        <b-nav-item-dropdown :text="modeSelected">
          <b-dropdown-item v-for="mode in modes" :key="mode.name" :value="mode.value" @click="changeMode(mode)">{{mode.name}}</b-dropdown-item>
        </b-nav-item-dropdown>
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
      modes: [],
      modeSelected: '미세먼지(pm2.5)'
    }
  },
  methods: {
    sort (option) {
      this.selected = option.name
      this.$EventBus.$emit('sort', option.value)
    },
    changeMode (mode) {
      this.modeSelected = mode.name
      this.$EventBus.$emit('mode', mode)
    },
    getModes () {
      this.$http.get(this.$baseUrl + '/api/modes')
        .then((result) => {
          this.modes = result.data.param.modes
        })
    }
  },
  created () {
    this.getModes()
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

a {
  color: black;
}

</style>
