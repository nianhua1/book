<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.recOrder").click(function () {
				return confirm("你确定要确认收货吗？");
            });
        });
	</script>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@include file="/pages/common/login_menu.jsp" %>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>

			<c:forEach items="${requestScope.myOrders}" var="myOrder">
				<tr>
					<td>${myOrder.orderId}</td>
					<td>${myOrder.createTime}</td>
					<td>${myOrder.price}</td>
					<td>
						<c:if test="${myOrder.status==0}">
							未发货
						</c:if>
						<c:if test="${myOrder.status==1}">
							已发货<a class="recOrder" style="font-size: 14px " href="orderServlet?action=receiverOrder&status=2&orderId=${myOrder.orderId}">确认收货</a>
						</c:if>
						<c:if test="${myOrder.status==2}">
							已收货
						</c:if>
					</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>