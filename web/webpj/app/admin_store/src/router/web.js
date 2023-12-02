export const web = {
    path: 'web',
    children: [
        {
            path: 'banner',
            name: 'banner',
            component: () => import('@/views/shopping/ShoppingBanner.vue'),
        }
    ]
}
