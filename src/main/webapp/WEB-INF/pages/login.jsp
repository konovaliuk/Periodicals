<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 15.08.2018
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Sign in</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<form name="loginForm" action="login" method="post">
    <input type="hidden" name="command" value="login">
    <div class="formContainer">
        <label><b><fmt:message key="login"/> </b></label>
        <input type="text" placeholder="Enter Username" name="login" required>

        <label><b><fmt:message key="password"/></b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <div class="submitButton">
            <button type="submit"><fmt:message key="login"/></button>
        </div>

        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
        <br/>
        <label style="color: brown">
        ${error}
        </label>
    </div>
</form>
</body>

</html>
