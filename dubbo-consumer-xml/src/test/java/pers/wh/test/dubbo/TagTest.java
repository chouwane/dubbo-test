package pers.wh.test.dubbo;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wanghui
 * @date 2019/5/7 13:15
 */
public class TagTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();

        //设置使用的哪个标签服务
        RpcContext.getContext().setAttachment(Constants.TAG_KEY, "Ni1902");

        HelloService helloService = (HelloService) context.getBean("helloService");
        String s = helloService.sayHello("1");
        System.out.println("-------------------->"+s);
    }

}
