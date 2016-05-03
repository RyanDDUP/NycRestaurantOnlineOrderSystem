<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	<title>online menu order system</title>




<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
	<script type="text/javascript">
		setInterval(function(){
			window.location.href = "/wirelessplatform/client.html?method=list";
		},1000 * 50);
	</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif" /> order list
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>order NO.</td>
					<td>table Name</td>
					<td>order Time</td>
					<td>price</td>
					<td>vip</td>
					<td>status</td>
					<td>manage</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">

			<c:if test="${not empty requestScope.pb.pageContent}">
				<c:forEach items="${requestScope.pb.pageContent}" var="order">
				<tr height="60">
					<td>${order.id}</td>
					<c:forEach items="${requestScope.tables}" var="table">
						<c:if test="${table.id eq  order.table_id}">
							<td>${table.tableName}</td>
						</c:if>
					</c:forEach>
					<td>${order.orderDate}</td>
					<td>$${order.totalPrice}</td>
					<td>$${order.totalVipPrice}</td>
					<td>
					<c:choose>
						<c:when test="${order.orderStatus eq 0}">
							unpaid
						</c:when>
						<c:when test="${order.orderStatus eq 1}">
							called, waiting to pay
						</c:when>
						<c:otherwise>
							paid
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
					<script type="text/javascript">
						function readyPay(id) {
							if(window.confirm("check now?")){
								document.location.href="${pageContext.request.contextPath}/order?method=check&orderId="+id;
							}
						}
					</script>

					<td>
						<a href="${pageContext.request.contextPath}/order?method=orderDetails&orderId=${order.id}" class="FunctionButton">details</a>

						<a href="javascript:void(0);" class="FunctionButton" onclick="readyPay(${order.id})">check</a>

					</td>
				</tr>
				</c:forEach>
			</c:if>
			 	
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
			<div class="FunctionButton">
				<c:if test="${empty requestScope.pb.currPageNum or requestScope.pb.currPageNum==1}">
					<font color="#dcdcdc">first page</font>
				</c:if>
				<c:if test="${requestScope.pb.currPageNum>1}">
					<a href="${pageContext.request.contextPath}/food?method=list&currPageNum=1">first page</a>
				</c:if>


			</div>

			<div class="FunctionButton">
				<c:if test="${empty requestScope.pb.currPageNum or requestScope.pb.currPageNum==1}">
					<font color="#dcdcdc">previews page</font>
				</c:if>
				<c:if test="${requestScope.pb.currPageNum>1}">
					<a href="${pageContext.request.contextPath}/food?method=list&currPageNum=${requestScope.pb.currPageNum-1}">previews
						page</a>
				</c:if>
			</div>

			<c:set var="t" value="${requestScope.pb.totalPage}"></c:set>
			<div class="FunctionButton">
				<c:if test="${requestScope.pb.currPageNum==t}">
					<font color="#dcdcdc">next page</font>
				</c:if>

				<c:if test="${requestScope.pb.currPageNum!=t}">
					<a href="${pageContext.request.contextPath}/food?method=list&currPageNum=${requestScope.pb.currPageNum+1}">next
						page</a>
				</c:if>
			</div>



			<div class="FunctionButton">
				<c:if test="${requestScope.pb.currPageNum!=t}">
					<a href="${pageContext.request.contextPath}/food?method=list&currPageNum=${t}">
						last page
					</a>
				</c:if>
				<c:if test="${requestScope.pb.currPageNum==t}">
					<font color="#dcdcdc">last page</font>
				</c:if>
			</div>

		</div>
	</div>
</body>
</html>
