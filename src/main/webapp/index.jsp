<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Main</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<c:set var="role" scope="session" value="${user.getUserRole().getRole()}"/>

<c:choose>
    <c:when test="${role=='admin'}">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
        <jsp:include page="/WEB-INF/pages/pagination.jsp"/>

        <%-- <form name="catalogForm" action="getCatalog" method="post">
             <input type="hidden" name="command" value="getCatalog"/>
             <input type="hidden" name="currentPage" value="1"/>
             <input type="hidden" name="recordsPerPage" value="4"/>
             <div class="submitButton">
                 <button type="submit">Show catalog</button>
             </div>

         </form>--%>
    </c:when>
    <c:when test="${role=='reader'}">
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
        <jsp:include page="/WEB-INF/pages/pagination.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
        <%--   <div class="head">
               <h1><fmt:message key="periodicals"/></h1>
           </div>
           <form name="catalogForm" action="getCatalog" method="post">
               <input type="hidden" name="command" value="getCatalog"/>
               <input type="hidden" name="currentPage" value="1"/>
               <input type="hidden" name="recordsPerPage" value="4"/>
               <div class="submitButton">
                   <button type="submit">Show catalog</button>
               </div>

           </form>--%>
        <jsp:include page="/WEB-INF/pages/pagination.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
