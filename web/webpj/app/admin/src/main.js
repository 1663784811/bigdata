import {createApp} from 'vue'
import App from './App.vue'
import ViewUIPlus from 'view-ui-plus'
import {createPinia} from 'pinia'
import {router} from '@/router/index.js';
import 'view-ui-plus/dist/styles/viewuiplus.css'


const pinia = createPinia()
const app = createApp(App);
app.use(router);
app.use(ViewUIPlus);
app.use(pinia);
app.mount('#app');
