package pers.wh.test.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author wanghui
 * @date 2019/5/6 13:58
 */
@Component
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("sayHello: "+ name);
        return "Hello " + name;
    }
}
