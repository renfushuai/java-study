package com.rfs.javastudy.utils;

import cn.hutool.core.util.StrUtil;
import com.rfs.javastudy.constant.LogAttr;
import com.rfs.javastudy.constant.ResponseCodeConst;
import com.rfs.javastudy.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
* @Description:
* @Author: rfs
*/
@Slf4j
public class XLoggerUtil {
    public static void info(String tag, String msg, Object... args) {
        try {
            MDC.put("tag", tag);
            MDC.put("code",  String.valueOf(ResponseCodeConst.SUCCESS.getCode()));
            MDC.put("status","1");
            MDC.put("msg", msg);
            log.info(msg, args);
            MDC.remove("tag");
            MDC.remove("code");
            MDC.remove("status");
            MDC.remove("msg");
        } catch (Exception ee) {
            log.error("XLoggerUtil.errorLog do record error", ee);
        }
    }
    public static void error(String tag, Integer errorCode, Exception e) {
        try {
            MDC.put("tag", tag);
            MDC.put("code", errorCode.toString());
            MDC.put("status","0");
            MDC.put("msg", ExcpUtil.buildErrorMessage(e));
            log.error(" ex = {}", e == null ? null : e.getMessage(), e);
            MDC.remove("tag");
            MDC.remove("code");
            MDC.remove("status");
            MDC.remove("msg");
        } catch (Exception ee) {
            log.error("XLoggerUtil.errorLog do record error", ee);
        }
    }
    public static void error(ResponseCodeConst responseCodeConst, Exception e) {
        try {
            MDC.put("tag", responseCodeConst.getMsg());
            MDC.put("code",String.valueOf(responseCodeConst.getCode()));
            MDC.put("status","0");
            MDC.put("msg", ExcpUtil.buildErrorMessage(e));
            log.error(" ex = {}", e == null ? null : e.getMessage(), e);
            MDC.remove("tag");
            MDC.remove("code");
            MDC.remove("status");
            MDC.remove("msg");
        } catch (Exception ee) {
            log.error("XLoggerUtil.errorLog do record error", ee);
        }
    }

    public static void error(String tag, Integer errorCode, String msg) {
        try {
            MDC.put("tag", tag);
            MDC.put("code", errorCode.toString());
            MDC.put("status","0");
            MDC.put("msg", msg);
            log.error(msg);
            MDC.remove("tag");
            MDC.remove("code");
            MDC.remove("status");
            MDC.remove("msg");
        } catch (Exception ee) {
            log.error("XLoggerUtil.errorLog do record error", ee);
        }
    }
    public static void info(String msg) {
        try {
            MDC.put("code", String.valueOf(ResponseCodeConst.SUCCESS.getCode()));
            MDC.put("status","1");
            MDC.put("msg", msg);
            log.info(msg);
            MDC.remove("code");
            MDC.remove("status");
            MDC.remove("msg");
        } catch (Exception ee) {
            log.error("XLoggerUtil.errorLog do record error", ee);
        }
    }
    /**
     * HTTP请求日志打印
     *  @param methodType
     * @param url
     * @param params
     * @param res
     * @param time
     * @param statusCode
     * @param errorCode
     * @param errorMsg
     */
    private static void httpLog(String methodType, String url, String tag, String params, String heads, String res, Long time, Integer statusCode, Integer errorCode, String errorMsg) {
        try {
            MDC.put("thirdApi.url", url);
            MDC.put("thirdApi.methodType", methodType);
            MDC.put("thirdApi.param", params);
            MDC.put("thirdApi.head", heads);
            String statCode = statusCode == null ? null : statusCode.toString();
            String errCode = errorCode == null ? "" : errorCode.toString();
            MDC.put("thirdApi.errorCode", errCode);
            MDC.put("thirdApi.errorMsg", errorMsg);

            MDC.put("uriPath", url);
            MDC.put("tag", tag);
            MDC.put("responseBody", res);
            String timeVal = time != null ? String.valueOf(time) : null;
            MDC.put("costMillis", timeVal);
            MDC.put("thirdApi.costTime", timeVal);
            if (errorCode == null) {
                MDC.put("logAttr", LogAttr.TEXT.getCode().toString());
                MDC.put("status", "1");
                MDC.put("code", statCode == null ? errCode : statCode);
                MDC.put("msg", "");
                log.info("HTTP cost {} ms, url = {}", time, url);
            } else {
                MDC.put("logAttr", LogAttr.THIRD_ERR.getCode().toString());
                MDC.put("status", "0");
                MDC.put("code", errCode);
                MDC.put("msg", errorMsg);
                log.error("HTTP ERROR msg = {}", errorMsg);
            }
            MDC.remove("thirdApi.url");
            MDC.remove("thirdApi.methodType");
            MDC.remove("thirdApi.param");
            MDC.remove("thirdApi.head");
            MDC.remove("thirdApi.errorCode");
            MDC.remove("thirdApi.errorMsg");
            MDC.remove("uriPath");
            MDC.remove("tag");
            MDC.remove("responseBody");
            MDC.remove("costMillis");
            MDC.remove("thirdApi.costTime");
            MDC.remove("status");
            MDC.remove("code");
            MDC.remove("logAttr");
            MDC.remove("msg");
        } catch (Exception t) {
            log.error("XLoggerUtil.httpLog do record error", t);
        }
    }

