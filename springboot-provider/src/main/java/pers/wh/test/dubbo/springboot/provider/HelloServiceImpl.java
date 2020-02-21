package pers.wh.test.dubbo.springboot.provider;

import org.apache.dubbo.config.annotation.Service;
import pers.wh.test.dubbo.HelloService;
import pers.wh.test.dubbo.exception.BaseException;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.CompletableFuture;

/**
 * @author wanghui
 * @date 2020/2/19 21:59
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        throw new ConstraintViolationException(null);
    }

    @Override
    public CompletableFuture<String> sayHelloAsyn(String name) {
        return null;
    }
}
