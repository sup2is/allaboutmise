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
      max: 600,
      reloadTime: 0
    }
  },
  methods: {
    timer () {
      setInterval(() => {
        this.reloadTime += 1
        if (this.reloadTime > 600) {
          this.$EventBus.$emit('reload')
          this.reloadTime = 0
        }
      }, 1000)
    }
  },
  mounted () {
    this.timer()
  },
  created () {
    this.$http.get(this.$baseUrl + '/api/reloadTime')
      .then((result) => {
        this.reloadTime = result.data.param.reloadTime
      })
  }
}
</script>

<style scoped>

</style>
