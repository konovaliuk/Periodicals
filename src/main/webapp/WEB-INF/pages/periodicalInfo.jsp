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

<%--<c:set var="role" scope="session" value="${role}"/>--%>
<jsp:include page="header.jsp"/>

<div class="container">
    <c:set var="item" scope="session" value="${periodical}"/>
    <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
    <div class="mainHeader">${item.getTitle()}</div>
    <img src="../../css/images/book.jpg" alt="image">
    <p>Category: ${item.getCategory()}</p>
    <p>Price: ${item.getPrice()}</p>
    <p>Period: ${item.getPeriodicalPeriod().getPeriod()}</p>
    <p>
        <span class="red">Description:</span> ${item.getDescription()}
    </p>
    <c:choose>
        <c:when test="${role=='admin'}">
            <form name="updateForm" action="updatePeriodical" method="post">
                <input type="hidden" name="command" value="updatePeriodical" required>
                <div class="submitButton">
                    <button type="submit">Update periodical</button>
                </div>

            </form>
        </c:when>
        <c:when test="${isSubscribe=='true'}">
            <a href="../../css/images/book.jpg"><p style="color: chocolate; font-style: italic"> Read</p></a>
        </c:when>
        <c:otherwise>
            <form name="subscribeForm" action="subscribe" method="post">
                <c:set var="periodicalTerm" value="${item.getPeriodicalPeriod().getTerm()}"/>
                <select name="term">
                    <option value="0" selected="selected">Select period</option>
                    <c:choose>
                        <c:when test="${periodicalTerm==1}">
                            <option value="3">3 month</option>
                            <option value="6">6 month</option>
                            <option value="12">year</option>
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
                    <button type="submit">Subscribe</button>
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