    /**
     * Get请求日志记录
     *  @param url
     * @param params
     * @param res
     * @param time
     * @param statusCode
     * @param errorCode
     * @param errorMsg
     */
    public static void httpGetLog(String url, String tag, String params, String heads,String res, Long time, Integer statusCode, Integer errorCode, String errorMsg) {
        httpLog("GET", url, tag, params,heads, res, time, statusCode, errorCode, errorMsg);
    }

    /**
     * Post请求日志记录
     *  @param url
     * @param params
     * @param res
     * @param time
     * @param statusCode
     * @param errorCode
     * @param errorMsg
     */
    public static void httpPostLog(String url, String tag, String params,  String heads,String res, Long time, Integer statusCode, Integer errorCode, String errorMsg) {
        httpLog("POST", url, tag, params,heads, res, time, statusCode, errorCode, errorMsg);
    }
    /**
     * 本地接口输入日志
     * @param url
     * @param tag
     * @param input
     * @param msgTemplate
     * @param args
     */
    public static void input(String url, String tag, Object input, String msgTemplate, Object... args) {
        MDC.put("logAttr", LogAttr.INPUT.getCode().toString());
        MDC.put("uriPath", url);
        MDC.put("tag", tag);
        MDC.put("requestParam", JacksonUtil.toJsonString(input));
        if (msgTemplate!=null){
            log.info(msgTemplate, args);
        }
        MDC.remove("logAttr");
        MDC.remove("uriPath");
        MDC.remove("tag");
        MDC.remove("requestParam");
    }

    /**
     * 本地接口输出日志
     * @param url
     * @param tag
     * @param output
     * @param msgTemplate
     * @param args
     */
    public static void output(String url, String tag, long cost, Object output, String msgTemplate, Object... args) {
        MDC.put("logAttr", LogAttr.OUTPUT.getCode().toString());
        MDC.put("uriPath", url);
        MDC.put("tag", tag);
        MDC.put("responseData", JacksonUtil.toJsonString(output));
        MDC.put("costMillis", String.valueOf(cost));
        if (output instanceof ResponseDTO) {
            Integer status = ((ResponseDTO) output).getStatus();
            if (status != null) {
                MDC.put("status", status.toString());
            }
            Integer code = ((ResponseDTO) output).getCode();
            if (code != null) {
                MDC.put("code", code.toString());
            }
            String msg = ((ResponseDTO) output).getMsg();
            if (msg != null) {
                MDC.put("msg",msg);
            }
        }
        if (msgTemplate!=null){
            log.info(msgTemplate, args);
        }
        MDC.remove("logAttr");
        MDC.remove("uriPath");
        MDC.remove("tag");
        MDC.remove("responseData");
        MDC.remove("costMillis");
        MDC.remove("code");
        MDC.remove("msg");
        MDC.remove("status");
    }

    /**
     * db输出日志
     */
    public static void dbOutput(String sql,String sqlParam, String sqlCommandType,  Object value,long cost, String errMsg) {
        MDC.put("logAttr", LogAttr.SQL.getCode().toString());
        MDC.put("uriPath", sql);
        MDC.put("tag","mysql-"+sqlCommandType);
        MDC.put("requestParam", sqlParam);
        MDC.put("responseData", value == null ? null : JacksonUtil.toJsonString(value));
        MDC.put("costMillis", String.valueOf(cost));
        if (StrUtil.isNotBlank(errMsg)) {
            MDC.put("msg",errMsg);
            MDC.put("status", String.valueOf(ResponseCodeConst.SQL_ERROR.getCode()));
            log.error("sql execute {} param {} ERROR, err = {}", sql,sqlParam, errMsg);
        }else {
            MDC.put("status", "1");
            log.info("sql execute {} param {} cost {} ms", sql,sqlParam, cost);
        }
        MDC.remove("logAttr");
        MDC.remove("uriPath");
        MDC.remove("tag");
        MDC.remove("requestParam");
        MDC.remove("responseData");
        MDC.remove("costMillis");
        MDC.remove("code");
        MDC.remove("msg");
        MDC.remove("status");
    }

    /**
     * redis输出日志
     */
    public static void redisOutput(String key,String methodName,  Object value,long cost, String errMsg) {
        MDC.put("logAttr", LogAttr.REDIS.getCode().toString());
        MDC.put("uriPath", methodName);
        MDC.put("tag", "redis-"+methodName);
        MDC.put("requestParam", key);
        MDC.put("responseData", value == null ? null : JacksonUtil.toJsonString(value));
        MDC.put("costMillis", String.valueOf(cost));
        if (StrUtil.isNotBlank(errMsg)) {
            MDC.put("msg",errMsg);
            MDC.put("status", String.valueOf(ResponseCodeConst.SQL_ERROR.getCode()));
            log.error("redis execute {} ERROR, err = {}", methodName, errMsg);
        }else {
            MDC.put("status", "1");
            log.info("redis execute {} cost {} ms", methodName, cost);
        }
        MDC.remove("logAttr");
        MDC.remove("uriPath");
        MDC.remove("tag");
        MDC.remove("requestParam");
        MDC.remove("responseData");
        MDC.remove("costMillis");
        MDC.remove("code");
        MDC.remove("msg");
        MDC.remove("status");
    }
}
