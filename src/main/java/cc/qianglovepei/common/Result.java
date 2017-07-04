package cc.qianglovepei.common;

import cc.qianglovepei.util.DateUtil;

import java.util.Date;

public class Result<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;

    private String message;

    private T data;

    private String url;

    private String serverTime;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public static <T> Result<T> Success(T data){
        Result result = new Result();
        result.setCode(OK);
        result.setMessage("请求成功");
        result.setData(data);
        result.setServerTime(DateUtil.convertDateToStr(new Date(),"yyyy-MM-dd hh:mm:ss"));
        return result;
    }
}
