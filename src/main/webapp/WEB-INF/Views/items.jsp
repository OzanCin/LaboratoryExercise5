<%--
  Created by IntelliJ IDEA.
  User: ozanc
  Date: 8/9/2021
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Items</title>
</head>
<body>
<div class="container">
    <h1>Items available under the ${desc}</h1>
    <h1>${id}</h1> <table class="table table-striped">
    <thead>
    <tr>
        <th>Item Code</th>
        <th>Item Description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos1}" var="todo">
        <tr>
            <td>${todo.itemcode}</td>
            <td>${todo.itemdesc}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <form method="POST">
    <input class="btn btn-back" type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
