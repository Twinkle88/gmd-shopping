package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailsService;
import com.springcloud.vo.ResultValue;

/**
 * 订单明细模块的控制层
 * 
 * @author 李星
 *
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;

	@RequestMapping(value = "/selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,
			@RequestParam("pageNumber") Integer pageNumber) {

		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法通过指定的编号查询相应订单详情的信息，并且得到查询是否成功
			PageInfo<OrderDetail> selectByOrderId = this.orderDetailsService.selectByOrderId(orderId, pageNumber);
			List<OrderDetail> list = selectByOrderId.getList();
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("orderDetailsList", list);

				PageUtils pageUtils = new PageUtils(selectByOrderId.getPages() *PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);

				map.put("selectByOrderId", selectByOrderId);
				rv.setDataMap(map);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("查询订单明细信息失败！！！");
		return rv;
	}
}
