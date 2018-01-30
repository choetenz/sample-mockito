package com.shashi.vo;

import java.util.Map;
import java.util.Set;

public class ProductVO {
	private Long id;
	private String productName;
	private String description;

	private Set<String> comments;

	private Map<String, String> dataMap;

	public ProductVO() {
	}

	public ProductVO(Long id, String productName, String description) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
	}

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

	public Set<String> getComments() {
		return comments;
	}

	public void setComments(Set<String> comments) {
		this.comments = comments;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", productName=" + productName + ", description=" + description + "]";
	}

}
