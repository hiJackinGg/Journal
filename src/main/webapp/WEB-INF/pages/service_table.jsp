<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Таблица отделов</title>
</head>
<body>

<h1>Отделы предприятия</h1>
<table>
    <tr>
        <td>Id</td>
        <td>Имя</td>
        <td>Кол-во сотр.</td>
    </tr>

    <c:if test="${serviceList.size() == 2}">
        <h2>Some text!!!</h2>
    </c:if>

    <c:forEach items="${l}" var="res">
        <tr>
            <td>${res[0]}</td>
            <td>${res[1]}</td>

        </tr>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/createServiceStart">Cоздать предприятие</a>


</table>

</body>
</html>