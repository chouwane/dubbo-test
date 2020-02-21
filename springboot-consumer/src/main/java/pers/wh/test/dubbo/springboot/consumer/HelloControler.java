package pers.wh.test.dubbo.springboot.consumer;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.wh.test.dubbo.HelloService;

/**
 * @author wanghui
 * @date 2020/2/19 22:12
 */
@RestController
public class HelloControler {
    @Reference
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        try {
            return helloService.sayHello("张三");
        }catch (Exception e){
            return e.getClass().toString();
        }
    }
}
