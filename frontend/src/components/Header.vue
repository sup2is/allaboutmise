<template>
  <div>
    <TimeBar/>
    <b-container fluid>
      <h1>{{msg}}</h1>
      <b-nav>
        <b-nav-item>실시간</b-nav-item>
        <b-nav-item>평균</b-nav-item>
        <b-nav-item-dropdown text="오름차순">
          <b-dropdown-item>오름차순</b-dropdown-item>
          <b-dropdown-item>내림차순</b-dropdown-item>
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
      msg: 'allaboutmise.'
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
