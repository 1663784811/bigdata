import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useCartStore = defineStore('cart', () => {
    const count = ref(0)
    return {count, updateCart: 0}
})
