<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>English Verb Conjugation Game</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
    }
    .game-container {
      background-color: #f5f5f5;
      border-radius: 10px;
      padding: 20px;
      margin-top: 20px;
    }
    .verb-display {
      font-size: 24px;
      font-weight: bold;
      text-align: center;
      margin-bottom: 30px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    input[type="text"] {
      width: 100%;
      padding: 8px;
      font-size: 16px;
      border: 1px solid #ddd;
      border-radius: 4px;
    }
    .submit-btn {
      background-color: #4CAF50;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      margin-top: 10px;
    }
    .submit-btn:hover {
      background-color: #45a049;
    }
    .feedback {
      margin-top: 20px;
      padding: 10px;
      border-radius: 4px;
    }
    .correct {
      background-color: #dff0d8;
      color: #3c763d;
    }
    .incorrect {
      background-color: #f2dede;
      color: #a94442;
    }
    .timer {
      text-align: center;
      font-size: 18px;
      margin-bottom: 20px;
    }
    .game-end-message {
      text-align: center;
      font-size: 20px;
      margin-top: 30px;
      padding: 20px;
      background-color: #f5f5f5;
      border-radius: 10px;
      font-weight: bold;
    }
    .restart-btn {
      text-align: center;
      margin-top: 20px;
    }
  </style>
</head>
<body>
<h1>English Verb Conjugation Game</h1>

<c:if test="${not empty result}">
  <div class="game-end-message">
    <p>${result}</p>
    <div class="restart-btn">
      <a href="partie" class="submit-btn">Play Again</a>
    </div>
  </div>
</c:if>

<c:if test="${empty result}">
  <div class="game-container">
    <p>Welcome, ${joueur.prenom}! Conjugate the following verb:</p>

    <div class="verb-display">
        ${verbe.baseVerbale}
          <p>Current Score: ${score}</p>
    </div>

    <div class="timer" id="timer">00:00</div>

    <form method="post" action="partie">
      <div class="form-group">
        <label for="preterit">Preterit (Simple Past):</label>
        <input type="text" id="preterit" name="preterit" required autocomplete="off">
      </div>

      <div class="form-group">
        <label for="pastParticiple">Past Participle:</label>
        <input type="text" id="pastParticiple" name="pastParticiple" required autocomplete="off">
      </div>

      <button type="submit" class="submit-btn">Submit Answer</button>
    </form>

    <c:if test="${not empty feedback}">
      <div class="feedback ${isCorrect ? 'correct' : 'incorrect'}">
          ${feedback}
      </div>
    </c:if>
  </div>

  <script>
    const startTime = new Date('${startTime}');

    function updateTimer() {
      const currentTime = new Date();
      const elapsedTime = Math.floor((currentTime - startTime) / 1000);

      const minutes = Math.floor(elapsedTime / 60);
      const seconds = elapsedTime % 60;

      document.getElementById('timer').textContent =
              (minutes < 10 ? '0' : '') + minutes + ':' +
              (seconds < 10 ? '0' : '') + seconds;

      if (elapsedTime >= 60) {
        window.location.href = 'partie';
      }
    }

    setInterval(updateTimer, 1000);
    updateTimer();

    document.getElementById('preterit').focus();
  </script>
</c:if>

</body>
</html>
