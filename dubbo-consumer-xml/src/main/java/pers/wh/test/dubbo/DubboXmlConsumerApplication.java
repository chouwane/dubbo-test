package pers.wh.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wanghui
 * @date 2019/5/6 14:05
 */
@SpringBootApplication
@ImportResource(value = {"classpath:consumer.xml"})
public class DubboXmlConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboXmlConsumerApplication.class, args);
    }
}
