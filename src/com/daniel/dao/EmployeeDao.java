package com.daniel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.daniel.model.Employee;
import com.daniel.util.DbUtil;

public class EmployeeDao {

	private Connection connection;

	public EmployeeDao() {
		connection = DbUtil.getConnection();
	}

	public void addEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Employees(EmployeeName,EmployeeDob,EmployeeAddress,EmployeeEmail,password) values (?, ?, ?,?,? )");
			// Parameters start with 1
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setDate(2,new Date(employee.getEmployeeDob().getTime()));
			preparedStatement.setString(3, employee.getEmployeeAddress());
			preparedStatement.setString(4, employee.getEmployeeEmail());
			preparedStatement.setString(5, employee.getPassword());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(int EmployeeId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Employees where EmployeeId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, EmployeeId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Employees set EmployeeName=?, EmployeeDob=?, EmployeeAddress=? " +
							 "where EmployeeId=?");
			// Parameters start with 1
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setDate(2,new Date(employee.getEmployeeDob().getTime()));
			preparedStatement.setString(3, employee.getEmployeeAddress());
			preparedStatement.setInt(4, employee.getEmployeeId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> Employees = new ArrayList<Employee>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Employees");
			while (rs.next()) {
				Employee Employee = new Employee();
				Employee.setEmployeeId(rs.getInt("EmployeeId"));
				Employee.setEmployeeName(rs.getString("EmployeeName"));
				Employee.setEmployeeDob(rs.getDate("EmployeeDob"));
				Employee.setEmployeeAddress(rs.getString("EmployeeAddress"));
				Employee.setEmployeeEmail(rs.getString("EmployeeEmail"));
				Employees.add(Employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Employees;
	}
	
	public Employee getEmployeeById(int EmployeeId) {
		Employee employee = new Employee();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from Employees where Employeeid=?");
			preparedStatement.setInt(1, EmployeeId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				employee.setEmployeeId(rs.getInt("EmployeeId"));
				employee.setEmployeeName(rs.getString("EmployeeName"));
				employee.setEmployeeDob(rs.getDate("EmployeeDob"));
				employee.setEmployeeAddress(rs.getString("EmployeeAddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	public Employee getEmployeeByEmail(String username) {
		Employee employee = new Employee();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from Employees where EmployeeEmail=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				employee.setEmployeeId(rs.getInt("EmployeeId"));
				employee.setEmployeeName(rs.getString("EmployeeName"));
				employee.setEmployeeDob(rs.getDate("EmployeeDob"));
				employee.setEmployeeAddress(rs.getString("EmployeeAddress"));
				employee.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
		
	}
}
