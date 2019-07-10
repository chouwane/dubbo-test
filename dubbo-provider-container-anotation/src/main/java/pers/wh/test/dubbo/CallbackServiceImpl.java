package pers.wh.test.dubbo;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author wanghui
 * @date 2019/5/29 13:15
 */
@Component
@Service(version = "1.0.0")
public class CallbackServiceImpl implements CallbackService{


    @Override
    public void addListener(String key, CallbackListener listener) {
        System.out.println(key);
    }

    @Override
    public void addListener(String sid, String contractId, CallbackListener listener) {

    }
}
