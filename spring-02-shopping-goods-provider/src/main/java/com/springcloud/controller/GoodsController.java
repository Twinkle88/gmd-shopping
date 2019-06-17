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
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

/**
 * 商品模块控制层
 * @author 梁应福
 * 当select子句中出现了聚合函数与非聚合函数的列时，必须使用group by子句
        当使用group by子句时，select子句中非聚合函数的列必须出现在group by子句中
   select c1.class1_name,count(g.goods_id) from class1 c1 join class2 c2 on c1.class1_id=c2.class1_id join goods g on c2.class2_id=g.class2_id group by c1.class1_name;
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法添加新的商品信息，并且得到添加是否成功
			Integer insert = this.goodsService.insert(goods);
			//如果查询成功
			if(insert > 0) {
				//设置结果状态为0
				rv.setCode(0);
				rv.setMessage("商品信息录入成功");
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品信息录入失败");
		return rv;
	}
	
	@RequestMapping(value = "/select")
	public ResultValue select(Goods goods,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			//查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			//从分页信息中获得商品信息
			List<Goods> list = pageInfo.getList();
			//如果查询到了满足条件的商品信息
			if(list != null && list.size() > 0) {
				
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				//将商品信息以指定的键存入集合中
				map.put("goodsList", list);
				
				//设置分页信息
				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				//将分页信息以指定的名字存入Map集合中
				map.put("pageUtils", pageUtils);
				
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的商品信息！！！");
		return rv;
	}
	@RequestMapping(value="/updateById")
	public ResultValue updateById(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法修改商品信息，并且得到修改是否成功
			Integer update = this.goodsService.updateGoodsById(goods);
			//如果查询成功
			if(update > 0) {
				//设置结果状态为0
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;		
	}
	
	@RequestMapping(value="/update")
	public ResultValue update(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法修改商品图片信息，并且得到修改是否成功
			Integer update = this.goodsService.update(goods);
			//如果查询成功
			if(update > 0) {
				//设置结果状态为0
				rv.setCode(0);
				rv.setMessage("商品图片信息修改成功！！！");
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品图片信息修改失败！！！");
		return rv;	
	}
	
	/**
	 * 查询销量前十的商品信息
	 * @return
	 */
	@RequestMapping(value = "/selectGroup")
	public ResultValue selectGroup() {
		ResultValue rv = new ResultValue();
		try {
			List<Goods> list = this.goodsService.selectGroup();
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//创建两个集合用于保存柱状图，x轴和y轴的数据
				List<String> x = new ArrayList<>();
				List<Integer> y = new ArrayList<>();
				//将查询的结果中商品名称存入x中，销量存入y中
				for (Goods goods : list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
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
