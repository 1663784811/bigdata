import { createApp } from 'vue';
import { createPinia } from 'pinia'
import App from './App.vue';
import router from "./routes";


import './style/common.scss';
// import './style/mixin.scss';

const pinia = createPinia();
const app = createApp(App);

app.use(pinia);
app.use(router);
app.mount('#app');
