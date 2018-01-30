package com.shashi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.shashi.dao.ProductDAO;
import com.shashi.dao.ProductDetailDAO;
import com.shashi.entity.ProductEntity;
import com.shashi.test.ProductData;
import com.shashi.vo.ProductVO;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
	
	@Mock
	private ProductDAO dao;
	
	@Mock
	private ProductDetailDAO detailDao;
	
	// private final ProductServiceImpl impl = new ProductServiceImpl();
	// spy acts as a partial mock, don't say new() once u have Spy
	@Spy
	private ProductServiceImpl impl;
	
	private ProductVO input;
	private ProductEntity entity;
	
	@Before
	public void setup() {
		input = ProductData.createProduct();
		entity = ProductData.createProductEntity();
		//impl.setDao(dao);
		//impl.setDetailDao(detailDao);
		when(impl.getDao()).thenReturn(dao);
		when(impl.getDetailDao()).thenReturn(detailDao);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveProductNullProduct() {
		final ProductServiceImpl impl = new ProductServiceImpl();
		impl.saveProduct(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveProductNullProductName() {
		final ProductVO input = ProductData.createProduct();
		input.setProductName(null);
		final ProductServiceImpl impl = new ProductServiceImpl();
		impl.saveProduct(input);
	}
	
	@Test()
	public void testCreateProduct() throws SQLException {
		
		when(dao.createProduct(any(ProductEntity.class))).thenReturn(entity);
		
		final ProductVO result = impl.saveProduct(input);
		
		assertResult(result);
		
		// make sure the methods are called in order 
		InOrder inorder = inOrder(dao, detailDao);
		
		// check that create Product and save product are called once only n properly
		inorder.verify(dao, times(1)).createProduct(any(ProductEntity.class));
		inorder.verify(detailDao).saveProductDetail(any(ProductEntity.class));
		
		// check that find() and updateProduct() are never called in createProduct() method call
		verify(dao, never()).find(any(Long.class));
		verify(dao, never()).updateProduct(any(ProductEntity.class));
	}
	
	@Test()
	public void testUpdateProduct() throws SQLException {
		
		input.setId(new Long(2000));
		input.setProductName("Abc product");
		
		when(dao.find(any(Long.class))).thenReturn(entity);
		
		final ProductVO result = impl.saveProduct(input);
		assertResult(result);
		
		InOrder inorder = inOrder(dao, detailDao);
		
		inorder.verify(dao, times(1)).find(any(Long.class));
		inorder.verify(dao).updateProduct(any(ProductEntity.class));
		inorder.verify(detailDao).saveProductDetail(any(ProductEntity.class));
		
		verify(dao, never()).createProduct(any(ProductEntity.class));
	}

	@Test()
	public void testUpdateProductException() throws SQLException {
		
		input.setId(new Long(2000));
		input.setProductName("Abc product");
		
		when(dao.find(any(Long.class))).thenThrow(new SQLException("The database is not available right now"));
		
		final ProductVO result = impl.saveProduct(input);
		impl.saveProduct(input);
	}
	
	private void assertResult(final ProductVO result) {
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(input.getProductName(), result.getProductName());
		assertEquals(input.getDescription(), result.getDescription());
	}

}
