<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Answer Questionary</title>
</head>
<body>
  <div class="container">
    <h1>${surveyPerInstance.survey.title}</h1>
    <h3>${surveyPerInstance.survey.description}</h3>
    <div class="row">
      <div class="span12">
        <g:each var="respuestas" in="${surveyPerInstance.answerPerInstances}" status="i">
        <hr>
          <dl>
            <dt>${i+1}.- ${respuestas.question.description}</dt>
            <g:if test="${respuestas.question.questionType == QuestionType.ABIERTA}">
              <dd class="success"><strong>Tu Respuesta: </strong>${respuestas.openAnswerPerUsers.getAt(0).userAnswer}</dd>
            </g:if>
            <g:elseif test="${respuestas.question.questionType == QuestionType.SIN_CALIFICACION}">
              <dd><strong>Tu Respuesta: </strong>${respuestas.answerPerUsers.getAt(0).answer.description}</dd>
              <div class="span12">
                <dt>Comentario:</dt>
                <dd><span class="label">${respuestas.openAnswerPerUsers.getAt(0).userAnswer}</span></dd>
              </div>
            </g:elseif>
            <g:elseif test="${respuestas.question.questionType == QuestionType.CON_CALIFICACION}">
              <div class="row-fluid">
                <div class="span6">
                  <dd><strong>Tu Respuesta: </strong>${respuestas.answerPerUsers.getAt(0).answer.description}</dd>
                </div>
                <div class="span6">
                  <dt>Calificacion:</dt>
                  <dd><span class="badge badge-info">${respuestas.rangeAnswerPerUsers.getAt(0).userAnswer}</span></dd>
                </div>
              </div>
              <div class="span12">
                <dt>Comentario:</dt>
                <dd><span class="label">${respuestas.openAnswerPerUsers.getAt(0).userAnswer}</span></dd>
              </div>
            </g:elseif>
          </dl>
        </g:each>
        <br><br>
          <a class="btn btn-success" href="${url}"><i class="icon-arrow-left icon-white"></i> Regresar</a>
        
      </div>
    </div>
  </div>
</body>
</html>