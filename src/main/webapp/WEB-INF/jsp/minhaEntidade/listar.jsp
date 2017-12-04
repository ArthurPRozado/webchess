<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Entidades</title>
	</head>
	<body>
		<dl>
			<c:forEach var="e" items="${entidades}">
				<dt>${e.numero}</dt>
				<dd>${e.texto}</dd>
			</c:forEach>
		</dl>
	</body>
</html>
