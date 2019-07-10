package pers.wh.test.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.List;

/**
 * @author wanghui
 * @date 2019/7/10 16:51
 */
public class ConsumerTest {

    public static void main(String[] args) {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("xxx");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("multicast://224.5.6.7:1234");

        // 引用远程服务
        ReferenceConfig<GroupService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(GroupService.class);
        reference.setGroup("*");
        reference.setMerger("true");

        // 和本地bean一样使用xxxService
        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        GroupService groupService = reference.get();

        List<Long> list = groupService.getMinFillId("Ni1901");
        if(list != null){
            System.out.println("Size = "+list.size());
            list.forEach(System.out::println);
        }
    }

}
