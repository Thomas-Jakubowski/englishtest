
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Creation Inscription</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="style/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<p class="info"></p>
<p>Nom : <c:out value="${member.nom}" /></p>
<p>Prénom : <c:out value="${member.prenom}" /></p>
<p>Email : <c:out value="${member.email}" /></p>
<p>Date de naissance : <c:out value="${member.date}" /></p>
<p>Téléphone : <c:out value="${member.telephone}" /></p>
<p>Sexe : <c:out value="${member.sexe}" /></p>
<p>Pays : <c:out value="${member.country.name}" /></p>
</body>
</html>
