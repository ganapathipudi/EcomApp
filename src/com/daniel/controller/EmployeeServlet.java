package com.daniel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daniel.dao.EmployeeDao;
import com.daniel.dao.ProductDao;
import com.daniel.model.Employee;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String inser_user_page = "/Employee.jsp";
	private EmployeeDao employeDao;
	private ProductDao productDao;

	public EmployeeServlet() {
		super();
		employeDao = new EmployeeDao();
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("insert")) {
			forward = inser_user_page;
		} else if (action.equalsIgnoreCase("login")) {

			Employee employee = employeDao.getEmployeeByEmail(request.getParameter("userName"));
			if (employee.getPassword().equalsIgnoreCase(request.getParameter("password"))) {
				request.setAttribute("products", productDao.getAllProducts());
				forward="/products.jsp";
			} else {
				forward="/error.jsp";
			}
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		employee.setEmployeeName(request.getParameter("employeeName"));

		try {
			Date employeeDob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("employeeDob"));
			employee.setEmployeeDob(employeeDob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employee.setEmployeeAddress(request.getParameter("employeeAddress"));
		employee.setEmployeeEmail(request.getParameter("employeeEmail"));
		employee.setPassword(request.getParameter("employeePassword"));
		String employeeid = request.getParameter("employeeId");
		if (employeeid == null || employeeid.isEmpty()) {
			employeDao.addEmployee(employee);
		}
		RequestDispatcher view = request.getRequestDispatcher("/success.jsp");
		view.forward(request, response);
	}
}