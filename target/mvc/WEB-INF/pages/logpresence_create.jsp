<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
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



    <form action="${pageContext.request.contextPath}/logpresence/createLog">


    <h2>Choose manager</h2><br>
    <select name="man" >
        <option value="0">none</option>
        <c:forEach items="${managerList}" var="manager">
            <option value="${manager.getId()}">${manager.getInitials()}</option>
        </c:forEach>
    </select>

    <h2>Choose reason</h2><br>
    <select name="res">
        <option value="0">none</option>
        <c:forEach items="${reasonList}" var="reason">
            <option value="${reason.getId()}">${reason.getName()}</option>
        </c:forEach>
    </select>

    <p>
        <label>Date absence</label>
        <input type="text"  name="dateAbsence">
    </p>
    <p>
        <label>Lateness Time?</label>
        <input type="text"  name="latenessTime" value="none">
    </p>
    <p>
        <label>Note</label>
        <input type="text"  name="note">
    </p>



    <p>
        <input type="submit" value="Add">
    </p>

</form>

    </div>
</div>
</body>
</html>