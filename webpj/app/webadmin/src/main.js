import { createApp } from 'vue';
import App from './App.vue';
import router from "./routes";
import useAppPinia from './store'


const pinia = createPinia();
const app = createApp(App);

app.use(pinia);
app.use(router);
app.mount('#app');
