<template>
  <b-container>
    <a @click="changeCity" href="#" class="under" v-for="city in citys" :key="city.name"><span>{{city.name}}</span></a>
  </b-container>
</template>

<script>
export default {
  name: 'Citys',
  data () {
    return {
      citys: [
        {name: '서울'},
        {name: '부산'},
        {name: '대구'},
        {name: '인천'},
        {name: '광주'},
        {name: '대전'},
        {name: '울산'},
        {name: '경기'},
        {name: '강원'},
        {name: '충북'},
        {name: '충남'},
        {name: '전북'},
        {name: '전남'},
        {name: '경북'},
        {name: '경남'},
        {name: '제주'},
        {name: '세종'}
      ]
    }
  },
  methods: {
    changeCity (e) {
      this.$setGlobalCity(e.target.innerHTML)
      this.$EventBus.$emit('reload')
      this.$EventBus.$emit('reloadTime')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

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
