<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css"/>--%>
</head>
<body>

<spring:eval expression="log.id == null ? 'Create new record' : 'Update record' " var="formTitle"/>

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

    <h1>${formTitle}</h1>

    <div id="content">



        <form:form commandName="log" name = "log" action="${pageContext.request.contextPath}/createLog" method="post">
            <table>
                <tr>
                    <th><h2>Choose manager</h2></th>
                    <td>
                        <form:select path="manager">
                            <form:option value="" label="--Please Select"/>
                            <form:options items="${managerList}" itemValue="id" itemLabel="firstName"/>
                        </form:select>
                    </td>
                    <td><form:errors path="manager" cssclass="error"></form:errors></td>

                </tr>
                <tr>
                    <th><h2>Choose reason</h2></th>
                    <td>
                        <form:select path="reason">
                            <form:option value="" label="--Please Select"/>
                            <form:options items="${reasonList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </td>
                    <td><form:errors path="reason" cssclass="error"></form:errors></td>
                </tr>
                <%--<tr>--%>
                    <%--<th>Date absence:</th>--%>
                    <%--<td><form:input path="dateAbsence" type="text" name = "dateAbsence"/></td>--%>
                    <%--<td></td>--%>
                <%--</tr>--%>
                <tr>
                    <th>Lateness Time:</th>
                    <td><form:input path="latenessTime" type="text" name="latenessTime" value="none"/></td>
                    <td><form:errors path="latenessTime" cssclass="error"></form:errors></td>
                </tr>
                <tr>
                    <th>Note:</th>
                    <td><form:textarea path="note" cols="50" rows="8"
                                       style="resize: none;"
                                       maxlength="600"
                                       onKeyPress="return ( this.value.length < 600 );"
                                       placeholder="Maximun 600 characters.."></form:textarea></td>
                    <td><form:errors path="firstName" cssclass="error"></form:errors></td>
                </tr>
                <tr>
                    <input type="submit" value="Save">
                </tr>
            </table>

            <button type="reset" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
                <span class="ui-button-text">Reset</span>
            </button>
            <!-- end id-form  -->
        </form:form>






    </div>
</div>
</body>
</html>