<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game Over</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .game-end-message {
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 10px;
            font-size: 24px;
            font-weight: bold;
        }
        .restart-btn {
            margin-top: 20px;
        }
        .submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .submit-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>Game Over</h1>

<div class="game-end-message">
    <p>${result}</p>
    <p>Current Score: ${score}</p>
</div>

<div class="restart-btn">
    <a href="partie" class="submit-btn">Play Again</a>
</div>

<form action="logout" method="post">
    <button type="submit" class="restart-btn">Logout</button>
</form>

</body>
</html>
