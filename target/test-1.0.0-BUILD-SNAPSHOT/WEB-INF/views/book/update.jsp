<%--
  Created by IntelliJ IDEA.
  User: goott4
  Date: 2023-04-04
  Time: 오후 5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>책 수정</title>
</head>
<body>
<h1>책 수정</h1>
<form method="POST">
    <input type="hidden" name="_method" value="put" />
    <p>제목 : <input type="text" name="title" value="${data.title}"></p>
    <p>카테고리 : <input type="text" name="category" value="${data.category}"></p>
    <p>가격 : <input type="text" name="price" value="${data.price}"></p>
    <p><input type="submit" value="저장"></p>
</form>
</body>
</html>
