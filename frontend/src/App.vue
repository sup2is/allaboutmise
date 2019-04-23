<template>
  <div id="app">
    <header>
      <Header/>
    </header>
    <section>
      <Content/>
    </section>
  </div>
</template>

<script>

import Header from './components/Header'
import Content from './components/Content'
import Vue from 'vue'


export default {
  name: 'App',
  components: {
    Header,
    Content
  }
}

</script>

<style>
header {
  height: 150px;
}


</style>
