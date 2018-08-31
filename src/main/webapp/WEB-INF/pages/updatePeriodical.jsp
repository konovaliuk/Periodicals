<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 31.08.2018
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update periodical</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form name="updatePeriodicalForm" action="updatePeriodical" method="post">
    <input type="hidden" name="command" value="updatePeriodical">
    <div class="formContainer">
        <label><b>Title</b></label>
        <input type="text" name="title" value="${periodical.getTitle()}" required>
        <label><b>Type</b></label>
        <select name="type">
            <%--  <option disabled>Select type</option>--%>
            <option selected>${periodical.getPeriodicalType().getType()}</option>
            <option value="newspaper">newspaper</option>
            <option value="journal">journal</option>
        </select>

        <label><b>Period</b></label>
        <select name="period">
            <%--<option disabled>Select period</option>--%>
            <option selected>${periodical.getPeriodicalPeriod().getPeriod()}</option>
            <option value="once a week">once a week</option>
            <option value="once a month">once a month</option>
            <option value="once a 3 month">once a 3 month</option>
            <option value="once a year">once a year</option>
        </select>

        <label><b>Category</b></label>
        <input type="text" name="category" value="${periodical.getCategory()}" required>

        <label><b>Price</b></label>
        <input type="number" name="price" value="${periodical.getPrice()}" required min="1">

        <label><b>Description</b></label>
        <input type="text" name="description" value="${periodical.getDescription()}">

        <div class="submitButton">
            <button type="submit">Update</button>
        </div>
        <br/>
        <label style="color: brown">
            ${info}
        </label>
    </div>
</form>
</body>
</html>
