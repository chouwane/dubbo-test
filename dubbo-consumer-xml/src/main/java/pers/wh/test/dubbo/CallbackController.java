package pers.wh.test.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author wanghui
 * @date 2019/5/7 11:17
 */
@Component
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    @PostConstruct
    public void hello() {
        callbackService.addListener("c", new CallbackListener() {
            @Override
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });
    }

}
