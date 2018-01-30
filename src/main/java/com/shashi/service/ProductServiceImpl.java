package com.shashi.service;

import java.sql.SQLException;

import com.shashi.dao.ProductDAO;
import com.shashi.dao.ProductDAOImpl;
import com.shashi.dao.ProductDetailDAO;
import com.shashi.dao.ProductDetailDAOImpl;
import com.shashi.entity.ProductEntity;
import com.shashi.service.exception.DuplicateProductException;
import com.shashi.service.exception.ProductException;
import com.shashi.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	private ProductMapper mapper = new ProductMapper();

	private ProductDAO dao = new ProductDAOImpl();

	private ProductDetailDAO detailDao = new ProductDetailDAOImpl();

	//@Override
	public ProductVO saveProduct(ProductVO vo) throws DuplicateProductException {
		if (vo == null || vo.getProductName() == null) {
			throw new IllegalArgumentException("Invalid parameters to save product");
		}

		ProductEntity entity = null;

		try {

			if (vo.getId() != null) {
				entity = this.getDao().find(vo.getId());
			}

			if (entity == null) {
				entity = new ProductEntity();
			}
			entity = mapper.mapToProductEntity(entity, vo);

			if (vo.getId() == null) {
				entity = this.getDao().createProduct(entity);
			} else {
				this.getDao().updateProduct(entity);

			}
			this.getDetailDao().saveProductDetail(entity);
			vo = mapper.mapToProductVO(entity, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}

		return vo;
	}

	ProductDAO getDao() {
		return dao;
	}

	ProductDetailDAO getDetailDao() {
		return detailDao;
	}
	
	// commented after @Spy
	/*void setDao(ProductDAO dao) {
		this.dao = dao;
	}

	void setDetailDao(ProductDetailDAO detailDao) {
		this.detailDao = detailDao;
	}*/
}
