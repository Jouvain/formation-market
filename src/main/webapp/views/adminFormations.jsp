<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>AdminFormations</title>
</head>
<body>
	<header>
		<ul>
			<li><a href="${ pageContext.request.contextPath }/home">Accueil</a></li>
		</ul>
	</header>
	<h1>ADMINISTRATION</h1>

	<h2>Liste des formations disponibles :</h2>
	<c:forEach items="${ listeFormations}" var="formation">
		<li class="formation">
			<c:out value="${ formation.nom }"></c:out> 
			<c:out value="${ formation.ville }"></c:out>
			<c:out value="${ formation.nbrJours }"></c:out>  
			<c:out value="${ formation.dateDebut }"></c:out>
		</li>
	</c:forEach>
	<h3>Actions :</h3>
	<ul class="filters">
		<li class="actions"><a href="${ pageContext.request.contextPath }/sortFormationsByNomADMIN">Tri par nom</a></li>
		<li class="actions"><a href="${ pageContext.request.contextPath }/sortFormationsByDateADMIN">Tri par date</a></li>
		<li><a href="${ pageContext.request.contextPath }/adminFormations"><button>RESET</button></a></li>
	 	<li><a href="${ pageContext.request.contextPath }/addFormation"><button>AJOUT</button></a></li> 
	</ul>
	<form action="${ pageContext.request.contextPath }/filterFormationsByNomADMIN" method="POST">
  		<fieldset>
  			<legend>Recherche selon le nom</legend>
  			Formation : <input type="text" name="nom">
  			<button>Chercher</button>
  	    </fieldset>
 	</form>


</body>
</html>