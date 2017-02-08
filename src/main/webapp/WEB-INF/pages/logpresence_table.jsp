<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Таблица отделов</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style1.css"/>

    <%--<spring:url value="/resources/scripts/jquery-1.7.1.js" var="jquery_url" />--%>
    <%--<spring:url value="/resources/scripts/jquery-ui-1.8.16.custom.min.js" var="jquery_ui_url" />--%>
    <%--<spring:url value="/resources/styles/custom-theme/jquery-ui-1.8.16.custom.css" var="jquery_ui_theme_css" />--%>
    <%--<link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_theme_css}" />--%>
    <%--<script src="${jquery_url}" type="text/javascript"><jsp:text/></script>--%>
    <%--<script src="${jquery_ui_url}" type="text/javascript"><jsp:text/></script>--%>

    <%--<!-- jqGrid -->--%>
    <%--<spring:url value="/resources/jqgrid/css/ui.jqgrid.css" var="jqgrid_css" />--%>
    <%--<spring:url value="/resources/jqgrid/js/i18n/grid.locale-en.js" var="jqgrid_locale_url" />--%>
    <%--<spring:url value="/resources/jqgrid/js/jquery.jqGrid.min.js" var="jqgrid_url" />--%>
    <%--<link rel="stylesheet" type="text/css" media="screen" href="${jqgrid_css}" />--%>
    <%--<script type="text/javascript" src="${jqgrid_locale_url}"><jsp:text/></script>--%>
    <%--<script type="text/javascript" src="${jqgrid_url}"><jsp:text/></script>--%>

    <%--<script type="text/javascript">--%>
        <%--$(function(){--%>
            <%--$("#list").jqGrid({--%>
                <%--url:'${showContactUrl}/listgrid',--%>
                <%--datatype: 'json',--%>
                <%--mtype: 'GET',--%>
                <%--colNames:['${labelContactFirstName}', '${labelContactLastName}', '${labelContactBirthDate}'],--%>
                <%--colModel :[--%>
                    <%--{name:'firstName', index:'firstName', width:150},--%>
                    <%--{name:'lastName', index:'lastName', width:100},--%>
                    <%--{name:'birthDateString', index:'birthDate', width:100}--%>
                <%--],--%>
                <%--jsonReader : {--%>
                    <%--root:"contactData",--%>
                    <%--page: "currentPage",--%>
                    <%--total: "totalPages",--%>
                    <%--records: "totalRecords",--%>
                    <%--repeatitems: false,--%>
                    <%--id: "id"--%>
                <%--},--%>
                <%--pager: '#pager',--%>
                <%--rowNum:10,--%>
                <%--rowList:[10,20,30],--%>
                <%--sortname: 'firstName',--%>
                <%--sortorder: 'asc',--%>
                <%--viewrecords: true,--%>
                <%--gridview: true,--%>
                <%--height: 250,--%>
                <%--width: 500,--%>
                <%--caption: '${labelContactList}',--%>
                <%--onSelectRow: function(id){--%>
                    <%--document.location.href ="${showContactUrl}/" + id;--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>

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
<table id = "list">
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
            <td>${log.getId()}</td>
            <td>${log.getManager().getFirstName()}</td>
            <td>${log.getManager().getLastName()}</td>
            <td>${log.getManager().getMiddleName()}</td>
            <td>${log.getReason().getName()}</td>
            <td>${log.getDateAbsence()}</td>
            <td>${log.getLatenessTime()}</td>
            <td>${log.getNote()}</td>

            <sec:authorize access="hasRole('supervisor')">
            <td><a href="${pageContext.request.contextPath}/logpresence/deleteLog/${log.getId()}">delete</a>
            </sec:authorize>


        </tr>
    </c:forEach>

    <h1>Log presence</h1>
    <a href="${pageContext.request.contextPath}/logpresence/createLogStart">Cоздать запись</a>


</table>
        <div id="pager"></div>

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