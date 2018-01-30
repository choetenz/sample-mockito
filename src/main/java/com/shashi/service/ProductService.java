package com.shashi.service;

import java.sql.SQLException;

import com.shashi.service.exception.DuplicateProductException;
import com.shashi.vo.ProductVO;

public interface ProductService {
	ProductVO saveProduct(ProductVO vo) throws DuplicateProductException,
			SQLException;
}
