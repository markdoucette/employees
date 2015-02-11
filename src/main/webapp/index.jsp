<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page import="ca.bcit.a00057006.util.URLUtilities" %>
<html>
<head>
    <title>Comp 4655 Assignment 01</title>
    <link rel="stylesheet" href="css/styles.css" type="text/css"/>
</head>
<body>
<div id="main-content">
    <h2>Comp 4655 Assignment 01</h2>
    <%--<article>--%>
        <%--<div class="table-div">--%>
            <%--<table>--%>
                <%--<th>Employee ID:</th><th>First Name</th><th>Last Name</th><th>Date of Birth</th>--%>
                <%--<c:forEach var="emp" items="${employees}">--%>
                    <%--<tr><td><c:out value="${emp.id}"/></td><td><c:out value="${emp.firstName}"/></td>--%>
                        <%--<td><c:out value="${emp.lastName}" /></td><td>${emp.dateOfBirth}</td></tr>--%>
                <%--</c:forEach>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</article>--%>
    <article>
        <h1>Employees List</h1>
        <div class="table-div">
            <display:table name="${sessionScope.employees}" pagesize="5"  >
                <display:setProperty name="paging.banner.placement" value="bottom"/>
                <display:column sortable="true" property="id" title="Emp ID" />
                <display:column sortable="false" property="firstName" title="First Name"/>
                <display:column sortable="false" property="lastName" title="Last Name"/>
                <display:column sortable="true" property="dateOfBirth" title="Date of Birth"/>
            </display:table>
        </div>
    </article>
    <article>
        <div class="forms-container">
            <div id="left" class="column">
                <form action="Employees" method="post">
                <table>
                <tr><td><label>ID:</label></td><td><input type="text" name="id" /></td></tr>
                <tr><td><label>First Name:</label></td><td><input type="text" name="firstName" /></td></tr>
                <tr><td><label>Last Name:</label></td><td><input type="text" name="lastName" /></td></tr>
                <tr><td><label>Date of Birth:</label></td><td><input type="text" name="dob" /></td></tr>
                <tr><td> </td><td><input type="submit" name="add" value="Add Employees" /></td></tr>
            </table>
                </form>
            </div>
            <div id="centre" class="column">
                <form action="Employees" method="post">
                <table>
                <tr><td><label>ID:</label></td><td><input type="text" name="id" /></td></tr>
                <tr><td> </td><td><input type="submit" name="find" value="Find Employee" /></td></tr>
            </table>
                </form>
            </div>
            <div id="right" class="column">
                <form action="Employees" method="post">
                <table>
                <tr><td><label>ID:</label></td><td><input type="text" name="id" /></td></tr>
                <tr><td> </td><td><input type="submit" name="remove" value="Remove Employee" /></td></tr>
            </table>
                </form>
            </div>
        </div>
    </article>
</div>
</body>
</html>
