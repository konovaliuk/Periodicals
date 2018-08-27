<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Main</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<c:set var="role" scope="session" value="${user.getUserRole().getRole()}"/>

<c:choose>
<c:when test="${role=='admin'}">
    <jsp:include page="WEB-INF/pages/header.jsp"/>

        <jsp:include page="WEB-INF/pages/content.jsp"/>
    </c:when>
    <c:when test="${role=='reader'}">
        <jsp:include page="WEB-INF/pages/header.jsp"/>
        <jsp:include page="WEB-INF/pages/content.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="WEB-INF/pages/header.jsp"/>
        <jsp:include page="WEB-INF/pages/content.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
