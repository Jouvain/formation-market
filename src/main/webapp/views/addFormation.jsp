<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>AddFormationAdmin</title>
</head>
<body>
 
	<h1>Ajout de formation :</h1>
	<form action="${pageContext.request.contextPath }/addFormation" method="post" class="addForm">
		<div>
			Nom : <input type="text" name="nom" required>
		</div>
		<div>
			Ville : <input type="text" name="ville" required>
		</div>
		<div>
			Durée : <input type="number" name="nbrJours" step="1" min="1" required>
		</div>
		<div>
			Démarrage : <input type="date" name="dateDebut" required>
		</div>
		<div>
			<button>Ajouter</button>
		</div>
	</form>
 
</body>
</html>
 