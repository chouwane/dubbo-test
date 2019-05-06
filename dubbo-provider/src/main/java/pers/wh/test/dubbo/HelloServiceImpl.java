package pers.wh.test.dubbo;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author wanghui
 * @date 2019/5/6 13:58
 */
@Component
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("sayHello: "+ name);
        return "Hello " + name;
    }

    @Override
    public CompletableFuture<String> sayHelloAsyn(String name) {
        RpcContext savedContext = RpcContext.getContext();
        // 建议为supplyAsync提供自定义线程池，避免使用JDK公用线程池
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(savedContext.getAttachment("name"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async response from provider: "+ name;
        });
    }
}
