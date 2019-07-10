package pers.wh.test.dubbo.mock;

import pers.wh.test.dubbo.CallbackListener;
import pers.wh.test.dubbo.CallbackService;

/**
 * @author wanghui
 * @date 2019/5/7 12:09
 */
public class CallbackServiceMock implements CallbackService {
    @Override
    public void addListener(String key, CallbackListener listener) {
        System.out.println("------------------------>"+ key);
    }

    @Override
    public void addListener(String sid, String contractId, CallbackListener listener) {

    }
}
