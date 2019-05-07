package pers.wh.test.dubbo;

/**
 * @author wanghui
 * @date 2019/5/7 16:18
 */
public class DubcoProviderContainer {

    public static void main(String[] args) {

        // 使用spring作为容器启动
        // 缺省只加载 spring
        org.apache.dubbo.container.Main.main(new String[]{"spring"});

    }

}
