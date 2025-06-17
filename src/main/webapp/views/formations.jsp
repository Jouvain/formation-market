<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<title>Formations</title>
</head>
<body>
	<header>
		<ul>
			<li><a href="${ pageContext.request.contextPath }/home">Accueil</a></li>
		</ul>
	</header>
	<h1>Page des formations</h1>
	<h2>Bienvenue,  <c:out value="${user.getFirstname() }"></c:out> </h2>
	<h2>Liste des formations disponibles :</h2>
	<c:forEach items="${ listeFormations}" var="formation">
		<li class="formation">
			<c:out value="${ formation.nom }"></c:out> 
			<c:out value="${ formation.ville }"></c:out>
			<c:out value="${ formation.nbrJours }"></c:out>  
			<c:out value="${ formation.dateDebut }"></c:out>
			<c:choose>
				<c:when test="${formation.dateDebut > today and not user.getFormations().contains(formation) }" >
					<form action="${pageContext.request.contextPath}/inscription" method="post">
	            		<input type="hidden" name="id" value="${formation.id}" />
	            		<button type="submit">S'inscrire</button>
	      	        </form> 
				</c:when>
			</c:choose>
		</li>
	</c:forEach>
	<h3>Consultation :</h3>
	<ul class="filters">
		<li class="actions"><a href="${ pageContext.request.contextPath }/sortFormationsByNom">Tri par nom</a></li>
		<li class="actions"><a href="${ pageContext.request.contextPath }/sortFormationsByDate">Tri par date</a></li>
		<li><a href="${ pageContext.request.contextPath }/formations"><button>RESET</button></a></li>
	</ul>
	<form action="${ pageContext.request.contextPath }/filterFormationsByNom" method="POST">
  		<fieldset>
  			<legend>Recherche selon le nom</legend>
  			Formation : <input type="text" name="nom">
  			<button>Chercher</button>
  	    </fieldset>
 	</form>
</body>
</html>