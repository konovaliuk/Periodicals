<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 27.08.2018
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create periodical</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form name="createPeriodicalForm" action="createPeriodical" method="post">
    <input type="hidden" name="command" value="createPeriodical">
    <div class="formContainer">
        <label><b>Title</b></label>
        <input type="text" placeholder="Enter title" name="title" required>

        <label><b>Type</b></label>
        <%-- <input type="text" placeholder="Enter type" name="type" required>--%>
        <select name="type">
            <option disabled>Select type</option>
            <option value="newspaper">newspaper</option>
            <option value="journal">journal</option>
        </select>

        <label><b>Period</b></label>
        <select name="period">
            <option disabled>Select period</option>
            <option value="once a week">once a week</option>
            <option value="once a month">once a month</option>
            <option value="once a 3 month">once a 3 month</option>
            <option value="once a year">once a year</option>
        </select>

        <label><b>Category</b></label>
        <input type="text" placeholder="Enter category" name="category" required>

        <label><b>Price</b></label>
        <input type="number" placeholder="Enter price" name="price" required min="1">

        <label><b>Description</b></label>
        <input type="text" placeholder="Enter description" name="description">

        <div class="submitButton">
            <button type="submit">Create</button>
        </div>
        <br/>
        <label style="color: brown">
            ${info}
        </label>
    </div>
</form>
</body>
</html>
