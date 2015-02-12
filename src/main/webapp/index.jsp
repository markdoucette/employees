<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
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
            <display:table name="${sessionScope.employees}" pagesize="5">
                <display:setProperty name="paging.banner.placement" value="bottom"/>
                <display:column sortable="false" property="id" title="Emp ID"/>
                <display:column sortable="true" property="firstName" title="First Name"/>
                <display:column sortable="false" property="lastName" title="Last Name"/>
                <display:column sortable="true" property="dateOfBirth" title="Date of Birth"
                                decorator="ca.bcit.a00057006.ui.decorator.DateColumnDecorator"/>
            </display:table>
        </div>
    </article>
    <article>
        <div class="forms-container">
            <div id="left" class="column">
                <h1>Add Employee</h1>
                <form action="Employees" method="post">
                    <table>
                        <tr>
                            <td><label>ID:</label></td>
                            <td><input type="text" name="id"/></td>
                        </tr>
                        <tr>
                            <td><label>First Name:</label></td>
                            <td><input type="text" name="firstName"/></td>
                        </tr>
                        <tr>
                            <td><label>Last Name:</label></td>
                            <td><input type="text" name="lastName"/></td>
                        </tr>
                        <tr>
                            <td><label>Date of Birth:</label></td>
                            <td><input type="text" name="dob"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="add" value="Add Employee"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <c:if test="${not empty addViolation}" >
                                    <span class="error"><c:out value="${addViolation}" /></span>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="centre" class="column">
                <h1>Find Employee by Id</h1>
                <form action="Employees" method="post">
                    <table>
                        <tr>
                            <td><label>ID:</label></td>
                            <td><input type="text" name="id"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="find" value="Find Employee"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <c:choose>
                                    <c:when test="${foundEmp != null}">
                                        <c:out value="Found ${foundEmp.firstName} ${foundEmp.lastName}"/>
                                    </c:when>
                                    <c:when test="${empty requestScope.foundEmp}">
                                        <c:out value="Error: No user found by that id"/>
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="right" class="column">
                <h1>Remove Employee</h1>
                <form action="Employees" method="post">
                    <table>
                        <tr>
                            <td><label>ID:</label></td>
                            <td><input type="text" name="id"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="remove" value="Remove Employee"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </article>
</div>
</body>
</html>
