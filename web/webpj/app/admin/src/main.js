import { createApp } from 'vue'
import App from './App.vue'
import naive from 'naive-ui'
import { createPinia } from 'pinia'
import {router} from "@/router/index";


const pinia = createPinia()
const app = createApp(App);
app.use(router);
app.use(pinia);
app.use(naive);
app.mount('#app');
