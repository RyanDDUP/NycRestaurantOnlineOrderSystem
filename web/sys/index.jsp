<%--
Created by IntelliJ IDEA.
User: RZ
Date: 4/19/16
Time: 4:55 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>online menu order system</title>
</head>
	<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
		<frame src="${pageContext.request.contextPath}/sys/public/top.jsp" scrolling="no" noresize />
		<frameset cols="178px,*">
			<frame noresize src="${pageContext.request.contextPath}/sys/public/left.jsp" scrolling="yes" />
			<frame noresize name="right" src="${pageContext.request.contextPath}/sys/public/right.jsp" scrolling="yes" />
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/sys/public/bottom.jsp" />
	</frameset>
	<noframes>
		<body>
			your browser did not support our page, try<a href="http://www.firefox.com.cn/download/" style="text-decoration: none;">FireFox</a>,
			<a href="http://www.google.cn/intl/zh-CN/chrome/" style="text-decoration: none;">Chrome</a>
		</body>
	</noframes>
</html>