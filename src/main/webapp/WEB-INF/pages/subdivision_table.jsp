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
        <%--<a href="/j_spring_security_logout">logout</a>--%>
        <h2>Tables</h2>


        <div class="sidemenu">

            <ul>
                <li><a href="${pageContext.request.contextPath}/manager">Managers</a></li>
                <li><a href="${pageContext.request.contextPath}/position" >Positions</a></li>
                <li><a href="${pageContext.request.contextPath}/subdivision">Subdivisions</a></li>
                <li><a href="${pageContext.request.contextPath}/logpresence">Log presence</a></li>
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
                <th>Подразделение</th>
                <th>Должность</th>
            </tr>

            <%--<c:if test="${managerList.size() == 2}">--%>
            <%--<h2>Some text!!!</h2>--%>
            <%--</c:if>--%>

            <c:forEach items="${subdivisionList}" var="subdivision">
                <tr>
                    <td>${subdivision.getId()}</td>
                    <td>${subdivision.getName()}</td>
                    <td>${subdivision.getSubdivisionLevel()}</td>
                    <td><a href="${pageContext.request.contextPath}/deleteSubdivision/${subdivision.getId()}">delete</a></td>
                </tr>
            </c:forEach>


            <a href="${pageContext.request.contextPath}/createSubdivisionStart">Добавить сотрудника</a>


        </table>


    </div>
</div>
</body>
</html>