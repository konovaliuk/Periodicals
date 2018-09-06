<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 21.08.2018
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Periodical</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <c:set var="item" value="${periodical}"/>
    <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
    <div class="mainHeader">${item.getTitle()}</div>
    <img src="../../images/book.jpg" alt="image">
    <p><fmt:message key="category"/>: ${item.getCategory()}</p>
    <p><fmt:message key="price"/>: ${item.getPrice()}</p>
    <p><fmt:message key="period"/>: ${item.getPeriodicalPeriod().getPeriod()}</p>
    <p>
        <span class="red"><fmt:message key="description"/>:</span> ${item.getDescription()}
    </p>
    <c:choose>
        <c:when test="${role=='admin'}">
            <form name="updateForm" action="updatePeriodical" method="post">
                <input type="hidden" name="command" value="getPage">
                <div class="submitButton">
                    <button type="submit"><fmt:message key="update_periodical"/></button>
                </div>
            </form>
            <form name="deletePeriodical" action="deletePeriodical" method="post">
                <input type="hidden" name="command" value="deletePeriodical">
                <div class="submitButton">
                    <input type="hidden" name="periodicalId" value="${item.getId()}">
                    <button type="submit"><fmt:message key="delete_periodical"/></button>
                </div>
            </form>
        </c:when>
        <c:when test="${isSubscribe=='true'}">
            <a href="../../images/book.jpg"><p style="color: chocolate; font-style: italic"> <fmt:message key="read"/></a>
        </c:when>
        <c:otherwise>
            <form name="subscribeForm" action="subscribe" method="post">
                <c:set var="periodicalTerm" value="${item.getPeriodicalPeriod().getTerm()}"/>
                <select name="term">
                    <option value="0" selected="selected"><fmt:message key="select_period"/></option>
                    <c:choose>
                        <c:when test="${periodicalTerm==1}">
                            <option value="3"><fmt:message key="3_month"/></option>
                            <option value="6"><fmt:message key="6_month"/></option>
                            <option value="12"><fmt:message key="year"/></option>
                        </c:when>
                        <c:when test="${periodicalTerm==3}">
                            <option value="6">6 month</option>
                            <option value="12">year</option>
                        </c:when>
                        <c:when test="${periodicalTerm==6}">
                            <option value="12">year</option>
                        </c:when>
                    </c:choose>
                </select>
                <input type="hidden" name="command" value="subscribe" required>
                <div class="submitButton">
                    <input type="hidden" name="periodicalId" value="${item.getId()}">
                    <button type="submit"><fmt:message key="subscribe"/></button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
    <label style="color: brown">
        ${info}
    </label>
</div>
</body>
</html>
