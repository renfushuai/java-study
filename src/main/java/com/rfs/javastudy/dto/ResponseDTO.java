package com.rfs.javastudy.dto;

import com.rfs.javastudy.constant.ResponseCodeConst;

/**
 * 返回类
 *
 * @param <T>
 */
public class ResponseDTO<T> {

    public static final ResponseDTO<Boolean> DEFAULT_SUCCEED = succData(Boolean.TRUE);

    protected Integer code;

    protected String msg;
    protected Boolean success;
    protected Integer status;
    protected T data;

    public ResponseDTO() {
    }

    /**
     * @Description: 自定义提示
     * @Author: rfs
     */
    public ResponseDTO(ResponseCodeConst responseCodeConst, String msg) {
        this.code = responseCodeConst.getCode();
        this.msg = msg;
        this.success = responseCodeConst.isSuccess();
    }
    public static <T> ResponseDTO<T> succ() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseCodeConst.SUCCESS.getCode());
        responseDTO.setMsg(ResponseCodeConst.SUCCESS.getMsg());
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> succData(T data, String msg) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseCodeConst.SUCCESS.getCode());
        responseDTO.setMsg(msg);
        responseDTO.setData(data);
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> succData(T data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseCodeConst.SUCCESS.getCode());
        responseDTO.setMsg(ResponseCodeConst.SUCCESS.getMsg());
        responseDTO.setData(data);
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> succMsg(String msg) {
        return new ResponseDTO<T>(ResponseCodeConst.SUCCESS, msg);
    }


    public static <T> ResponseDTO<T> error(ResponseCodeConst codeConst) {
        ResponseDTO<T> responseDTO = new ResponseDTO<T>();
        responseDTO.setCode(codeConst.getCode());
        responseDTO.setMsg(codeConst.getMsg());
        responseDTO.setSuccess(false);
        responseDTO.setStatus(0);
        return responseDTO;
    }


    public static <T> ResponseDTO<T> error(ResponseCodeConst codeConst, String msg) {
        ResponseDTO<T> responseDTO = new ResponseDTO<T>();
        responseDTO.setCode(codeConst.getCode());
        responseDTO.setMsg(msg);
        responseDTO.setSuccess(false);
        responseDTO.setStatus(0);
        return responseDTO;
    }
    public static <T> ResponseDTO<T> error(Integer code, String msg) {
        ResponseDTO<T> responseDTO = new ResponseDTO<T>();
        responseDTO.setCode(code);
        responseDTO.setMsg(msg);
        responseDTO.setSuccess(false);
        responseDTO.setStatus(0);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> error(Integer code, String msg, T t) {
        ResponseDTO<T> responseDTO = new ResponseDTO<T>();
        responseDTO.setCode(code);
        responseDTO.setMsg(msg);
        responseDTO.setSuccess(false);
        responseDTO.setStatus(0);
        responseDTO.setData(t);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> error(ResponseDTO responseDTO) {
        return error(responseDTO.getCode(), responseDTO.getMsg());
    }

    public String getMsg() {
        return this.msg;
    }

    public ResponseDTO setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public Integer getStatus() {
        return this.status;
    }

    public ResponseDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public int getCode() {
        return this.code;
    }

    public ResponseDTO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ResponseDTO setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "code=" + code + ", msg='" + msg + '\'' + ", success=" + success + ", items=" + data +
                '}';
    }
}
