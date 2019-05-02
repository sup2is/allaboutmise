<template>
  <b-container>
    <a href="#" class="under"><span>서울</span></a>
    <a href="#" class="under"><span>대전</span></a>
    <a href="#" class="under"><span>대구</span></a>
    <a href="#" class="under"><span>부산</span></a>
    <a href="#" class="under"><span>울산</span></a>
    <a href="#" class="under"><span>제주</span></a>
  </b-container>
</template>

<script>
export default {
  name: 'Citys',
  data () {
    return {

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
