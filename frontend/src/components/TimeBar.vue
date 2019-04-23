<template>
  <div>
    <b-progress height="2px" :value="value" :max="max" class="mb-3"></b-progress>
  </div>
</template>

<script>
export default {
  name: 'TimeBar',
  data () {
    return {
      max: 600,
      value: 1
    }
  },
  methods: {
    timer() {
      setInterval(()=> {
        this.value += 5
      },1000)
    }
  },
  mounted: {
    this.timer()
  }
}
</script>

<style scoped>

</style>
