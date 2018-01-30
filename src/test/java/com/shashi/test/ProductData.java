package com.shashi.test;

import com.shashi.entity.ProductEntity;
import com.shashi.vo.ProductVO;

public class ProductData {
	
	public static ProductVO createProduct() {
		final ProductVO vo = new ProductVO();
		vo.setProductName("HP Laptop");
		vo.setDescription("Powerful Laptop");
		return vo;
	}
	
	public static ProductEntity createProductEntity() {
		final ProductEntity entity = new ProductEntity();
		entity.setProductName("HP Laptop");
		entity.setDescription("Powerful Laptop");
		entity.setId(new Long(2000));
		return entity;
	}

}
