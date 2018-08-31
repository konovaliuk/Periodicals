<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 11.08.2018
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/user" prefix="userInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css"/>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<div class="header">
    <div class="navbar">
        <a href="/"><fmt:message key="home"/> </a>
        <c:if test="${empty user}">
            <a href="login"><fmt:message key="sign_in"/> </a>
            <a href="register"><fmt:message key="registration"/> </a>
        </c:if>
        <c:if test="${not empty user}">
            <c:choose>
                <c:when test="${role=='admin'}">
                    <a href="createPeriodical">Create periodical</a>
                </c:when>
                <c:otherwise>
                    <a href="Controller?command=getUserPeriodicals">My periodicals</a>
                </c:otherwise>
            </c:choose>
            <a href="Controller?command=logout"><fmt:message key="sign_out"/> </a>
        </c:if>
        <div class="locale">
            <a href="Controller?command=locale&locale=en"><img src="../../css/images/en.png"></a>
            <a href="Controller?command=locale&locale=ua"><img src="../../css/images/ua.png"></a>
            <a href="Controller?command=locale&locale=ru"><img src="../../css/images/ru.png"></a>
        </div>
    </div>
</div>
<div class="head">
    <h1><fmt:message key="periodicals"/></h1>
</div>
</body>
</html>
