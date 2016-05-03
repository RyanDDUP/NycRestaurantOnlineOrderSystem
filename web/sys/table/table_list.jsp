<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->

	<title>online menu order system</title>




<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> table list
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="${pageContext.request.contextPath}/table?method=search" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="please type in table name">
		<input type="submit" value="search">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>No</td>
				<td>table name</td>
				<td>status</td>
				<td>reserve time</td>
				<td>operate</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
		<c:choose>
			<c:when test="${not empty requestScope.list}">

        <tbody id="TableData">
			<c:forEach items="${requestScope.list}" var="table">
			<tr class="TableDetail1">
				<td align="center">${table.id}&nbsp;</td>
				<td align="center"> ${table.tableName}&nbsp;</td>
				<c:if test="${table.tableStatus==1}">
					<td align="center">reserved</td>
					<td align="center">${table.orderDate}&nbsp;</td>
				<td>
					<a href="${pageContext.request.contextPath}/table?method=reserve&id=${table.id}" class="FunctionButton">cancel</a>
				</c:if>
				<c:if test="${table.tableStatus==0}">
					<td align="center">available</td>
					<td align="center">${table.orderDate}&nbsp;</td>
				<td>
					<a href="${pageContext.request.contextPath}/table?method=reserve&id=${table.id}" class="FunctionButton">reserve</a>
				</c:if>
					<a href="${pageContext.request.contextPath}/table?method=delete&id=${table.id}" onClick="return delConfirm();"class="FunctionButton">delete</a>
				</td>
			</tr>
			</c:forEach>
		</c:when>
			<c:otherwise>
			<tr class="TableDetail1">
				<td align="center" colspan="4">please add new table first</td>
			</tr>
			</c:otherwise>
			</c:choose>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/sys/table/table_add.jsp">addNew</a></div>
    </div> 
</div>
</body>
</html>
