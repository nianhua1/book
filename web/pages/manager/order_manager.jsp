<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>		

			<c:forEach items="${requestScope.order}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<td>
						<c:if test="${order.status == 0}">
							<a href="orderServlet?action=sendOrder&status=1&orderId=${order.orderId}">发货</a>
						</c:if>
						<c:if test="${order.status == 1}">
							已发货
						</c:if>
						<c:if test="${order.status == 2}">
							已收货
						</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>