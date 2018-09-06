<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 31.08.2018
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Update periodical</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<form name="updatePeriodicalPageForm" action="updatePeriodical" method="post">
    <input type="hidden" name="command" value="updatePeriodical">
    <div class="formContainer">
        <label><b><fmt:message key="title"/></b></label>
        <input type="text" name="title" value="${periodical.getTitle()}" required>
        <label><b><fmt:message key="type"/></b></label>
        <select name="type">
            <option selected>${periodical.getPeriodicalType().getType()}</option>
            <option value="newspaper"><fmt:message key="newspaper"/></option>
            <option value="journal"><fmt:message key="journal"/></option>
        </select>

        <label><b><fmt:message key="period"/></b></label>
        <select name="term">
            <option selected>${periodical.getPeriodicalPeriod().getTerm()}</option>
            <option value="1"><fmt:message key="once_a_month"/></option>
            <option value="3"><fmt:message key="3_month"/></option>
            <option value="6"><fmt:message key="6_month"/></option>
            <option value="12"><fmt:message key="year"/></option>
        </select>

        <label><b><fmt:message key="category"/></b></label>
        <input type="text" name="category" value="${periodical.getCategory()}" required>

        <label><b><fmt:message key="price"/></b></label>
        <input type="number" name="price" value="${periodical.getPrice()}" required min="1">

        <label><b><fmt:message key="description"/></b></label>
        <input type="text" name="description" value="${periodical.getDescription()}">

        <div class="submitButton">
            <button type="submit"><fmt:message key="update"/></button>
        </div>
        <br/>
        <label style="color: brown">
            ${info}
        </label>
    </div>
</form>
</body>
</html>
