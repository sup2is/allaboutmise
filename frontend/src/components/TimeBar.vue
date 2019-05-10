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
      max: 3600,
      reloadTime: 0
    }
  },
  methods: {
    timer () {
      setInterval(() => {
        this.reloadTime += 1
        if (this.reloadTime > this.max) {
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
