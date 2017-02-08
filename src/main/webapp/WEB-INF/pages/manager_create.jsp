<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
    <%--<link rel="stylesheet" type="text/css" href="resources/css/style1.css"/>--%>

</head>
<body>

<div id="container">

    <div id="sidebar">

        <h2>Tables</h2>


        <div class="sidemenu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/manager">Managers</a></li>
                <li><a href="${pageContext.request.contextPath}/position" >Positions</a></li>
                <li><a href="${pageContext.request.contextPath}/sector">Sectors</a></li>
                <li><a href="${pageContext.request.contextPath}/logpresence">Log presence</a></li>
                <li><a href="${pageContext.request.contextPath}/service">Service</a></li>
                <li><a href="${pageContext.request.contextPath}/department">Department</a></li>
                <li><a href="${pageContext.request.contextPath}/delegation">Delegation</a></li>
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <li><a href="${pageContext.request.contextPath}/reason">Reason</a></li>
                <li><a href="${pageContext.request.contextPath}/role">Role</a></li>
            </ul>

        </div>

    </div>


    <div id="content">
        <form:form commandName="manager" name = "manager" action="${pageContext.request.contextPath}/createManager" method="post">

            <table>

                <tr>
                    <th>First name</th>
                    <td><form:input path="firstName" type="text" name="firstName"/></td>
                    <td><form:errors path="firstName" cssclass="error"></form:errors></td>
                </tr>

                <tr>
                    <th>Last name</th>
                    <td><form:input path="lastName" type="text" name="lastName"/></td>
                    <td><form:errors path="lastName" cssclass="error"></form:errors></td>
                </tr>

                <tr>
                    <th>Middle name</th>
                    <td><form:input path="middleName" type="text" name="middleName"/></td>
                    <td><form:errors path="middleName" cssclass="error"></form:errors></td>
                </tr>

                <tr>
                    <th>Personnel</th>
                    <td><form:input path="personnel" type="text" name="personnel"/></td>
                    <td><form:errors path="personnel" cssclass="error"></form:errors></td>
                </tr>

                <tr>
                    <th>email</th>
                    <td><form:input path="email" type="text" name="email"/></td>
                    <td><form:errors path="email" cssclass="error"></form:errors></td>
                </tr>

                <tr>
                    <th><h2>Choose position</h2></th>
                    <td>
                        <form:select path="position">
                            <form:option value="" label="--Please Select"/>
                            <form:options items="${positionList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </td>
                    <td><form:errors path="position" cssclass="error"></form:errors></td>

                </tr>
                <tr>
                    <th><h2>Choose subdivision</h2></th>
                    <td>
                        <form:select path="subdivision">
                            <form:option value="" label="--Please Select"/>
                            <form:options items="${subdivisionList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </td>
                    <td><form:errors path="subdivision" cssclass="error"></form:errors></td>
                </tr>

                <tr>
                    <input type="submit" value="Save">
                </tr>
            </table>

            <button type="reset" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
                <span class="ui-button-text">Reset</span>
            </button>
</form:form>

    </div>
</div>
</body>
</html>