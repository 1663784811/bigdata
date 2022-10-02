import { createApp } from 'vue';
import App from './App.vue';
import router from "./routes";
import useAppPinia from './store'

const app = createApp(App);

app.use(useAppPinia);
app.use(router);
app.mount('#app');
