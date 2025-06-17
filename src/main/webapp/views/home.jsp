<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>FormationMarket</title>
</head>
<body>
	<header>
		<c:if test="${logged}">
			<ul>
				<li><a href="${ pageContext.request.contextPath }/logout">Cliquer pour se déconnecter</a></li>
				<li><a href="${ pageContext.request.contextPath }/formations">Formations</a></li>
				<li><a href="${ pageContext.request.contextPath }/cursus">Cursus</a></li>
			</ul>
			
		</c:if>
		<c:if test="${not logged}">
			<ul>
				<li><a href="${ pageContext.request.contextPath }/login">Cliquer pour se connecter</a></li>
			</ul>
		</c:if>
	</header>
	<h1>Bienvenue sur Formation Market</h1>
</body>
</html>