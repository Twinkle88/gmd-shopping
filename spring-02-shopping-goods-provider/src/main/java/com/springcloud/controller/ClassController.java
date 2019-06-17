package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.ClassService;
import com.springcloud.vo.ResultValue;

/**
 * 一级类别与二级类别的控制器
 * @author 梁应福
 *
 */
@RestController
@RequestMapping("type")
public class ClassController {

	@Autowired
	private ClassService classService;
	/**
	 * 查询所有一级类别的信息
	 * @return
	 */
	@RequestMapping(value = "/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法查询所有一级类别的信息，并保存查询结果
			List<Class1> allClass1 = this.classService.selectAllClass1();
			//如果查询成功
			if(allClass1 != null && allClass1.size() > 0) {
				//设置结果状态为0
				rv.setCode(0);
				//创建map集合
				Map<String, Object> map = new HashMap<>();
				//将查询的结果放入Map集合中
				map.put("allClass1", allClass1);
				//将Map集合存入ResultValue对象中
				rv.setDataMap(map);
				
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
	/**
	 * 根据一级类别的编号查询相应的二级类别信息
	 * @param class1Id	一级类别编号
	 * @return
	 */
	@RequestMapping(value = "/selectById")
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法查询所有一级类别的信息，并保存查询结果
			List<Class2> class2ByClass1Id = this.classService.selectClass2ByClass1Id(class1Id);
			//如果查询成功
			if(class2ByClass1Id != null && class2ByClass1Id.size() > 0) {
				//设置结果状态为0
				rv.setCode(0);
				//创建map集合
				Map<String, Object> map = new HashMap<>();
				//将查询的结果放入Map集合中
				map.put("class2ByClass1Id", class2ByClass1Id);
				//将Map集合存入ResultValue对象中
				rv.setDataMap(map);
				
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
}
