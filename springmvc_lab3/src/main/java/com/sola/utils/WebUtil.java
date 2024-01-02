package com.sola.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * Web 操作工具类
 */
public class WebUtil {

    private static final Gson gson = new Gson();

    /**
     * 将数据以 JSON 格式写入响应中
     */
    public static void writeJSON(HttpServletResponse response, Object data) {
        try {
            // 设置响应头
            response.setContentType("application/json"); // 指定内容类型为 JSON 格式
            response.setCharacterEncoding("utf-8"); // 防止中文乱码
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            String json = gson.toJson(data); // 转为 JSON 字符串
            System.out.println("json = " + json);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("在响应中写数据出错！" + e);
            throw new RuntimeException(e);
        }
    }

}
