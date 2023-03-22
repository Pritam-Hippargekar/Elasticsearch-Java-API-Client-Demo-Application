//package com.jentsch.skyer9;
//
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Data
//public class ResponseResult {
//    private ResponseResult() {
//    }
//
//    private Boolean success;
//
//    private Integer code;
//
//    private String message;
//
//    private Map<String, Object> RESPONSE_DATA_MAP = new HashMap<>();
//
//    public static ResponseResult ok() {
//        ResponseResult responseResult = new ResponseResult();
//        responseResult.setSuccess(true);
//        responseResult.setCode(ResultCode.SUCCESS);
//        responseResult.setMessage("成功");
//        return responseResult;
//    }
//
//    public static ResponseResult error() {
//        ResponseResult responseResult = new ResponseResult();
//        responseResult.setSuccess(false);
//        responseResult.setCode(ResultCode.ERROR);
//        responseResult.setMessage("失败");
//        return responseResult;
//    }
//
//    public ResponseResult success(Boolean success) {
//        this.setSuccess(success);
//        return this;
//    }
//
//    public ResponseResult message(String message) {
//        this.setMessage(message);
//        return this;
//    }
//
//    public ResponseResult code(Integer code) {
//        this.setCode(code);
//        return this;
//    }
//
//    public ResponseResult data(String key, Object value) {
//        this.RESPONSE_DATA_MAP.put(key, value);
//        return this;
//    }
//
//    public ResponseResult data(Map<String, Object> map) {
//        this.setRESPONSE_DATA_MAP(map);
//        return this;
//    }
//}
