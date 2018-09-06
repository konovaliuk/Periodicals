<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 22.08.2018
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
            <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
            <div class="mainHeader">${item.getTitle()}</div>
            <img src="../../images/book.jpg" alt="image">
            <p><fmt:message key="category"/>: ${item.getCategory()}</p>
            <p><fmt:message key="price"/>: ${item.getPrice()}</p>
            <p><fmt:message key="period"/>: ${item.getPeriodicalPeriod().getPeriod()}</p>
            <p>
                <span class="red"><fmt:message key="description"/>:</span> ${item.getDescription()}
            </p>
            <a href="../../images/book.jpg"><p style="color: chocolate; font-style: italic"> <fmt:message key="read"/></p></a>
        </c:forEach>
    </ul>
</div>
</body>
</html>
