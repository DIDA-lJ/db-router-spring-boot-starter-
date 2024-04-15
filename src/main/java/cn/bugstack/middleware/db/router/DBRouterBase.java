package cn.bugstack.middleware.db.router;

/**
 * @description: 数据源基础配置
 * @author: linqi
 * @date: 2023/9/22
 * @version: 1.0
 */
public class DBRouterBase {
    /**
     *  数据源表索引
     */
    private String tbIdx;

    public String getTbIdx() {
        return DBContextHolder.getTBKey();
    }

}
