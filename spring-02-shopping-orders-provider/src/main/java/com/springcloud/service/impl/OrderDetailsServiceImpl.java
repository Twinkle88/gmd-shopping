package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailsService;
/**
 * 订单明细模块模型层的实现类，用于实现订单明细模块的方法
 * @author 梁应福
 *
 */
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	public PageInfo<OrderDetail> selectByOrderId(Integer orderId, Integer pageNumber) {
		//设置分页信息,用PageHelper插件，显示第一页的数据，每一行显示几条数据
		PageHelper.startPage(pageNumber+1,5);
		//查询指定编号的订单明细的信息
		List<OrderDetail> list = this.orderDetailMapper.selectByOrderId(orderId);
		//返回分页信息
		return new PageInfo<>(list);
	}


}
