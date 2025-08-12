<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Создание тестов</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style> .question-block {
        margin-bottom: 20px;
    }

    input[type=checkbox]:checked ~ label {
        font-weight: bold;
        color: green;
    }
    </style>
</head>
<body>

<form id="addTestForm" action="/secure/admin/add" method="post">
    Название категории:<br>
    <label>
        <input type="text" name="categoryName" required>
    </label><br>
    Название теста:<br>
    <label>
        <input type="text" name="testName" required>
    </label><br>

    <div class="questions-container">
        <div class="question-block">
            Вопрос №1:<br>
            <textarea name="question[]" rows="3" cols="50" placeholder="Напишите вопрос здесь..."
                      required></textarea><br>

            Варианты ответов:
            <ol start="1">
                <li>
                    <input type="text" name="answers[]" required>
                    <input type="hidden" name="isCorrect[]" value="false"/>
                    <input type="checkbox" onchange="toggleCorrect(this)"/> Правильный ответ?<br>
                </li>
                <li>
                    <input type="text" name="answers[]" required>
                    <input type="hidden" name="isCorrect[]" value="false"/>
                    <input type="checkbox" onchange="toggleCorrect(this)"/> Правильный ответ?<br>
                </li>
                <li>
                    <input type="text" name="answers[]" required>
                    <input type="hidden" name="isCorrect[]" value="false"/>
                    <input type="checkbox" onchange="toggleCorrect(this)"/> Правильный ответ?<br>
                </li>
                <li>
                    <input type="text" name="answers[]" required>
                    <input type="hidden" name="isCorrect[]" value="false"/>
                    <input type="checkbox" onchange="toggleCorrect(this)"/> Правильный ответ?<br>
                </li>
            </ol>
        </div>
    </div>

    <button type="button" onclick="addQuestion()">Добавить новый вопрос</button>

    <button type="submit">Создать тест</button>
</form>

<script>
    function toggleCorrect(checkbox) {
        const hiddenField = checkbox.parentElement.querySelector('input[name^="isCorrect"]');
        if (checkbox.checked) {
            hiddenField.value = 'true';
        } else {
            hiddenField.value = 'false';
        }
    }

    function addQuestion() {
        let questionCount = $('.question-block').length + 1;
        let newBlock = '<div class="question-block"> Вопрос №' + questionCount + ':<br> <textarea name="question[]" rows="3" cols="50" placeholder="Напишите вопрос здесь..." required></textarea><br> Варианты ответов: <ol start="' + (questionCount * 4 - 3) + '"> <li> <input type="text" name="answers[]" required> <input type="hidden" name="isCorrect[]" value="false"/> <input type="checkbox" onchange="toggleCorrect(this)" />Правильный ответ?<br> </li> <li> <input type="text" name="answers[]" required> <input type="hidden" name="isCorrect[]" value="false"/> <input type="checkbox" onchange="toggleCorrect(this)" />Правильный ответ?<br> </li> <li> <input type="text" name="answers[]" required> <input type="hidden" name="isCorrect[]" value="false"/> <input type="checkbox" onchange="toggleCorrect(this)" />Правильный ответ?<br> </li> <li> <input type="text" name="answers[]" required> <input type="hidden" name="isCorrect[]" value="false"/> <input type="checkbox" onchange="toggleCorrect(this)" />Правильный ответ?<br> </li> </ol> </div> ';
        $(".questions-container").append(newBlock);
    }
</script>
</body>
</html>