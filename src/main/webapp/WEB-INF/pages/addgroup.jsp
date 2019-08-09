<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add group</title>
</head>
<body>
<h1>Add New Group</h1>
 <form:form method="post" action="save">
    <table >
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
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
<a href="/groups">Back</a>
</body>
</html>