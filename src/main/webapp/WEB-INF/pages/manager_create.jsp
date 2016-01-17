<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="resources/css/style1.css"/>

</head>
<body>

<div id="container">

    <div id="sidebar">

        <h2>Tables</h2>


        <div class="sidemenu">
            <ul>
                <li><a href="${pageContext.request.contextPath}">Managers</a></li>
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
        <form action="${pageContext.request.contextPath}/createManager">
            <p>
                <label >first name?</label>
                <input type="text"  name="firstName" >
            </p>
            <p>
                <label>last name?</label>
                <input type="text"  name="lastName">
            </p>
    <p>
        <label>middle name?</label>
        <input type="text"  name="middleName">
    </p>
    <p>
        <label >personnel</label>
        <input type="text"  name="personnel">
    </p>
    <p>
        <label >email</label>
        <input type="text"  name="email">
    </p>

    <h2>Choose section</h2><br>
    <select name="sec" >
        <option value="0">none</option>
        <c:forEach items="${sectorList}" var="sector">
            <option value="${sector.getId()}">${sector.getName()}</option>
        </c:forEach>
    </select>

    <h2>Choose position</h2><br>
    <select name="pos">
        <option value="0">none</option>
        <c:forEach items="${positionList}" var="position">
            <option value="${position.getId()}">${position.getName()}</option>
        </c:forEach>
    </select>


    <p>
        <input type="submit" value="Add">
    </p>
</form>

    </div>
</div>
</body>
</html>