import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080/springmvc_lab3_war_exploded",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""), // 不可以省略rewrite
      },
    },
  },
});
