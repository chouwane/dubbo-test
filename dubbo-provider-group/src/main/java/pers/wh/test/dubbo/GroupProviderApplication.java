package pers.wh.test.dubbo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * @author wanghui
 * @date 2019/7/10 16:03
 */
@SpringBootApplication
@ImportResource("classpath:provider.xml")
public class GroupProviderApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GroupProviderApplication.class, args);
        //阻塞作用，否则会由于不是web项目，执行main方法后立即停止服务。
        System.in.read();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
