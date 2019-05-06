package pers.wh.test.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghui
 * @date 2019/5/6 14:04
 */
@RestController
public class HelloController {

    @Reference(loadbalance="consistenthash")
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(String s) {
        String hello = helloService.sayHello(s);
        return hello;
    }
}
