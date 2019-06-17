package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OrderDetail表对应的实体类，用于封装数据库一行订单明细
 * @author 梁应福
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = -2281236879693007932L;
	/**
	 * 订单明细编号
	 */
	
	private Integer orderDetailId;
	
	/**
	 * 订单编号
	 */
    private Integer orderId;
    
    /**
     * 商品编号
     */
    private Integer goodsId;
    
    /**
     * 商品名称
     */
    private String goodsName;
    
    /**
     * 成交价
     */
    private Double transactionPrice;
    
    /**
     * 成交数量
     */
    private Integer transactionCount;
    
}