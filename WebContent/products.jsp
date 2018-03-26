<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Products</title>
</head>
<body>
	<h2>Product Management</h2>
	<table border=1>
		<thead>
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Price</th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><c:out value="${product.productId}" /></td>
					<td><c:out value="${product.productName}" /></td>
					<td><c:out value="${product.productPrice}" /></td>
					<td><a
						href="products?action=addCart&productId=<c:out value="${product.productId}"/>">AddToCart</a></td>
					<td><a
						href="products?action=delete&productId=<c:out value="${product.productId}"/>">UpdateCart</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>