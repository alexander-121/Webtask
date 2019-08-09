<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add student</title>
</head>
<body>
<h1>Add New students</h1>
<form:form method="post" action="savestd">
    <table >
        <tr>
            <td>First name : </td>
            <td><form:input path="firstname"/></td>
        </tr>
        <tr>
            <td>Fathers name : </td>
            <td><form:input path="fathersname"/></td>
        </tr>
        <tr>
            <td>Last name : </td>
            <td><form:input path="lastname"/></td>
        </tr>
        <tr>
            <td>Group :</td>
            <td><form:select path="grp.name">
                <form:option value="<Empty>"/>
                <c:forEach var="grp" items="${grp}">
                    <option>${grp.name}</option>
                </c:forEach>
            </form:select>
            </td>
        </tr>
        <tr>
            <td>Study type : </td>
            <td><form:select path="studytype">
            <form:option value="Full-time"/> Full-time
            <form:option value="Part-time"/> Part-time
            </form:select>
            </td>
        </tr>
        <tr>
            <td> Enrollment data : </td>
            <td><form:input path="date" type="date" min="1999-09-01" max="2109-01-01" value="2019-09-01"/></td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
<a href="/students">Back</a>
</body>
</html>