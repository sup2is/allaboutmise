<template>
  <div>
    <nav>
      <Citys/>
    </nav>

    <article>
      <div class="container">
        <div v-for="content in contents" class="row mb-4">
          <div class="col-sm-1">{{ content.name }}</div>
          <div class="col-sm-11 pt-1">
            <b-progress :value="content.value" :variant="content.class" :key="content.value" show-value>

            </b-progress>
          </div>
        </div>
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
          { name: '종로구', value: 112, class: 'dark'},
          { name: '강남구', value: 98, class: 'danger'},
          { name: '송파구', value: 88, class: 'danger'},
          { name: '강동구', value: 72, class: 'warning'},
          { name: '중구', value: 56, class: 'warning'},
          { name: '노원구', value: 28, class: 'success'},
          { name: '강북구', value: 13, class: 'primary'}
        ],
        max: 50,
        value: 33.333333333
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

nav {
  margin-bottom: 50px;
}

</style>
