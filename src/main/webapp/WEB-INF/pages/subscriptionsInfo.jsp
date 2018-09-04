<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 31.08.2018
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Info</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<jsp:include page="/WEB-INF/pages/header.jsp"/>

<body>
<table>
    <tr>
        <th><fmt:message key="periodical"/></th>
        <th><fmt:message key="user"/></th>
        <th><fmt:message key="expiration_date"/></th>
    </tr>
    <c:forEach var="item" items="${subscriptions}">
    <tr>
        <td>${item.getPeriodical().getTitle()}</td>
        <td>${item.getUser().getLogin()}</td>
        <td>${item.getExpirationDate()}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
