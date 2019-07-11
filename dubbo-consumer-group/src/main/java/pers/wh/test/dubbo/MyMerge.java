package pers.wh.test.dubbo;

import org.apache.dubbo.rpc.cluster.Merger;

/**
 * @author wanghui
 * @date 2019/7/11 16:36
 */
public class MyMerge implements Merger<Long> {

    @Override
    public Long merge(Long... items) {
        if(items != null && items.length > 0){
            long min = items[0];
            for (Long item : items) {
                if(item < min){
                    min = item;
                }
            }
            return min;
        }
        return 0L;
    }
}
