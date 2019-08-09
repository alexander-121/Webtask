<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>University groups</title>
</head>
<body>
<h1>Group list</h1>
<form:form method="post" action="find">
    <table >
        <tr>
            <td>ID : </td>
            <td><form:input path="id"/></td>
            <td>Name : </td>
            <td><form:input path="name"/></td>
            <td>Faculty :</td>
            <td><form:select path="faculty">
                <form:option value="<Empty>"/> Empty
                <form:option value="Software Engineering"/> Software Engineering
                <form:option value="Rocket Science"/> Rocket Science
                <form:option value="Electrical Engineering"/> Electrical Engineering
                <form:option value="Mineralogy"/> Mineralogy
                <form:option value="Engine Design"/> Engine Design
            </form:select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Find"/>
</form:form>
<button onclick="window.location.href = '/groups';"> Find All</button>
<br>
<table border="2" width="60%" cellpadding="2">
    <tr>
        <th>ID</th>
        <th>Group number</th>
        <th>Faculty</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${grp}" var="grp">
        <tr>
            <td>${grp.id}</td>
            <td>${grp.name}</td>
            <td>${grp.faculty}</td>
            <td><a href="editgrp/${grp.id}">Edit</a> </td>
            <td><a href="deletegrp/${grp.id}">Delete</a> </td>
        </tr>
    </c:forEach>
</table>
<div>
    <a href="addgroup">Add group</a>
</div>
<h3>To other pages</h3>
<div>
    <a href="/">Main page</a>
</div>
<div>
    <a href="/students">View students</a>
</div>
</body>
</html>