package com.lefer.demo4doc.common;

import com.fasterxml.jackson.annotation.JsonView;
import com.lefer.demo4doc.model.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fang on 17-7-3.
 */
@ApiModel("统一响应模型")
public class Result {
    @ApiModelProperty("响应状态")
    @JsonView(View.Summary.class)
    private int status;
    @ApiModelProperty("响应消息")
    @JsonView(View.Summary.class)
    private String message;
    @ApiModelProperty("响应数据")
    @JsonView(View.Summary.class)
    private Object data;

    public Result setStatus(ResultStatus resultStatus) {
        this.status = resultStatus.status;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Result setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
