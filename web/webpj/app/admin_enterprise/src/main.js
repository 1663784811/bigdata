import {createApp} from 'vue'
import App from './App.vue'
import ViewUIPlus from 'view-ui-plus'
import {createPinia} from 'pinia'
import piniaPluginPersist from 'pinia-plugin-persist'

import router from '@/router/index.js';
import 'view-ui-plus/dist/styles/viewuiplus.css'

import {use} from 'echarts/core';
import {CanvasRenderer} from 'echarts/renderers';
import {PieChart} from 'echarts/charts';
import {TitleComponent, TooltipComponent, LegendComponent,} from 'echarts/components';
use([CanvasRenderer, PieChart, TitleComponent, TooltipComponent, LegendComponent,]);



const pinia = createPinia();
pinia.use(piniaPluginPersist);

const app = createApp(App);
app.use(router);
app.use(ViewUIPlus);
app.use(pinia);
app.mount('#app');




