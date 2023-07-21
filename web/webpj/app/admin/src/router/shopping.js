export const shopping = {
    path: 'shopping',
    children: [
        {
            path: 'shoppingList',
            component: import('@/views/shopping/ShoppingList.vue'),
            name: 'shopping'
        },
        {
            path: 'shoppingBanner',
            component: import('@/views/shopping/ShoppingBanner.vue'),
            name: 'shoppingBanner'
        }
    ]
}
