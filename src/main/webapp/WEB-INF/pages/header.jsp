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
<div class="navbar">
    <a href="/"><fmt:message key="home"/> </a>
    <c:choose>
        <c:when test="${empty user}">
            <a href="login"><fmt:message key="sign_in"/></a>
            <a href="register"><fmt:message key="sign_up"/></a>
        </c:when>
        <c:otherwise>
            <c:set var="role" scope="session" value="${user.getUserRole().getRole()}"/>
            <c:choose>
                <c:when test="${role=='admin'}">
                    <form name="createPeriodicalPageForm" action="createPeriodical" method="post">
                        <input type="hidden" name="command" value="getPage">
                        <input type="submit" value="<fmt:message key="create_periodical"/>">
                    </form>
                    <form name="subscriptionsInfoForm" action="subscriptionsInfo" method="post">
                        <input type="hidden" name="command" value="subscriptionsInfo">
                        <input type="submit" value="<fmt:message key="info"/>">
                    </form>
                </c:when>
                <c:otherwise>
                    <form name="userPeriodicalsForm" action="userPeriodicals" method="post">
                        <input type="hidden" name="command" value="getUserPeriodicals">
                        <input type="submit" value="<fmt:message key="my_periodicals"/>">
                    </form>
                </c:otherwise>
            </c:choose>
            <form name="logoutForm" action="logout" method="post">
                <input type="hidden" name="command" value="logout">
                <input type="submit" value="<fmt:message key="sign_out"/>">
            </form>
        </c:otherwise>
    </c:choose>
    <div class="locale">
        <a href="Controller?command=locale&locale=en"><img src="../../images/en.png"></a>
        <a href="Controller?command=locale&locale=ua"><img src="../../images/ua.png"></a>
        <a href="Controller?command=locale&locale=ru"><img src="../../images/ru.png"></a>
    </div>
</div>
<userInfo:getInfo/>
<br>
<jsp:useBean id="now" class="java.util.Date" />
    <fmt:setLocale value="${locale}" />
    <fmt:formatDate value="${now}" />
<div class="head">
    <h1><fmt:message key="periodicals"/></h1>
</div>
</body>
</html>
