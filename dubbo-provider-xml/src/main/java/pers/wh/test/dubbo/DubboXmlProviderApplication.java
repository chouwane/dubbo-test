package pers.wh.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wanghui
 * @date 2019/5/6 13:59
 */
@SpringBootApplication
@ImportResource(value = "classpath:provider.xml")
public class DubboXmlProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboXmlProviderApplication.class, args);
        try {
            //阻塞作用，否则会由于不是web项目，执行main方法后立即停止服务。
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
