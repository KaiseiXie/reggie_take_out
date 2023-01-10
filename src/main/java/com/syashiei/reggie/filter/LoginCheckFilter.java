package com.syashiei.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.syashiei.reggie.common.BaseContext;
import com.syashiei.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经登录
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //类型转换
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        //1.获得本次请求的URI
        String requestURI = httpServletRequest.getRequestURI();// /backend/index.html
        log.info("拦截到请求，{}",httpServletRequest.getRequestURI());

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls,requestURI);

        //3.如果不需要处理，则直接放放行
        if (check){
            log.info("接收到不需要处理的请求{}",requestURI);
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //4.判断登录状态，如果已经登录则放行
        if (httpServletRequest.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id为：{}",httpServletRequest.getSession().getAttribute("employee"));

            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //5.如果未登录则返回未登录结果，通过输出流的方式往回写数据
        log.info("接收到未带有登录信息的请求{}",requestURI);
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 匹配路径，检查是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
