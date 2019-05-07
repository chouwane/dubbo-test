package pers.wh.test.dubbo.mock;

import pers.wh.test.dubbo.HelloService;

import java.util.concurrent.CompletableFuture;

/**
 * @author wanghui
 * @date 2019/5/7 12:01
 */
public class HelloServiceMock implements HelloService {
    @Override
    public String sayHello(String name) {
        return "sayHello mock :" + name;
    }

    @Override
    public CompletableFuture<String> sayHelloAsyn(String name) {
        return CompletableFuture.supplyAsync(()->{
            return "sayHelloAsyn mock : "+name;
        });
    }
}
