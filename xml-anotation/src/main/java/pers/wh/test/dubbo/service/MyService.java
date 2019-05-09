package pers.wh.test.dubbo.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import pers.wh.test.dubbo.HelloService;

/**
 * @author wanghui
 * @date 2019/5/9 10:57
 */
@Component
public class MyService {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    public String sayHello (String name){
        return helloService.sayHello(name);
    }

}
