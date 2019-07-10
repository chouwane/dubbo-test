package pers.wh.test.dubbo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 参数回调实现类
 * @author wanghui
 * @date 2019/5/7 11:08
 */
public class CallbackServiceImpl implements CallbackService {

    /**存储所有的订阅者*/
    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<String, CallbackListener>();

    public CallbackServiceImpl() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        System.out.println("listeners: "+listeners.size());
                        for(Map.Entry<String, CallbackListener> entry : listeners.entrySet()){
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                listeners.remove(entry.getKey());
                            }
                        }

                        // 定时触发变更通知
                        Thread.sleep(5000);
                    } catch (Throwable t) { // 防御容错
                        t.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);

        // 发送变更通知
        listener.changed(getChanged(key));
    }

    @Override
    public void addListener(String sid, String contractId, CallbackListener listener) {

    }


    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
