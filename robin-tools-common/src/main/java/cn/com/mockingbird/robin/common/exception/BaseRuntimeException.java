package cn.com.mockingbird.robin.common.exception;

import cn.com.mockingbird.robin.common.util.response.ResponseCode;
import lombok.Getter;

/**
 * 异常基类
 *
 * @author zhaopeng
 * @date 2023/10/18 0:42
 **/
@Getter
@SuppressWarnings({"unused"})
public abstract class BaseRuntimeException extends RuntimeException {

    /**
     * 500，服务器处理失败
     */
    private int code = ResponseCode.FAIL.getCode();

    public BaseRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

}
