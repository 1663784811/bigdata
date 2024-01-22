export const pay = {
    path: 'pay',
    children: [
        {
            path: 'weixinPay',
            name: 'weixinPay',
            component: () => import('@/views/pay/WeixinPay.vue'),
            meta: {title: '微信支付设置'}
        },
        {
            path: 'aliPay',
            name: 'aliPay',
            component: () => import('@/views/pay/AliPay.vue'),
            meta: {title: '支付宝支付设置'}
        }
    ]
}
