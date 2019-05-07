package pers.wh.test.dubbo;

/**
 * 参数回调
 * @author wanghui
 * @date 2019/5/7 11:06
 */
public interface CallbackService {

    void addListener(String key, CallbackListener listener);

}
