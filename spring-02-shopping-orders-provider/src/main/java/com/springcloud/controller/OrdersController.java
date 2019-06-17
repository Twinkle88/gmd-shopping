package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

/**
 * 订单模块的控制层
 * @author 梁应福
 *
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders,@RequestParam("pageNumber") Integer pageNumber) {
		
		ResultValue rv = new ResultValue();
		
		try {
			PageInfo<Orders> selectOrder = this.ordersService.selectOrder(orders, pageNumber);
			List<Orders> list = selectOrder.getList();
			if(list != null && list.size() > 0) {
				
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("ordersList", list);
				
				PageUtils pageUtils = new PageUtils(selectOrder.getPages() * PageUtils.PAGE_ROW_COUNT);
				map.put("pageUtils", pageUtils);
				
				map.put("selectOrder", selectOrder);
				rv.setDataMap(map);
				
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
	
		return rv;
	}
	/**
	 * 修改指定编号订单的订单状态
	 * @param orders 修改的订单信息
	 * @return
	 */
	
	@RequestMapping(value = "/updateOrdersStatus")
	public ResultValue updateOrdersStatus(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateOrdersStatus = this.ordersService.updateOrdersStatus(orders);
			if(updateOrdersStatus>0) {
				rv.setCode(0);
				rv.setMessage("修改指定编号状态信息成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		rv.setCode(1);
		rv.setMessage("修改指定编号状态信息失败！！！");
		return rv;
		
	}
	
	/**
	 * 查询指定日期范围内的销售额
	 * @param orders 查询条件
	 * @return
	 */
	@RequestMapping(value = "/selectGroup")
	public ResultValue selectGroup(Orders orders) {
		
		ResultValue rv = new ResultValue();
		try {
			List<Orders> list = this.ordersService.selectGroup(orders);
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//创建两个集合用于保存折线图，x轴和y轴的数据
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//将查询的结果中商品名称存入x中，销量存入y中
				for (Orders order : list) {
					x.add(order.getOrdersMonth());
					y.add((order.getOrdersPrice()));
				}
				//创建一个Map集合用于保存获取到的x轴和y轴的数据
				Map<String, Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				//将Map集合添加到ResultValue中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获取数据失败！！！");
		return rv;
	}

}
