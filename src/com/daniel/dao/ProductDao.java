package com.daniel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.daniel.model.Product;
import com.daniel.util.DbUtil;

public class ProductDao {

	private Connection connection;

	public ProductDao() {
		connection = DbUtil.getConnection();
	}

	public void addProduct(Product product) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into products(productName,productPrice) values (?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getProductPrice());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(int productId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from products where productId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, productId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct(Product product) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update products set productName=?, productPrice=? " +
							 "where productId=?");
			// Parameters start with 1
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getProductPrice());
			preparedStatement.setInt(3, product.getProductId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from products");
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	public Product getProductById(int productId) {
		Product product = new Product();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from products where productid=?");
			preparedStatement.setInt(1, productId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}
}
