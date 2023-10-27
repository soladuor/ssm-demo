package com.sola.servlet.base;

import com.sola.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        处理请求中文乱码：
        get请求：tomcat8已经处理好了
        post请求：在所有的获取请求参数之前，设置字符集 */
        request.setCharacterEncoding("utf-8");
        // response.setContentType("application/json;charset=utf-8");  // WebUtil里设置过了
        // 先获得flag的参数，进行判断
        String flag = request.getParameter("flag");
        // 通过反射获得想要调用的方法的对象
        // 其实写Class clazz = this.getClass();也行的，只是为了规范化所以得加上继承BaseServlet的类
        Class<? extends BaseServlet> clazz = this.getClass();// clazz就是继承此类的类信息（因为是当前类）
        Method method;
        try {
            method = clazz.getDeclaredMethod(flag, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true); // 暴力访问（为了访问私有的方法）
            Object result = method.invoke(this, request, response); // 调用这个函数
            WebUtil.writeJSON(response, result); // 响应JSON数据到前端
        } catch (Exception e) {
            e.printStackTrace(); // 如果删除这行则没有错误信息，不好找错误
            throw new RuntimeException(e);
        }

    }

    /**
     * 封装页面提交的分页参数及搜索条件
     */
    protected Map<String, Object> getFilters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> filters = new TreeMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] values = entry.getValue();
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    filters.put(paramName, values[0]);
                }
            }
        }
        if (!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if (!filters.containsKey("pageSize")) {
            filters.put("pageSize", 10);
        }

        return filters;
    }
}
