package pers.wh.test.dubbo.exception;

/**
 * @author wanghui
 * @date 2020/2/19 22:03
 */
public class BaseException extends RuntimeException {

    public BaseException(String desc){
        super(desc);
    }

    public BaseException(Throwable cause){
        super(cause);
    }

}
