<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css"/>
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



        <%--<form name = "log" action="${pageContext.request.contextPath}/createLog" method="post">--%>


        <%--<h2>Choose manager</h2><br>--%>
        <%--<select name="manager" >--%>
        <%--<option value="0">none</option>--%>
        <%--<c:forEach items="${managerList}" var="manager">--%>
        <%--<option value="${manager.getId()}">${manager.getInitials()}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>

        <%--<h2>Choose reason</h2><br>--%>
        <%--<select name="reason">--%>
        <%--<option value="0">none</option>--%>
        <%--<c:forEach items="${reasonList}" var="reason">--%>
        <%--<option value="${reason.getId()}">${reason.getName()}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>

        <%--<p>--%>
        <%--<label>Date absence</label>--%>
        <%--<input type="text"  name="dateAbsence">--%>
        <%--</p>--%>
        <%--<p>--%>
        <%--<label>Lateness Time?</label>--%>
        <%--<input type="text"  name="latenessTime" value="none">--%>
        <%--</p>--%>
        <%--<p>--%>
        <%--<label>Note</label>--%>
        <%--<input type="text"  name="note">--%>
        <%--</p>--%>



        <%--<p>--%>
        <%--<input type="submit" value="Add">--%>
        <%--</p>--%>

        <%--</form>--%>


        <form:form commandName="log" name = "log" action="${pageContext.request.contextPath}/updateLog" method="post">
            <table>
                <tr>
                    <th><h2>Choose manager</h2></th>
                    <td>
                        <form:select name = "manager" path="manager">
                            <form:option value="none" label="--Please Select"/>
                            <form:options items="${managerList}" itemValue="id" itemLabel="firstName"/>
                        </form:select>
                    </td>
                    <form:errors path="manager" cssclass="error"></form:errors>
                </tr>
                <tr>
                    <th><h2>Choose reason</h2></th>
                    <td>
                        <form:select name = "reason" path="reason">
                            <form:option value="none" label="--Please Select"/>
                            <form:options items="${reasonList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </td>
                    <form:errors path="reason" cssclass="error"></form:errors>
                </tr>
                    <tr>
                    <th>Date absence:</th>
                    <td><form:input path="dateAbsence" type="text" name = "dateAbsence"/></td>
                    <td></td>
                    </tr>
                <tr>
                    <th>Lateness Time:</th>
                    <td><form:input path="latenessTime" type="text" name="latenessTime" value="none"/></td>
                    <form:errors path="latenessTime" cssclass="error"></form:errors>
                </tr>
                <tr>
                    <th>Note:</th>
                    <td><form:textarea name = "note" path="note" cols="50" rows="8"
                                       style="resize: none;"
                                       maxlength="600"
                                       onKeyPress="return ( this.value.length < 600 );"
                                       placeholder="Maximun 600 characters.."></form:textarea></td>
                    <form:errors path="note" cssclass="error"></form:errors>
                </tr>
                <tr>
                    <input type="submit" value="Add">
                </tr>
            </table>
            <!-- end id-form  -->
        </form:form>






    </div>
</div>
</body>
</html>