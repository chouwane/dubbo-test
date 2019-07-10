package pers.wh.test.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.util.List;

/**
 * @author wanghui
 * @date 2019/7/10 16:03
 */
@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class GroupConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GroupConsumerApplication.class, args);
        //阻塞作用，否则会由于不是web项目，执行main方法后立即停止服务。
        System.in.read();
    }

    @Autowired
    private GroupService groupService;

    @Override
    public void run(String... args) throws Exception {
        List<Long> list = groupService.getMinFillId("Ni1901");
        if(list != null){
            System.out.println("Size = "+list.size());
            list.forEach(System.out::println);
        }
    }
}
