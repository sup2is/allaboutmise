// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from 'axios'

import BootstrapVue from 'bootstrap-vue'

import './assets/normalize.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.config.productionTip = false

Vue.prototype.$EventBus = new Vue()
Vue.prototype.$http = axios
Vue.prototype.$globalCity = '서울'
Vue.prototype.$setGlobalCity = function (city) {
  Vue.prototype.$globalCity = city
}

Vue.prototype.$baseUrl = 'http://15.164.48.89:8081/'
// Vue.prototype.$baseUrl = 'http://127.0.0.1:8081/'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>'
})
