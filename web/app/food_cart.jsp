<%@ page import="java.util.Map" %>
<%@ page import="domain.Food" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>online menu Chinese Restaurant</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/style/css/index.css"/>
    <script type="text/javascript">
        /** // 删除菜品项
         function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.jsp?method=removeSorder&gid="+gid;
		}

         // 修改菜品项数量
         function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.jsp?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
         */
        // 下单
        function genernateOrder() {
            if(${sessionScope.cart.size==0}) {
                window.location.href="javascript:void(0)";
                return;
            }
            if(window.confirm("ready to put?"))
            document.location.href = "${pageContext.request.contextPath}/order?method=putOrder";
        }
        //改数量
        function alterSorder(node) {
            var num = node.value;
            var foodId = node.lang;
            document.location.href="${pageContext.request.contextPath}/order?method=alterNum&foodId="+foodId+"&num="+num;

        }
        //删除订单

        function removeSorder(node) {
            var foodId = node.lang;

            document.location.href="${pageContext.request.contextPath}/order?method=delById&foodId="+foodId;
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
                    <td align="center" width="16.6%">manage</td>
                </tr>
                <%double sum = 0;
                    double vsum = 0;
                %>
                <c:choose>
                    <c:when test="${not empty sessionScope.cart}">
                        <%
                            Map<Integer, Integer> cart = (Map<Integer, Integer>) request.getSession().getAttribute("cart");
                            Map<Integer, Food> cartFood = (Map<Integer, Food>) request.getSession().getAttribute("cartFood");
                            for (int key : cart.keySet()) { %>

                        <tr height="60">
                            <td align="center" width="16.6%"><%=cartFood.get(key).getFoodName()%>
                            </td>
                            <td align="center" width="16.6">$<%=cartFood.get(key).getPrice()%>
                            </td>
                            <td align="center" width="16.6"><font color="#8a2be2">$<%=cartFood.get(key).getMprice()%></font>
                            </td>
                            <td align="center" width="16.6%">
                                <input type="text" value="<%=cart.get(key)%>" size="3" lang="<%=key%>" onblur="alterSorder(this)"/>
                            </td>
                            <td align="center" width="16.6%">
                                <% double money = cartFood.get(key).getPrice() * cart.get(key);
                                    sum += money;
                                double vmoney = cartFood.get(key).getMprice() * cart.get(key);
                                    vsum +=vmoney;
                                %>
                                $<%=money%><font color="#8a2be2">(<%=vmoney%>)</font>
                            </td>
                            <td align="center" width="16.6%">
                                <input type="button" value="delete" class="btn_next" lang="<%=key%>"
                                       onclick="removeSorder(this)"/>
                            </td>
                        </tr>
                        <%
                            }
                        %>

                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>

                <tr>
                    <td colspan="6" align="right">total:
                        <span style="font-size:36px;">$<%=sum%><font color="#8a2be2">(<%=vsum%>)</font> </span>
                        <label
                                id="counter" style="font-size:36px"></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="margin-left: 100px; text-align: center;" align="right">
                        <input type="hidden" name="bId" value="">


                        <input type="button" value="ORDER" class="btn_next" onclick="genernateOrder()"/>


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
                    <a href="${pageContext.request.contextPath}/app/food_cart.jsp">
                        <img src="${pageContext.request.contextPath}/app/style/images/call2.gif"/>
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
            <form action="${pageContext.request.contextPath}/food?method=menu" method="post">
                <table width="166px">
                    <tr>
                        <td>
                            <input type="text" id="dish_name" name="foodName" class="select_value"/>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="sub" value=""/></td>
                    </tr>
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/food?method=menu">
                                <img src="${pageContext.request.contextPath}/app/style/images/look.gif"/>
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
