package pers.wh.test.dubbo;


import java.util.concurrent.CompletableFuture;

/**
 * @author wanghui
 * @date 2019/5/6 13:41
 */
public interface HelloService {

    String sayHello(String name);

    CompletableFuture<String> sayHelloAsyn(String name);

}
