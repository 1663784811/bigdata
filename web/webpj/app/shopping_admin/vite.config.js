import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {VantResolver} from 'unplugin-vue-components/resolvers';
import Components from 'unplugin-vue-components/vite';

import path from 'path';

const srcPath = path.resolve(__dirname, 'src');

export default defineConfig({
  envDir:"env",
  base: '/storePage',
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
  ],
  server: {
    host: "0.0.0.0"
  },
  build: {
    // 打包输出的文件夹名称
    outDir: 'storePage',
    // 静态资源文件保存的文件夹名称
    assetsDir: 'static',
    // 是否启用css代码拆分
    cssCodeSplit: true,
    // 打包构建后是否生成 source map 文件。
    sourcemap: false,
    // 打包构建时压缩混淆使用的混淆器
    minify: 'esbuild',
  }
})
