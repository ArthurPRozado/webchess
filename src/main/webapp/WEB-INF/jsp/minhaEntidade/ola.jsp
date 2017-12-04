<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ola, mundo!</title>
	</head>
	<body>
		<ul>
			<c:forEach var="msg" items="${mensagens}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</body>
</html>
