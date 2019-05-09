<template>
  <div>
    <b-progress height="2px" :value="reloadTime" :max="max" class="mb-3"></b-progress>
  </div>
</template>

<script>
export default {
  name: 'TimeBar',
  data () {
    return {
      max: 305,
      reloadTime: 0
    }
  },
  methods: {
    timer () {
      var times = setInterval(() => {
        this.reloadTime += 1
        console.log(this.reloadTime)
        if (this.reloadTime > this.max) {
          console.log('over')
          this.$EventBus.$emit('reload')
        }
      }, 1000)
    }
  },
  mounted () {
    this.timer()
  },
  created () {
    this.$EventBus.$on('reloadTime', (reloadTime) => {
      this.reloadTime = reloadTime
    })
  }
}
</script>

<style scoped>

</style>
