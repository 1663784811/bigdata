import {defineConfig} from 'vite'
import {resolve} from 'path';
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite';
import {VantResolver} from 'unplugin-vue-components/resolvers';


export default defineConfig({
    plugins: [
        vue(),
        Components({
            resolvers: [VantResolver()],
        }),
    ],
    server: {
        port: 8000,
        hmr: true
    },
    resolve: {
        alias: {
            "@": resolve(__dirname, "src")
        }
    }
})
