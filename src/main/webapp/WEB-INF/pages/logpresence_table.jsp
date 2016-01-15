<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Таблица отделов</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="resources/css/style1.css"/>

</head>
<body>

<div id="container">

    <div id="sidebar">

        <h2>Tables</h2>


        <div class="sidemenu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Managers</a></li>
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

<h1></h1>
<table>
    <tr>
        <th>Id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Причина</th>
        <th>Дата</th>
        <th>Время опоздания</th>
        <th>Заметка</th>
    </tr>

    <c:if test="${managerList.size() == 2}">
        <h2>Some text!!!</h2>
    </c:if>

    <c:forEach items="${logList}" var="log">
        <tr>
            <td>${log.getLogId()}</td>
            <td>${log.getManager().getFirstName()}</td>
            <td>${log.getManager().getLastName()}</td>
            <td>${log.getManager().getMiddleName()}</td>
            <td>${log.getReason().getReasonName()}</td>
            <td>${log.getDateAbsence()}</td>
            <td>${log.getLatenessTime()}</td>
            <td>${log.getNote()}</td>
            <td><a href="${pageContext.request.contextPath}/logpresence/deleteLog/${log.getLogId()}">delete</a>


        </tr>
    </c:forEach>

    <h1>Log presence</h1>
    <a href="${pageContext.request.contextPath}/logpresence/createLogStart">Cоздать запись</a>


</table>

<p>
<a href="${pageContext.request.contextPath}/logpresence/findLogs1">Отсутствующие</a>
</p>

<p>
<a href="${pageContext.request.contextPath}/logpresence/findLogs2">Кто больше всего опоздал</a>
</p>

<p>
    <a href="${pageContext.request.contextPath}/logpresence">Показать всех</a>
</p>

<h2>Find in period</h2>
<form action="${pageContext.request.contextPath}/logpresence/findLogs3">
    <label>From</label>
    <input type="text"  name="date1">
    <label>Till</label>
    <input type="text"  name="date2">
    <p>
    <input type="submit" value="Find">
    </p>
</form>
    </div>
</div>

</body>
</html>