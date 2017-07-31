package com.lefer.demo4doc.common;

/**
 * @author fang
 * @version
 * @creatdate 17-7-3
 */
public enum  ResultStatus {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误


    public int status;

    ResultStatus(int status) {
        this.status = status;
    }
}
