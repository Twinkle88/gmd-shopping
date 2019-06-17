package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

/**
 * 订单模块的模型层的实现类，用于实现订单模块的方法
 * @author 李星
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersMapper ordersMapper;

	/**
	 * 查询满足条件的订单信息（分页功能�?
	 * 
	 * @param orders     查询条件
	 * 
	 * @param pageNumber 页数
	 * @return 返回com.github.pagehelper.PageInfo类型的实�?
	 */
	@Override
	public PageInfo<Orders> selectOrder(Orders orders,Integer pageNumber) {
		if(orders.getUser() != null) {
			//在用户名称两端加%
			orders.getUser().setUserName("%"+orders.getUser().getUserName()+"%");
		}
		//设置每页的信息
		PageHelper.startPage(pageNumber+1,PageUtils.PAGE_ROW_COUNT);
		//查询满足条件的商品信息
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		return new PageInfo<>(list);
	}
	//添加事务，
	@Transactional
	@Override
	public Integer updateOrdersStatus(Orders orders) {
		//调用持久化层ordersMapper的updateOrdersStatus方法
		return this.ordersMapper.updateOrdersStatus(orders);
	}
	
	/**
	 * 查询指定日期范围内的销售额
	 */
	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.ordersMapper.selectGroup(orders);
	}

}
