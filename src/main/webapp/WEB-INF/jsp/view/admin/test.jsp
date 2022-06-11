
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: SuperElectro
  Date: 05/05/2022
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<select class="form-control" name="module">


    <c:forEach items="${enseignants}" var="p">
        <option><c:out value="${p.nom}" /></option>
    </c:forEach>
</select>



<select class="form-control" name="module">


    <c:forEach items="${modules}" var="p">
        <option><c:out value="${p.titre}" /></option>
    </c:forEach>
</select>

</body>
</html>
