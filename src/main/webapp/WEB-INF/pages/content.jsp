<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 18.08.2018
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/catalog" prefix="catalog" %>


<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<div class="head">
    <h1><fmt:message key="periodicals"/></h1>
</div>
<catalog:getCatalog/>
<div class="container">
    <ul>
        <c:forEach var="item" items="${periodicals}">
            <li>
                <a href="Controller?command=periodicalInfo&id=${item.getId()}">
                    <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
                    <div class="mainHeader">${item.getTitle()}</div>
                    <img src="../../css/images/book.jpg" alt="image">
                    <p>Category: ${item.getCategory()}</p>
                    <p>Price: ${item.getPrice()}</p>
                    <p>Period: ${item.getPeriodicalPeriod().getPeriod()}</p>
                    <p style="color: chocolate; font-style: italic"> Details</p></a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>