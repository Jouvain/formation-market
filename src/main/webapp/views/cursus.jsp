<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Cursus</title>
</head>
<body>
	<header>
		<ul>
			<li><a href="${ pageContext.request.contextPath }/home">Accueil</a></li>
		</ul>	
	</header>
	<h1>Cursus</h1>
	<h2>Bienvenue,  <c:out value="${user.getFirstname() }"></c:out> </h2>
	<h2>Vos formations :</h2>
	<c:forEach items="${ listeFormations}" var="formation">
		<li class="formation">
			<c:out value="${ formation.nom }"></c:out> 
			<c:out value="${ formation.ville }"></c:out>
			<c:out value="${ formation.nbrJours }"></c:out>  
			<c:out value="${ formation.dateDebut }"></c:out> 
		</li>
	</c:forEach>
	<h3>Consultation :</h3>
	<ul class="filters">
		<li class="actions"><a href="${ pageContext.request.contextPath }/pastCursus">Terminées</a></li>
		<li class="actions"><a href="${ pageContext.request.contextPath }/ongoingCursus">En cours</a></li>
		<li class="actions"><a href="${ pageContext.request.contextPath }/futureCursus">À venir</a></li>
		<li><a href="${ pageContext.request.contextPath }/cursus"><button>RESET</button></a></li>
	</ul>	
</body>
</html>