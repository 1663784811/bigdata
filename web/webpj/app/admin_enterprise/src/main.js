import {createApp} from 'vue'
import App from './App.vue'
import ViewUIPlus from 'view-ui-plus'
import {createPinia} from 'pinia'
import piniaPluginPersist from 'pinia-plugin-persist'

import router from '@/router/index.js';
import 'view-ui-plus/dist/styles/viewuiplus.css'

import CommonTable from '@/component/CommonTable.vue'
import SelectDataDrawer from '@/component/modal/SelectDataDrawer.vue'
import DataTable from '@/component/modal/DataTable.vue'
import ModalDataList from '@/component/modal/ModalDataList.vue'
import ModalTable from '@/component/modal/ModalTable.vue'
import ModalMqttMsg from '@/component/modal/ModalMqttMsg.vue'

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


// ================== 被组件调用的组件
app.component('modal-data-list', ModalDataList)
app.component('modal-table', ModalTable)
app.component('modal-mqtt-msg', ModalMqttMsg)


// =================== 组件
app.component('common-table', CommonTable);
app.component('data-table', DataTable);
app.component('select-dataDrawer', SelectDataDrawer);



app.mount('#app');




