import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {VantResolver} from 'unplugin-vue-components/resolvers';
import Components from 'unplugin-vue-components/vite';
// 代码块分析工具
import {visualizer} from 'rollup-plugin-visualizer'
// cdn 引入
import {autoComplete, Plugin as importToCDN} from "vite-plugin-cdn-import"
// gzip 压缩
import viteCompression from 'vite-plugin-compression'

import path from 'path';

const srcPath = path.resolve(__dirname, 'src');

// https://vitejs.dev/config/
export default defineConfig({
    envDir: "env",
    base: '/enterprise',
    resolve: {
        // 配置路径别名
        alias: [
            {find: '@', replacement: srcPath},
        ],
    },
    plugins: [
        vue(),
        Components({
            resolvers: [VantResolver()],
        }),
        visualizer({
            emitFile: false,
            filename: 'enterprise/stats.html',
            open: true,
            sourcemap: true
        }),
        viteCompression({
            verbose: true,
            disable: false,
            threshold: 1024,
            algorithm: 'gzip',
            deleteOriginFile: false
        })
    ],
    server: {
        host: "0.0.0.0"
    },
    build: {
        // 打包输出的文件夹名称
        outDir: 'enterprise',
        // 静态资源文件保存的文件夹名称
        assetsDir: 'static',
        // 是否启用css代码拆分
        cssCodeSplit: true,
        // 打包构建后是否生成 source map 文件。
        sourcemap: true,
        // 打包构建时压缩混淆使用的混淆器
        minify: 'esbuild',
    }
})
