package pers.wh.test.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author wanghui
 * @date 2019/5/9 17:38
 */
public class CallbackTest{

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();


        CallbackService callbackService = (CallbackService)context.getBean("callbackService");
        Callback callback = new Callback(1);
        callbackService.addListener("1", callback);
        callbackService.addListener("1", callback);
        callbackService.addListener("1", callback);

        System.in.read();
    }

    static class Callback implements CallbackListener{
        private int c;

        public Callback(int c){
            this.c = c;
        }

        @Override
        public void changed(String msg) {
            System.out.println(c + "--->监听到---->" + msg);
        }
    }
}
