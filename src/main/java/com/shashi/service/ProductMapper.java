package com.shashi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shashi.entity.ProductEntity;
import com.shashi.vo.ProductVO;

@Component
public class ProductMapper {

	public ProductEntity mapToProductEntity(ProductVO vo) {
		ProductEntity entity = new ProductEntity();
		return mapToProductEntity(entity, vo);
	}

	public ProductEntity mapToProductEntity(ProductEntity entity, ProductVO vo) {
		entity.setProductName(vo.getProductName());
		entity.setDescription(vo.getDescription());
		entity.setId(vo.getId());
		return entity;
	}

	public ProductVO mapToProductVO(ProductEntity entity, ProductVO vo) {
		vo.setProductName(entity.getProductName());
		vo.setDescription(entity.getDescription());
		vo.setId(entity.getId());
		return vo;
	}

	public List<ProductVO> mapToProductVOList(List<ProductEntity> products) {
		List<ProductVO> results = new ArrayList<ProductVO>();

		for (ProductEntity entity : products) {
			results.add(mapToProductVO(entity, new ProductVO()));
		}

		return results;
	}

}
