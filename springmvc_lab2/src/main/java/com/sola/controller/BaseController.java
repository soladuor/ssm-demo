package com.sola.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

public abstract class BaseController {

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
