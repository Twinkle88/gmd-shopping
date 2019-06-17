package com.springcloud.dao;

import com.springcloud.entity.Orders;
import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);
    
    /**
     * 查询ORDERS表中满足条件的订单信息
     * @param orders	查询条件
     * @return	成功返回java.util.List类型的实例，否则返回null
     */
    public abstract List<Orders> selectOrders(Orders orders);
    /**
             * 修改orders表中指定订单编号的订单信息
     * @param orders 查询条件
     * @return 成功返回大于0 的整数，否则返回小于等于0的整数
     */
    public abstract Integer updateOrdersStatus(Orders orders);
    
    /**
     * 查询指定日期范围内的销售额
     * @param orders 查询条件
     * @return 成功返回java.util.List类型的实例，否则返回null
     */
    public abstract List<Orders> selectGroup(Orders orders);
}