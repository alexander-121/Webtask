<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit group</title>
</head>
<body>
<h1>Edit Employee</h1>
<form:form method="POST" action="/editsave">
    <table >
        <tr>
            <td></td>
            <td><form:hidden  path="id" /></td>
        </tr>
        <tr>
            <td>Name : </td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td>Faculty :</td>
            <td><form:select path="faculty">
                <form:option value="<Empty>"/>
                <form:option value="Software Engineering"/> Software Engineering
                <form:option value="Rocket Science"/> Rocket Science
                <form:option value="Electrical Engineering"/> Electrical Engineering
                <form:option value="Mineralogy"/> Mineralogy
                <form:option value="Engine Design"/> Engine Design
            </form:select>
            </td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Edit Save" /></td>
        </tr>
    </table>
</form:form>
<div>
    <a href="/groups">Back</a>
</div>
</body>
</html>