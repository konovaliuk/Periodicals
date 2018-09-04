<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 18.08.2018
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/catalog" prefix="catalog" %>


<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>
<form name="catalogForm" action="catalog" method="post">
    <input type="hidden" name="command" value="getCatalog"/>
    <input type="hidden" name="currentPage" value="1"/>
    <input type="hidden" name="recordsPerPage" value="4"/>
    <div class="submitButton">
        <button type="submit"><fmt:message key="show_catalog"/> </button>
    </div>
</form>
</body>
</html>