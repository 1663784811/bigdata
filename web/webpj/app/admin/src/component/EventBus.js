import { getCurrentInstance } from 'vue'

export default function EventBus() {
    const internalInstance = getCurrentInstance()
    const emitter = internalInstance.appContext.config.globalProperties.emitter
    console.log(emitter)
    return emitter
}
