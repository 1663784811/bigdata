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
        },
        {
            path: 'shoppingGType',
            component: import('@/views/shopping/ShoppingGType.vue'),
            name: 'shoppingGType'
        },
        {
            path: 'shoppingStore',
            component: import('@/views/shopping/ShoppingStore.vue'),
            name: 'shoppingStore'
        }
    ]
}
