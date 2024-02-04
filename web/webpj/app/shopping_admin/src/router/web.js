export const web = {
    path: 'web',
    children: [
        {
            path: 'storeBanner',
            name: 'storeBanner',
            component: () => import('@/views/shopping/storeBanner.vue'),
        }
    ]
}
