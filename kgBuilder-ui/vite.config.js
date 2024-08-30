import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue2'; // 使用适用于 Vue 2 的插件

export default defineConfig({
  plugins: [vue()],
  build: {
    lib: {
      entry: './src/components/index.js',
      name: 'EntityRelationGraph',
      fileName: (format) => `entity-relation-graph.${format}.js`,
    },
    rollupOptions: {
      external: ['vue'],
      output: {
        globals: {
          vue: 'Vue',
        }
      },
    },
  },
});
