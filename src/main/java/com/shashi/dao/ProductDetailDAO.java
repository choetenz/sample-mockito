package com.shashi.dao;

import java.sql.SQLException;

import com.shashi.entity.ProductEntity;

public interface ProductDetailDAO {
	ProductEntity saveProductDetail(ProductEntity entity) throws SQLException;
}
