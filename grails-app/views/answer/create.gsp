<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <title>Create answer</title>
  <meta name="layout" content="bootstrap"/>
</head>
<body>
  <div class="container">
    <h1>Captura respuesta para "${question.description}"</h1>
    <div class="row-fluid">
      <div class="span6">
        <g:if test="${question.questionType == QuestionType.SIN_CALIFICACION}">
          <g:form name="answer1Form" action="agregar">
            <label>¿Cual es la respuesta?:</label>
            <textarea class="field span12" rows="4" name="description"></textarea>
            <input type="hidden" value="${question.id}" name="question" />
            <br>
            <input class="btn btn-primary" type="submit" value="Enviar" />
          </g:form>
        </g:if>
        <g:else>
          <g:form name="answerForm" action="agregar">
            <label>Descripción:</label>
            <textarea class="field span12" rows="4" name="description"></textarea>
            <label>Tipo de respuesta:</label>
            <g:select name="answerType" from="${AnswerType.values()}"/> 
            <input type="hidden" value="${question.id}" name="question" />
            <br>
            <input class="btn btn-primary" type="submit" value="Enviar" />
          </g:form>
        </g:else>
      </div>
    </div>
  </div>
</body>
</html>
