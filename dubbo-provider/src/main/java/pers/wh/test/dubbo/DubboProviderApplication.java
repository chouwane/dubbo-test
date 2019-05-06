package pers.wh.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanghui
 * @date 2019/5/6 13:59
 */
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
        try {
            //阻塞作用，否则会由于不是web项目，执行main方法后立即停止服务。
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
