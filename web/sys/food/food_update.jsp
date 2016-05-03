<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                 src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> update food info


        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="${pageContext.request.contextPath}/food?method=update" method="post" enctype="multipart/form-data">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0"
                 src="${pageContext.request.contextPath}/sys/style/images/item_point.gif"> food info&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <c:choose>
                            <c:when test="${not empty requestScope.types}">
                                <tr>
                                    <td width="80px">food type</td>
                                    <td>
                                        <select name="foodType_id" style="width:80px">
                                            <c:forEach items="${requestScope.types}" var="type">
                                                <option value="${type.id}"
                                                        <c:if test="${type.id==food.foodType_id}">
                                                            selected="selected"
                                                        </c:if>
                                                >${type.typeName}</option>
                                            </c:forEach>


                                        </select>
                                        <input type="hidden" name="id" value="${food.id}"/></td>
                                </tr>
                                <tr>
                                    <td width="80px">name</td>
                                    <td><input type="text" name="foodName" class="InputStyle" value="${food.foodName}"/>
                                        *
                                    </td>
                                </tr>
                                <tr>
                                    <td width="80px">中文菜名</td>
                                    <td><input type="text" name="foodNameCN" class="InputStyle"
                                               value="${food.foodNameCN}"/> *
                                    </td>
                                </tr>
                                <tr>
                                    <td>price</td>
                                    <td><input type="text" name="price" class="InputStyle" value="${food.price}"/> *
                                    </td>
                                </tr>
                                <tr>
                                    <td>vip price</td>
                                    <td><input type="text" name="mprice" class="InputStyle" value="${food.mprice}"/> *
                                    </td>
                                </tr>

                                <tr>
                                    <td>description</td>
                                    <td><textarea name="description"
                                                  class="TextareaStyle">${food.description}！</textarea></td>
                                </tr>
                                <tr>
                                    <td width="80px">image</td>
                                    <td>
                                        <img style="max-width:68px;width:68px;width:expression(width>68?'68px':width 'px');max-width: 68px;"

                                             src="${pageContext.request.contextPath}${food.img}">
                                        <!-- <input type="hidden" name="image" value="baizhuoxia.jpg">-->

                                        <input type="file" name="img"/> *
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
            </div>
        </div>


        <!-- 表单操作 -->
        <div id="InputDetailBar">


            <input type="submit" value="update" class="FunctionButtonInput">


            <a href="javascript:history.go(-1);" class="FunctionButton">back</a>
        </div>
    </form>
</div>
</body>
</html>
