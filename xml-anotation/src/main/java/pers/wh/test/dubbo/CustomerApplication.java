package pers.wh.test.dubbo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import pers.wh.test.dubbo.service.MyService;

import java.io.IOException;

/**
 * @author wanghui
 * @date 2019/5/9 10:33
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.practice.springboot.dubbo.consumer"})
@ImportResource("classpath:dubbo-consumer.xml")
public class CustomerApplication implements CommandLineRunner {
    @Autowired
    private MyService myService;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CustomerApplication.class, args);

        System.in.read();
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化运行");
        System.out.println(myService.sayHello("1"));
    }
}
