package com.springcloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *CLASS1表对应的实体类：用于封装实体类CLASS1表中的一行数据
 * @author 梁应福
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class1 {
	/**
	 * 类别一编号
	 */
    private Integer class1Id;

    /**
	 * 类别一名称
	 */
    private String class1Name;

    /**
	 * 类别一序号
	 */
    private Integer class1Num;

    /**
	 * 类别一备注
	 */
    private String remark;
   
}