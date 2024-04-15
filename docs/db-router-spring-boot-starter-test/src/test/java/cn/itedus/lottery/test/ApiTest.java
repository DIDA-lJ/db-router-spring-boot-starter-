package cn.itedus.lottery.test;

import cn.itedus.lottery.infrastructure.dao.IUserStrategyExportDao;
import cn.itedus.lottery.infrastructure.dao.IUserTakeActivityDao;
import cn.itedus.lottery.infrastructure.po.UserStrategyExport;
import cn.itedus.lottery.infrastructure.po.UserTakeActivity;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linqi
 * @description 功能测试

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;
    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Test
    public void test_userTakeActivityDao() {
        UserTakeActivity req = new UserTakeActivity();
        req.setuId("fuzhengwei");
        req.setActivityId(100001L);
        UserTakeActivity res = userTakeActivityDao.queryNoConsumedTakeActivityOrder(req);
        logger.info("请求参数：{} 测试结果：{}", JSON.toJSONString(req), JSON.toJSONString(res));
    }

    @Test
    public void test_userStrategyExportDao(){
        List<UserStrategyExport> res = userStrategyExportDao.queryUserStrategyExportByUId("fustack");
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

}
