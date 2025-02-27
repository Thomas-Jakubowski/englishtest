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
<form action="connexion" method="post">
    <fieldset>
        <legend>Connexion</legend>
        <table border="0">
            <tr>
                <td>Email</td>
                <td><input type="email" id ="email" name="email" /></td>
                <td>*</td>
            </tr>
            <tr>
                <td>Mot de passe</td>
                <td><input id="pass1" type="password"  name="password" value="" /></td>
                <td>*</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Connexion" /></td>
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

