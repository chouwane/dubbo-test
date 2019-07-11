package pers.wh.test.dubbo;

import java.util.List;

/**
 * 测试分组聚合的接口
 * @author wanghui
 * @date 2019/7/10 15:53
 */
public interface GroupService {

    /**
     * 成交入库服务会有多个，所以这里取的是每个服务最小入库的成交单号
     * 这里消费者收到的结果是多个服务的合集
     * @param contractId 合约编码
     * @return 每个服务最小的成交单号
     */
    List<Long> getMinFillId(String contractId);

    Long getClearingFillId(String contractId);
}
