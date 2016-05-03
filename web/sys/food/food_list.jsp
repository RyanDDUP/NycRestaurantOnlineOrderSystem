<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>无线点餐平台</title>


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
                 src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> food list
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="${pageContext.request.contextPath}/food" method="get">
        <input type="hidden" name="method" value="search">
        <input type="text" name="keyword" title="please type in dish's name">
        <input type="submit" value="search">
    </form>
</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>food No.</td>
            <td>name</td>
            <td>中文名</td>
            <td>type</td>
            <td>price</td>
            <td>vip price</td>
            <td>manage</td>
        </tr>
        </thead>
        <!--显示数据列表 -->

        <tbody id="TableData">
        <c:choose>
            <c:when test="${not empty requestScope.list}">
                <c:forEach items="${requestScope.list}" var="food">
                    <tr class="TableDetail1">
                        <td>${food.id}&nbsp;</td>
                        <td>${food.foodName}&nbsp;</td>
                        <td>${food.foodNameCN}&nbsp;</td>
                        <td>
                            <c:forEach items="${applicationScope.types}" var="type">
                                <c:if test="${type.id==food.foodType_id}">
                                    ${type.typeName}
                                </c:if>
                            </c:forEach>

                            &nbsp;</td>
                        <td>${food.price}&nbsp;</td>
                        <td>${food.mprice}&nbsp;</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/food?method=viewUpdate&id=${food.id}"
                               class="FunctionButton">update</a>
                            <a href=" ${pageContext.request.contextPath}/food?method=delete&id=${food.id}"
                               onClick=" return delConfirm()"
                               class="FunctionButton" id="delId">
                                delete</a>


                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr class="TableDetail1">
                    <td colspan="5">please add new food first</td>
                </tr>
            </c:otherwise>
        </c:choose>

        </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
        <div class="FunctionButton"><a href="${pageContext.request.contextPath}/food?method=addQuery">addNew</a></div>


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
