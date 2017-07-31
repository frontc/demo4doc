package com.lefer.demo4doc.common;

/**
 * Created by fang on 17-7-3.
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setStatus(ResultStatus.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {

        return new Result()
                .setStatus(ResultStatus.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static String genSuccessString(String data) {
        return "{\"status\":" + ResultStatus.SUCCESS + ",\"message\":\"" + DEFAULT_SUCCESS_MESSAGE + "\",\"data\":" + data + "}";
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setStatus(ResultStatus.FAIL)
                .setMessage(message);
    }
}

