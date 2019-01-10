package com.yunchao.hsh.utils.superdir;

import com.yunchao.hsh.utils.enumdir.ErrorCode;
import com.yunchao.hsh.utils.exception.BaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.ValidationUtils;

import java.io.Serializable;

/**
 * Created by wangqi on 2017/11/7
 */
@ApiModel(value = "接口返回说明")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -6681069801029150974L;
    static Log log = LogFactory.getLog(Result.class);
    @ApiModelProperty(value = "成功标识;true:成功;false：失败")
    private boolean success;
    @ApiModelProperty(value = "描述信息")
    private String message;

    @ApiModelProperty(value = "数据内容")
    private T data;

    public Result() {
    }


    public Result(boolean success) {
        this.success = success;
        if (success) {
            this.setMessage("操作成功");
        } else {
            this.setMessage("操作失败");
        }

    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static <D> Result<D> build(Result.Command<D> cmd) {
        D data = null;
        Result result = new Result();

        try {
            data = cmd.execute();
            result.setSuccess(true);
            result.setData(data);
        } catch (BaseException var4) {
            log.error(var4.getMessage(), var4);
            result.setSuccess(false);
            result.setMessage(var4.getMessage());
        } catch (Exception var5) {
            log.error(var5.getMessage(), var5);
            result.setSuccess(false);
            result.setMessage((new BaseException(ErrorCode.FR_011, new Object[0])).getMessage());
        }

        return result;
    }

    public static Result ok(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("操作成功");
        result.setData(obj);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage("操作失败");
        result.setData(null);
        return result;
    }

    public static Result build(String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setData(null);
        result.setMessage(String.valueOf(message));
        return result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }


    public interface Command<D> {
        D execute();
    }
}
