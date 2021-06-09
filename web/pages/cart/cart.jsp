<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				return confirm("你确定要删除["+$(this).parent().parent().find("td:first").text()+"]吗？");
            });

			$("a#clearItem").click(function () {
                return confirm("你确定要清空购物车吗？");
            });

			$(".updateCount").change(function () {
			    var itemName = $(this).parent().parent().find("td:first").text();
			    var count = $(this).val();
				var bookid = $(this).attr("bookid");
				if(confirm("你确定要将【"+itemName+"】商品的数量修改为"+count+"？")){
					location.href = "http://localhost:8080/book/cartServlet?action=updateItem&count="+count+"&bookid="+bookid;
				}else{
				    this.value = this.defaultValue;
				}
            });
        });
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_menu.jsp" %>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<C:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">还没有加入购物车，浏览商品吧！</a></td>
				</tr>
			</C:if>

			<C:if test="${not empty sessionScope.cart.items}">
				<C:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input bookid="${entry.value.id}" class="updateCount" style="width: 80px;" type="text" value="${entry.value.count}" />
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&bookid=${entry.value.id}">删除</a></td>
					</tr>
				</C:forEach>
			</C:if>



			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearItem" href="cartServlet?action=clearItem">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>