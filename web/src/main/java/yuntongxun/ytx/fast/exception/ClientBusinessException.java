package yuntongxun.ytx.fast.exception;

import lombok.Data;

/**
 * 客户端异常对象
 * @author sintang
 * @date 2019-07-18
 **/
@Data
public class ClientBusinessException extends RuntimeException {

    private Integer code;

    private Object info;

    public ClientBusinessException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ClientBusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ClientBusinessException(String message, Object obj){
        super(message);
        this.info = obj;
    }
}
