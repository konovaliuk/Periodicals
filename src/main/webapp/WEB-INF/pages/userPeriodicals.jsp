<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 22.08.2018
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Periodicals</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="container">
    <ul>
        <c:forEach var="item" items="${userPeriodicals}">
            <li><a href="../../css/images/book.jpg">
                <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
                <div class="mainHeader">${item.getTitle()}</div>
                <img src="../../css/images/book.jpg" alt="image">
                <p style="color: chocolate; font-style: italic"> Read</p></a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
