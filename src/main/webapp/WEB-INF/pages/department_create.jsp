
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
       <form action="${pageContext.request.contextPath}/createDepartment">
           <label>
               <input name="departmentName" type="text">
               Введите название отдела
           </label>
           <input type="submit" value="Создать">
       </form>
</body>
</html>