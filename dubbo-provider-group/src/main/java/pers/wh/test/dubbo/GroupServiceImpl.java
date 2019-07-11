package pers.wh.test.dubbo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wanghui
 * @date 2019/7/10 16:06
 */
public class GroupServiceImpl implements GroupService {
    @Override
    public List<Long> getMinFillId(String contractId) {
        long r = getRandomLong(1, 100);

        List<Long> list = new ArrayList<>();
        list.add(r);
        return list;
    }

    @Override
    public Long getClearingFillId(String contractId) {
        return getRandomLong(1, 100);
    }

    public long getRandomLong(long min, long max) {
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        System.out.println("随机Long值"+rangeLong);
        return rangeLong;
    }
}
