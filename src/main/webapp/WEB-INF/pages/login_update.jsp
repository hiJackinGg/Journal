<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
    <script src="${baseURL}/resources/scripts/jquery-1.7.1.js" type="text/javascript"></script>
    <script src="${baseURL}/resources/scripts/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
    <link href="${baseURL}/resources/jtable/themes/metro/blue/jtable.min.css"  rel="stylesheet" type="text/css"  />
    <script src="${baseURL}/resources/jtable/jquery.jtable.min.js" type="text/javascript"></script>
    <link href="${baseURL}/resources/styles/custom-theme/jquery-ui-1.8.16.custom.css"  rel="stylesheet" type="text/css" />



    <head/>


<body>

<script type="text/javascript">
    $(document).ready(function () {
        $('#TableContainer').jtable({
            title: 'Table of people',
            selecting: true,
            paging: true,
            pageSize: 10,
            sorting: true,
            actions: {
                listAction: '/login/loginListgrid',
                createAction: '/GettingStarted/CreatePerson',
                updateAction: '/GettingStarted/UpdatePerson',
                deleteAction: '/GettingStarted/DeletePerson'
            },
            fields: {
                Id: {
                    key: true,
                    list: false
                },
                Username: {
                    title: 'username',
                    width: '40%'
                },
                Password: {
                    title: 'password',
                    width: '20%'
                }
//                RecordDate: {
//                    title: 'Record date',
//                    width: '30%',
//                    type: 'date',
//                    create: false,
//                    edit: false
//                }
            }
        });

        $('#TableContainer').jtable('load');
    });
</script>
<div>
<div id="TableContainer"></div>
</div>
</body>

</html>