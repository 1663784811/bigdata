import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {VantResolver} from 'unplugin-vue-components/resolvers';
import Components from 'unplugin-vue-components/vite';

import path from 'path';

const srcPath = path.resolve(__dirname, 'src');

// https://vitejs.dev/config/
export default defineConfig({
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
})
