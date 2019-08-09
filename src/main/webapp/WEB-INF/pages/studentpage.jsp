<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
</head>
<body>
    <h1>Student list</h1>
    <form:form method="post" action="findstd">
        <table >
            <tr>
                <td>ID : </td>
                <td><form:input path="id"/></td>
                <td>Any Name : </td>
                <td><form:input path="firstname"/></td>
                <td>Group :</td>
                <td><form:select path="grp.name">
                    <form:option value="<Empty>"/>
                    <c:forEach var="grp" items="${grp}">
                        <option>${grp.name}</option>
                    </c:forEach>
                </form:select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Find"/>
    </form:form>
    <button onclick="window.location.href = '/students';"> Find All</button>
    <br>
        <table border="2" width="60%" cellpadding="2">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Father's Name</th>
                <th>Last Name</th>
                <th>Group</th>
                <th>Faculty</th>
                <th>Study type</th>
                <th>Enrollment data</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${std}" var="std">
                <tr>
                    <td>${std.id}</td>
                    <td>${std.firstname}</td>
                    <td>${std.fathersname}</td>
                    <td>${std.lastname}</td>
                    <td>${std.grp.name}</td>
                    <td>${std.grp.faculty}</td>
                    <td>${std.studytype}</td>
                    <td>${std.date}</td>
                    <td><a href="editstd/${std.id}">Edit</a> </td>
                    <td><a href="deletestd/${std.id}">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    <div>
        <a href="addstudent">Add student</a>
    </div>
    <h3>To other pages</h3>
    <div>
        <a href="/">Main page</a>
    </div>
    <div>
        <a href="/groups">View groups</a>
    </div>

</body>
</html>