package com.rfs.javastudy.interceptor;

import com.rfs.javastudy.constant.ResponseCodeConst;
import com.rfs.javastudy.dto.ResponseDTO;
import com.rfs.javastudy.utils.JacksonUtil;
import com.rfs.javastudy.utils.LogIdUtil;
import com.rfs.javastudy.utils.XLoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;


@Configuration
public class ContextFilter implements Filter {

    public static final String OPTIONS = HttpMethod.OPTIONS.name();

    @Bean
    public FilterRegistrationBean<Filter> logIdFilterRegistration() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        ContextFilter filter = new ContextFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
        ReReadableHttpRequestWrapper requestWrapper = new ReReadableHttpRequestWrapper((HttpServletRequest) servletRequest);
        HttpServletResponse responseWrapper = (HttpServletResponse) servletResponse;
        try {
            String logId = getParamOrHeader(requestWrapper, LogIdUtil.LOG_ID);
            if (StringUtils.isBlank(logId)) {
                logId = LogIdUtil.generate();
            }
            LogIdUtil.setCurrentLogId(logId);
            responseWrapper.addHeader("logId", logId);
            filterChain.doFilter(requestWrapper, responseWrapper);
        } catch(Throwable e){
            handleException(e, responseWrapper);
        } finally {
            MDC.clear();
        }
    }
    private void handleException(Throwable t, HttpServletResponse responseWrapper) {
        PrintWriter writer = null;
        try {
            XLoggerUtil.error("装载请求信息失败", ResponseCodeConst.SYSTEM_ERROR.getCode(),t.getMessage());
            ResponseDTO error = ResponseDTO.error(ResponseCodeConst.SYSTEM_ERROR);
            responseWrapper.addHeader("Content-Type", "application/json;charset=utf-8");
            writer = responseWrapper.getWriter();
            writer.write(JacksonUtil.toJsonString(error));
            writer.flush();

        } catch (IOException e) {
            XLoggerUtil.error(ResponseCodeConst.SYSTEM_ERROR.getMsg(),ResponseCodeConst.SYSTEM_ERROR.getCode(),e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    private String getRequestBody(ReReadableHttpRequestWrapper requestWrapper) {
        BufferedReader br = null;
        StringBuilder bodyStr = new StringBuilder();
        try {
            br = requestWrapper.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                bodyStr.append(str);
            }

        } catch (IOException e) {
            XLoggerUtil.error("ContextFilter异常，获取RequestBody信息异常",500,e);
        }
        return bodyStr.toString();
    }

    @Override
    public void destroy() {
    }

    private String getParamOrHeader(ReReadableHttpRequestWrapper request, String key) {
        String re = request.getHeader(key);
        if (re != null && !"".equals(re)) {
            return re;
        }
        re = request.getParameter(key);
        return re;
    }
}