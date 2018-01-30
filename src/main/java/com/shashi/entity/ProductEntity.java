package com.shashi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "prodSeq", sequenceName = "EMP_SEQ", allocationSize = 100)
	@GeneratedValue(generator = "prodSeq")
	private Long id;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_DESC")
	private String description;

	@Column(name = "PRODUCT_CODE")
	private String productCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", productName=" + productName + ", description=" + description
				+ ", productCode=" + productCode + "]";
	}

}
