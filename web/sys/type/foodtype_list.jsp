<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->
    <title>online menu order system</title>



    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
    <link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css"/>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> food type list
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>
<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="${pageContext.request.contextPath}/foodtype" method="get">
        <input type="hidden" name="method" value="search">
        <input type="text" name="keyword" title="please type in food type name">
        <input type="submit" value="search">
    </form>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>type No.</td>
            <td>type name</td>
            <td>manage</td>
        </tr>


        <script type="text/javascript">
            function delCheck(iid) {
                <c:forEach items="${applicationScope.types}" var="it">
                if("${it.id}"==iid) {
                    alert("food type is in use!!");
                    return;
                }
                    </c:forEach>

                if(delConfirm())
                    document.location.href="${pageContext.request.contextPath}/foodtype?method=delete&id="+iid;
            }
        </script>




        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:choose>
        <c:when test="${not empty requestScope.list}">
        <c:forEach items="${requestScope.list}" var="i">
        <tr align="center">
            <td>${i.id}</td>
            <td>${i.typeName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/foodtype?method=viewUpdate&id=${i.id}" class="FunctionButton">update</a>
                <a href="javascript:void(0)" class="FunctionButton" onclick="delCheck(${i.id})">delete</a>
            </td>

            </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="3">sorry, you don't have any type</td>
                </tr>
            </c:otherwise>
            </c:choose>

        </tr>

        </tbody>
    </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
        <div class="FunctionButton">
            <a href="${pageContext.request.contextPath}/sys/type/foodtype_add.jsp">new food type</a>
        </div>
    </div>
</div>
</body>
</html>
