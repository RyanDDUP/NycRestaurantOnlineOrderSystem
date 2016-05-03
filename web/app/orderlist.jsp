<%@ page import="java.util.Map" %>
<%@ page import="domain.Food" %>
<%@ page import="domain.OrderDetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/style/css/index.css" />
	<script type="text/javascript">
		// 通知服务员结账
		function callPay(node) {
			var orderId = node.lang;
			window.location.href = "${pageContext.request.contextPath}/order?method=pay";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
						<td align="center" width="16.6%">name</td>
						<td align="center" width="16.6%">price</td>
						<td align="center" width="16.6%">vip</td>
						<td align="center" width="16.6%">number</td>
						<td align="center" width="16.6%">count</td>

					</tr>
					<%	double sum=0;
						double vsum=0; %>
					<c:if test="${not empty requestScope.orderDetails}">
						<%
							Map<Integer, Food> cartFood = (Map<Integer, Food>) request.getSession().getAttribute("cartFood");
							List<OrderDetail> details =(List<OrderDetail>) request.getAttribute("orderDetails");
							for (OrderDetail detail : details) {

								int foodId = detail.getFoodId();
						%>

						<tr height="60">
							<td align="center" width="16.6%"><%=cartFood.get(foodId).getFoodName()%>
							</td>
							<td align="center" width="16.6">$<%=cartFood.get(foodId).getPrice()%>
							</td>
							<td align="center" width="16.6"><font color="#8a2be2">$<%=cartFood.get(foodId).getMprice()%></font>
							</td>
							<td align="center" width="16.6%">
								<%=detail.getFoodCount()%>
							</td>
							<td align="center" width="16.6%">
								<% double money = cartFood.get(foodId).getPrice() * detail.getFoodCount();
									sum += money;
									double vmoney = cartFood.get(foodId).getMprice() * detail.getFoodCount();
									vsum +=vmoney;
								%>
								$<%=money%><font color="#8a2be2">(<%=vmoney%>)</font>
							</td>

						</tr>
						<%}
							%>

					</c:if>



					<tr>
						<td colspan="6" align="right">in sum:
							<span style="font-size:36px;">$</span>
							<label
								id="counter" style="font-size:36px"><%=sum%><font color="#8a2be2">(<%=vsum%>)</font></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="pay now" class="btn_next" lang="" onclick="callPay(this)" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
					<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath}/order?method=addToCart">
						<img src="${pageContext.request.contextPath}/app/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					<c:forEach items="${applicationScope.types}" var="type">
						<li>
							<a href="${pageContext.request.contextPath}/food?method=menu&foodTypeId=${type.id}">${type.typeName}</a>
						</li>

					</c:forEach>


				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="/wirelessplatform/food.html" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<input type="hidden" value="selectFood" name="method">
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/food?method=menu">
									<img src="${pageContext.request.contextPath}/app/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>
