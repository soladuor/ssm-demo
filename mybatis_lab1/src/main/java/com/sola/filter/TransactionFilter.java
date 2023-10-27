package com.sola.filter;


import com.google.gson.Gson;
import com.sola.utils.MapperTools;
import com.sola.utils.WebUtil;
import com.sola.utils.result.JSONResult;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 事务处理拦截器
 *
 * @version 1.0
 */
@WebFilter(filterName = "TransactionFilter", urlPatterns = "/*")
public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        SqlSession sqlSession = MapperTools.getSqlSession(); // 获得会话
        try {
            chain.doFilter(request, response); // 放行
            sqlSession.commit(); // 正常运行到此处没有异常产生，则提交
        } catch (Exception e) { // 注：捕获类型一定要设置为最大的(什么异常都可以捕获)
            sqlSession.rollback(); // 走到这里表示有异常产生，则回滚
            Throwable cause = e.getCause();
            String message = cause.getMessage();
            while ((cause = cause.getCause()) != null) {
                message = cause.getMessage();
            }
            String errorJson = new Gson().toJson(e);
            System.out.println("errorJson = " + errorJson);
            WebUtil.writeJSON((HttpServletResponse) response, JSONResult.fail(message));
        } finally {
            MapperTools.closeSqlSession(); // 无论成功与否，关闭会话
        }
    }
}
