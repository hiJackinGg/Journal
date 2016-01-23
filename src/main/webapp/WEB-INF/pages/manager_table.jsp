<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Таблица отделов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style1.css"/>
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

    <h1>Managers</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Код</th>
        <th>email</th>
        <th>Сектор</th>
        <th>Должность</th>
    </tr>

    <%--<c:if test="${managerList.size() == 2}">--%>
        <%--<h2>Some text!!!</h2>--%>
    <%--</c:if>--%>

    <c:forEach items="${managerList}" var="manager">
        <tr>
            <td>${manager.getId()}</td>
            <td>${manager.getFirstName()}</td>
            <td>${manager.getLastName()}</td>
            <td>${manager.getMiddleName()}</td>
            <td>${manager.getPersonnel()}</td>
            <td>${manager.getEmail()}</td>
            <td>${manager.getSector().getName()}</td>
            <td>${manager.getPosition().getName()}</td>
            <td><a href="${pageContext.request.contextPath}/deleteManager/${manager.getId()}">delete</a></td>
        </tr>
    </c:forEach>


    <a href="${pageContext.request.contextPath}/createManagerStart">Добавить сотрудника</a>


</table>

<form action="${pageContext.request.contextPath}/sortManagers">
    <h2>Sort by:</h2><br>
    <select name="sortOpt">
        <option value="firstName">Order by firstname</option>
        <option value="lastName">Order by lastname</option>
        <option value="middleName">Order by middlename</option>
        <option value="personnel">Order by personnel</option>
        <option value="email">Order by email</option>
        <option value="sector">Order by sector</option>
        <option value="position">Order by position</option>
    </select>

    <p>
        <input type="submit" value="Sort">
    </p>
</form>

<form action="${pageContext.request.contextPath}/findManagers">
    <h2>Find by:</h2><br>
    <select name="findOpt">
        <option value="firstName">Find by firstname</option>
        <option value="lastName">Find by lastname</option>
        <option value="middleName">Find by middlename</option>
        <option value="personnel">Find by personnel</option>
        <option value="email">Find by email</option>
        <option value="sector">Find by sector</option>
        <option value="position">Find by position</option>
    </select>

        <label>Title:</label>
        <input type="text"  name="title">

    <p>
        <input type="submit" value="Find">
    </p>
</form>

        <p>
            <a href="${pageContext.request.contextPath}/">Показать всех</a>
        </p>

        <p>
            <a href="${pageContext.request.contextPath}/"><img src="/resources/images/en.jpg"></a>
        </p>

</div>
    </div>
</body>
</html>