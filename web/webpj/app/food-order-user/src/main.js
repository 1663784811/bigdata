import {createApp} from 'vue'
import {createPinia} from 'pinia'
import piniaPluginPersist from 'pinia-plugin-persist'


import {
    ActionBar, ActionBarIcon, ActionBarButton, Divider, Popup,
    Overlay, Loading, Dialog, ContactCard, Form, AddressEdit, AddressList,
    Field, CellGroup, Cell, SwipeCell, Icon, Stepper, Card, Checkbox, CheckboxGroup,
    Button, Swipe, SwipeItem, PullRefresh, List, Tab, Tabs, SubmitBar, Toast, Skeleton

} from 'vant'

import App from './App.vue'
import router from './router'
import 'lib-flexible/flexible'

import './assets/main.css'
import './common/style/theme.css'
import 'vant/es/toast/style'





const app = createApp(App)

const pinia = createPinia();
pinia.use(piniaPluginPersist);

app.use(pinia)
app.use(router)
app.use(ActionBarButton)
app.use(ActionBarIcon)
app.use(ActionBar)
app.use(Divider)
app.use(Popup)
app.use(Overlay)
app.use(Loading)
app.use(Dialog)
app.use(Toast)
app.use(ContactCard)
app.use(Form)
app.use(AddressEdit)
app.use(AddressList)
app.use(Field)
app.use(CellGroup)
app.use(Cell)
app.use(SwipeCell)
app.use(Icon)
app.use(Stepper)
app.use(Card)
app.use(Button)
app.use(Swipe)
app.use(SwipeItem)
app.use(PullRefresh)
app.use(List)
app.use(Tab)
app.use(Tabs)
app.use(SubmitBar)
app.use(Checkbox)
app.use(CheckboxGroup)
app.use(Skeleton)


// 全局过滤器
app.config.globalProperties.$filters = {
    prefix(url) {
        if (url && url.startsWith('http')) {
            return url
        } else {
            url = `http://sssss${url}`
            return url
        }
    }
}

app.mount('#app')
