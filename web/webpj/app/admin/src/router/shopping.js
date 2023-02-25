export const shopping = {
    path: 'shopping',
    children: [
        {
            path: 'shoppingList',
            component: import('@/views/shopping/ShoppingList.vue'),
            name: 'shopping'
        }
    ]
}
