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
<c:set var="role" scope="session" value="${role}"/>
<jsp:include page="header.jsp"/>
<div class="container">
    <ul>
        <li>
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
                <c:otherwise>
                    <form name="subscribeForm" action="subscribe" method="post">
                        <select name="period">
                            <option disabled>Select period</option>
                            <option value="month">month</option>
                            <option value="threeMonth">threeMonth</option>
                            <option value="sixMonth">sixMonth</option>
                            <option value="year">year</option>
                        </select>
                        <input type="hidden" name="command" value="subscribe" required>
                        <div class="submitButton">
                            <button type="submit">Subscribe</button>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</div>
</body>
</html>
