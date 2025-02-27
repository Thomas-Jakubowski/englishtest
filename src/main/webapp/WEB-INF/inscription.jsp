<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Page Inscription</title>
  <meta charset="UTF-8">
  <link href="style/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="inscription" method="post">
  <fieldset>
    <legend>Inscription utilisateur</legend>
    <table border="0">
      <tr>
        <td>Nom</td>
        <td><input type="text" name="nom" value='<c:out value="${nom}" />' /></td>
        <td>*</td>
      </tr>
      <tr>
        <td>Prénom</td>
        <td><input type="text" name="prenom" value='<c:out value="${prenom}" />' /></td>
        <td>*</td>
      </tr>
      <tr>
        <td>Email</td>
        <td><input type="email" id ="email" name="email" value='<c:out value="${email}" />' /></td>
        <td>*</td>
      </tr>
      <tr>
        <td>Mot de passe</td>
        <td><input id="pass1" type="password" name="password"
                   value="" /></td>
        <td>*</td>
      </tr>
      <tr>
        <td>Ville</td>
        <td><select name="ville">
          <c:forEach items="${villes}" var="ville">
            <option value="${ville.id}"><c:out value="${ville.nom}" /></option>
          </c:forEach>
        </select></td>
        <td>*</td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Valider" /><input
                type="reset" value="Cancel" /></td>
        <td></td>
      </tr>
    </table>
    <c:if test="${ not empty erreurs }" >
      <p class="erreur">${erreurs}</p>
    </c:if>
  </fieldset>
</form>
</body>
</html>