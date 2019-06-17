package com.springcloud.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;

/**
 * 订单明细模块的模型层，用于定义订单明细模块的方法
 * @author 梁应福
 *
 */
public interface OrderDetailsService {
	/**
	 * 查询订单编号的订单明细信息（分页）
	 * @param orderId 订单编号
	 * @param pageNumber 页数
	 * @return 返回com.github.pagehelper.PageInfo<orderDetails>类型的实例
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId,Integer pageNumber);
}
