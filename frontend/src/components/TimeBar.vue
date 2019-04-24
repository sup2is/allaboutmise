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
      value: 0
    }
  },
  methods: {
    timer() {
      setInterval(()=> {
        this.value += 100
        if(this.value > 600) {
          this.$EventBus.$emit('reload')
          this.value = 0
        }
      },1000)
    }
  },
  mounted() {
    this.timer()
  }
}
</script>

<style scoped>

</style>
