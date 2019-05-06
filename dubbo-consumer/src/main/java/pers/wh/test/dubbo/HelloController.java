package pers.wh.test.dubbo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

/**
 * @author wanghui
 * @date 2019/5/6 14:04
 */
@RestController
public class HelloController {

    @Reference(version = "1.0.0", loadbalance="consistenthash", timeout = 10000)
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(String s) {
        String hello = helloService.sayHello(s);
        System.out.println(hello);
        return hello;
    }

    @RequestMapping("/hello2")
    public DeferredResult<String> helloAsyn(String s) {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        CompletableFuture<String> future = helloService.sayHelloAsyn(s);
        future.whenComplete((v, e) -> {
            if (e != null) {
                e.printStackTrace();
                deferredResult.setErrorResult(e);
            } else {
                System.out.println("Response: " + v);
                deferredResult.setResult(v);
            }
        });
        System.out.println("----->");
        return deferredResult;
    }
}
