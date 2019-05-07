package pers.wh.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wanghui
 * @date 2019/5/6 14:05
 */
@SpringBootApplication
@ImportResource(value = {"classpath:consumer.xml"})
public class DubboXmlConsumerApplication {

    public static void main(String[] args) {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        CallbackService callbackService = (CallbackService) context.getBean("callbackService");
        callbackService.addListener("foo.bar", msg -> System.out.println("callback1:" + msg));*/

        SpringApplication.run(DubboXmlConsumerApplication.class, args);


    }
}
