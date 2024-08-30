import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import axios from "axios";
import components from './components/index'
import 'echarts';
import ECharts from 'vue-echarts'
// import './assets/main.css'
// import './views/kgbuilder/entity-relation-graph/style.css';

// import echarts from "./utils/echarts";
// import * as echarts from 'echarts';
// Vue.prototype.$echarts = echarts;
Vue.component('ECharts', ECharts)

Vue.prototype.$http = axios; //正确的使用
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(components)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
