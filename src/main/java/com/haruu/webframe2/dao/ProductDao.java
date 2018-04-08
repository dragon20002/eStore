package com.haruu.webframe2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.haruu.webframe2.model.Product;

@Repository
public class ProductDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Product> getProducts() {
		String sql = "SELECT * FROM product";
		return jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int cnt) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});
	}

	public Product getProductById(int id) {
		String sql = "SELECT * FROM product WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int cnt) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}			
		});
	}
	
	public boolean addProduct(Product product) {
		String sql = "insert into product (name, category, price, manufacturer, unitInStock, description)"
				+ " values (?, ?, ?, ?, ?, ?)";

		return jdbcTemplate.update(sql, new Object[] {
				product.getName(),
				product.getCategory(),
				product.getPrice(),
				product.getManufacturer(),
				product.getUnitInStock(),
				product.getDescription()	
			}) > 0;
	}

	public boolean deleteProduct(int id) {
		String sql = "delete from product where id=?";

		return jdbcTemplate.update(sql, id) > 0;
	}

	public boolean updateProduct(Product product) {
		String sql = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, description=?"
				+ "where id=?";
		
		return jdbcTemplate.update(sql, new Object[] {
				product.getName(),
				product.getCategory(),
				product.getPrice(),
				product.getManufacturer(),
				product.getUnitInStock(),
				product.getDescription(),
				product.getId()
			}) > 0;
	}

}
