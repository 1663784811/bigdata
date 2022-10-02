import { createPinia } from 'pinia'
import PersistPlugin from './plugin/persist'

const pinia = createPinia()
pinia.use(PersistPlugin)

function useAppPinia(app) {
  app.use(pinia)
}

export default useAppPinia
