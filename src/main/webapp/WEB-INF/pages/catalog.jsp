<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 30.08.2018
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Catalog</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div class="container">
    <ul>
        <c:forEach var="item" items="${periodicals}">
            <li>
                <a href="Controller?command=periodicalInfo&id=${item.getId()}">
                    <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
                    <div class="mainHeader">${item.getTitle()}</div>
                    <img src="../../images/book.jpg" alt="image">
                    <p>Category: ${item.getCategory()}</p>
                    <p>Price: ${item.getPrice()}</p>
                    <p>Period: ${item.getPeriodicalPeriod().getPeriod()}</p>
                    <p style="color: chocolate; font-style: italic"> Details</p></a>
            </li>
        </c:forEach>
    </ul>
</div>

<div class="pagination">
    <c:if test="${currentPage != 1}">
        <a href="Controller?command=getCatalog&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">&laquo;</a>
    </c:if>

    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <a class="active">${i}</a>
            </c:when>
            <c:otherwise>
                <a href="Controller?command=getCatalog&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt numberOfPages}">
        <a href="Controller?command=getCatalog&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">&raquo;</a>
    </c:if>
</div>
</body>
</html>
