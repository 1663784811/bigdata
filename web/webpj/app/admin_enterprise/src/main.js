import {createApp} from 'vue'
import App from './App.vue'
import ViewUIPlus from 'view-ui-plus'
import {createPinia} from 'pinia'
import piniaPluginPersist from 'pinia-plugin-persist'

import {router} from '@/router/index.js';
import 'view-ui-plus/dist/styles/viewuiplus.css'
import mitt from 'mitt'


const pinia = createPinia();
pinia.use(piniaPluginPersist);

const app = createApp(App);
app.config.globalProperties.emitter = mitt()
app.use(router);
app.use(ViewUIPlus);
app.use(pinia);
app.mount('#app');
